package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.tfjybj.integral.entity.MonthIntegralEntity;
import com.tfjybj.integral.model.*;
import com.tfjybj.integral.provider.dao.*;
import com.tfjybj.integral.utils.cache.JSONUtils;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class HomeLoadingService {
    @Resource
    private IntegralDetailDao integralDetailDao;
    @Resource
    private TikUserDao tikUserDao;
    @Resource
    private IntegralCollectDao integralCollectDao;
    @Resource
    private PluginUserDao pluginUserDao;
    @Autowired
    RedisUtil redisUtil;
    @Resource
    private MonthIntegralDao monthIntegralDao;
    @Resource
    private OrganizationDao organizationDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private ScoreDisplayService scoreDisplayService;

    public UserHomeModel queryPageContentByUserId(String userId) {
        HomePluginModel homePluginModel = new HomePluginModel();
        UserHomeModel userHomeModel = new UserHomeModel();
        // 查询计算今日的积分和本月的积分以及总积分
        //从Redis中查询今天的积分
        //今日的日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date localDateTime = new Date();
        String toDay = sf.format(localDateTime);
        IntegralColDetDayModel integralColDetDayModel = scoreDisplayService.findTodayIntegralByRedis(userId, toDay);
        //今日的总收益
        userHomeModel.setSumIntegral(integralColDetDayModel.getSumProfit());
        //本月的总收益
        //根据userId查询用户总积分
        Integer Integral = tikUserDao.queryUserIntegralByUserId(userId);
        if (Integral == null) {
            Integral = 0;
        }
        userHomeModel.setIntegral(Integral + integralColDetDayModel.getSumProfit());
        //根据userId查询用户本月所获得的积分
        Integer monthearmingsIncome = integralCollectDao.queryUserMonthIntegralByUserId(userId);
        Integer monthearmingsOut=integralCollectDao.queryUserMonthIntegralOutByUserId(userId);

        if (monthearmingsIncome == null) {
            monthearmingsIncome = 0;
        }
        if (monthearmingsOut==null){
            monthearmingsOut=0;
        }
        Integer monthearmings=monthearmingsIncome-monthearmingsOut;
        userHomeModel.setMonthearmings(integralColDetDayModel.getSumProfit() + monthearmings);
        //根据id查询用户是否有未读的信息，这条用首页消息按钮是否展示红点的判断
        List<MessageModel> messageModelList = messageDao.selectMessageByAcceoptId(userId);
        if (messageModelList.size() > 0) {
            userHomeModel.setMessageCount(messageModelList.size());
        }
        // userHomeModel=   JSON.toJSONString(redisUtil.get(RedisContants.homeLoad+userId));
        //判断首页加载所需要的插件信息，是否已经存在Redis中，如果在就从Redis中查，如果没有则从数据库中查，然后放到Redis中
        if (redisUtil.hasKey(RedisContants.homeLoad + userId)) {
            //通过key值，从Redis中查找首页加载所需要的插件信息
            homePluginModel = JSONObject.parseObject(redisUtil.get(RedisContants.homeLoad + userId), HomePluginModel.class);
            userHomeModel.setSetPlugin(homePluginModel.getSetPlugin());
            return userHomeModel;
        } else {
            //根据userId查询用户绑定的插件
            List<PluginModel> listModel = pluginUserDao.queryUserBindPlugin(userId);
            homePluginModel.setSetPlugin(listModel);
            //将查到的首页加载所需要的信息存放到Redis中
            redisUtil.set(RedisContants.homeLoad + userId, JSON.toJSONString(homePluginModel));
            //设置redis过期时间
            redisUtil.expire(RedisContants.homeLoad + userId, 1800);
            userHomeModel.setSetPlugin(homePluginModel.getSetPlugin());
            return userHomeModel;
        }

    }

    /**
     * Job汇总积分详情到月积分表-赵芬-2019年9月14日16:30:28
     */
    public void SummaryMonthIntegral() {
        //查询本月净收入
        List<QueryMonthModel> MonthNetIncomes = integralDetailDao.QueryMonthNetIncome();
        //插入本月净收入
        for (int i = 0; i < MonthNetIncomes.size(); i++) {
            MonthNetIncomes.get(i).setId(IdWorker.getIdStr());
        }
        monthIntegralDao.InsertMonthNetIncome(MonthNetIncomes);
        //查询上月净收入
        List<QueryMonthModel> queryLastMonthNetIncomes = integralDetailDao.QueryLastMonthNetIncome();
        //更新上月净收入
        monthIntegralDao.UpdateLastMonthNetIncome(queryLastMonthNetIncomes);
        //查询本月总收入
        List<QueryMonthModel> queryMonthIncomes = integralDetailDao.QueryMonthIncome();
        //更新本月总收入
        monthIntegralDao.UpdateMonthIncome(queryMonthIncomes);
        //查询本月总支出
        List<QueryMonthModel> queryMonthexpenditures = integralDetailDao.QueryMonthexpenditure();
        //更新本月总支出
        monthIntegralDao.UpdateMonthexpenditure(queryMonthexpenditures);
        //查询本月插件加分
        List<QueryMonthPluginModel> queryMonthPlugin = integralDetailDao.QueryMonthPlugin();

        //更新本月插件加分
        //定义一个HashMap用来存放queryMonthPlugin遍历结果[key-userid value(plugin_id,integral)]
        HashMap<String, HashMap> MonthPluginHashMaps = new HashMap<String, HashMap>();
        //实例化一个model(plugin_id,integral)
        MonthPluginSumModel monthPluginSumModel = new MonthPluginSumModel();
        //定义一个HashMap用来存放遍历内容 插件（key)-加分(value)
        HashMap<String, Integer> MonthPluginHashMap = new HashMap<String, Integer>();
        //实例化一个UserId
        String USERID = "1";
        //for(Iterator<QueryMonthPluginModel> it = queryMonthPlugin.iterator(); it.hasNext();  )  {}
        for (QueryMonthPluginModel queryMonthPluginModel : queryMonthPlugin) {
            //得带外层Hashmap(MonthPluginHashMaps)的key值-userId
            String userId = queryMonthPluginModel.getUserId();
            //判断外层HashMap里有没有这个人的UserId
            boolean Istrue = userId.equals(USERID);
            boolean isFalse = USERID.equals(userId);
            if (Istrue) {
                MonthPluginHashMap.put(queryMonthPluginModel.getPluginId(), queryMonthPluginModel.getSumIntegral());
            } else {
                //nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'MonthHashmapModel' in 'class com.tfjybj.integral.model.MonthHashmapModel'

                //如果外层HashMap里不包含这个这个人的UserId
                //将这个人的keyValue（userId，pluginId-integral)存到数据库中
                //将这个人的keyValuepluginId-integral转为Json格式
                boolean Istrueoffalse = USERID.equals("1");
                if (!USERID.equals("1")) {
                    String HashJson = JSONUtils.toJSONString(MonthPluginHashMap);
                    MonthHashmapModel monthHashmapModel = new MonthHashmapModel();
                    monthHashmapModel.setUserId(USERID);
                    monthHashmapModel.setPluginJson(HashJson);
                    monthIntegralDao.UpdateMonthPlugin(monthHashmapModel);
                }
                MonthPluginHashMap.clear();
                MonthPluginHashMap.put(queryMonthPluginModel.getPluginId(), queryMonthPluginModel.getSumIntegral());
                MonthPluginHashMaps.put(userId, MonthPluginHashMap);
                USERID = userId;
            }
        }

        //组织结构批量插入和更新
        //查询本月组织结构净收入
        List<QueryMonthOrganizationModel> queryMonthOrganizationNetIncomes = integralDetailDao.QueryMonthOrganizationIncome();
        //插入本月组织结构净收入
        for (int i = 0; i < queryMonthOrganizationNetIncomes.size(); i++) {
            queryMonthOrganizationNetIncomes.get(i).setId(IdWorker.getIdStr());
        }
        organizationDao.InsertMonthOrganizationNetIncome(queryMonthOrganizationNetIncomes);
        //查询本月组织结构总收入
        List<QueryMonthOrganizationModel> queryMonthOrganizationIncomes = integralDetailDao.QueryMonthOrganizationIncome();
        //更新本月组织结构总收入
        organizationDao.UpdateMonthOrganizationIncome(queryMonthOrganizationIncomes);
        //查询本月组织结构总支出
        List<QueryMonthOrganizationModel> queryMonthOrganizationExpenditures = integralDetailDao.QueryMonthOrganizationExpenditure();
        //更新本月组织结构总支出
        organizationDao.UpdateMonthOrganizationExpenditure(queryMonthOrganizationExpenditures);

        //查询本月组织结构插件加分
        List<QueryMonthOrganizationPluginModel> queryMonthOrganizationPlugin = integralDetailDao.QueryMonthOrganizationPlugin();
        //更新本月组织结构插件加分
        //定义一个HashMap用来存放QueryMonthOrganizationPlugin遍历结果
        Map<String, HashMap> OrganizationPluginHashMaps = new HashMap<String, HashMap>();
        //实例化一个model
        MonthPluginSumModel monthOrganizationPluginSumModel = new MonthPluginSumModel();
        //定义一个HashMap用来存放遍历内容 插件（key)-加分(value)
        HashMap<String, Integer> OrganizationPluginHashMap = new HashMap<String, Integer>();
        //实例化一个OUserID
        String O_USERId = "1";
        for (QueryMonthOrganizationPluginModel queryMonthOrganizationPluginModel : queryMonthOrganizationPlugin) {
            //外层HashMap(MonthOrganizationPluginHashMap)的key值-organizationId
            String OriganizationId = queryMonthOrganizationPluginModel.getOrganizationId();
            //判断外层的HashMap里有没有这个组织的组织id
            if (OriganizationId != null && !OriganizationId.equals("")) {
                boolean OriganiztionIdExit = OriganizationId.equals(O_USERId);
                if (OriganiztionIdExit) {
                    OrganizationPluginHashMap.put(queryMonthOrganizationPluginModel.getPluginId(), queryMonthOrganizationPluginModel.getSumIntegral());
                } else {
                    //如果外层的HashMap不包括这个组织的O_UserId
                    //将这个组织的的KeyValue(O_userId,pluginId-integral)存到数据库中
                    //将这个人的keyValue(pluginid-integral）转为json格式
                    boolean IsEqualsOUserid = O_USERId.equals("1");
                    if (!O_USERId.equals("1")) {
                        String HashJsonOriganization = JSONUtils.toJSONString(OrganizationPluginHashMap);
                        MonthHashmapModel monthOriganizationHashmapModel = new MonthHashmapModel();
                        monthOriganizationHashmapModel.setUserId(O_USERId);
                        monthOriganizationHashmapModel.setPluginJson(HashJsonOriganization);
                        organizationDao.UpdateMonthOrganizationPlugin(monthOriganizationHashmapModel);
                    }
                    OrganizationPluginHashMap.clear();
                    OrganizationPluginHashMap.put(queryMonthOrganizationPluginModel.getPluginId(), queryMonthOrganizationPluginModel.getSumIntegral());
                    OrganizationPluginHashMaps.put(OriganizationId, OrganizationPluginHashMap);
                    O_USERId = OriganizationId;
                }
            } else {
                continue;
            }

        }
    }

    public Integer queryUnreadMessage(String userId) {
        try {
            return messageDao.queryUnreadMessage(userId);
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer queryUnreadRedMessage(String userId) {
        try {
            return messageDao.queryUnreadRedMessage(userId);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据用户ID查询本月所支出的分数-曹祥铭-2019-10-8 10:20:15
     * @param userId
     * @return
     */
   public Integer queryUserMonthIntegralOutByUserId(String userId){
        return  integralCollectDao.queryUserMonthIntegralOutByUserId(userId);
   }
}
