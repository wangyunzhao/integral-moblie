package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tfjybj.integral.model.*;
import com.tfjybj.integral.provider.dao.IntegralDetailDao;
import com.tfjybj.integral.provider.dao.PluginDao;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import javax.annotation.Resource;
import javax.persistence.Convert;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service("scoreDisplayService")
public class ScoreDisplayService {
    @Resource
    private IntegralDetailDao integralDetailDao;
    @Resource
    private PluginDao pluginDao;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 手机端，通过用户id、日期查询所得总分、收入详情和支出详情
     * 崔晓鸿，2019年9月12日11:14:34
     *
     * @return List<IntegralColDetDayModel>
     */

    public List<IntegralColDetDayModel> findByUserIdTodayIntegral(String userId, String createTime, int pageNum, int pageSize) {
        //判断是否是今天，今天要加上redis中的数据
        boolean isToday=IsToday(createTime);
        if(isToday){
            IntegralColDetDayModel integralColDetDayModel = new IntegralColDetDayModel();
            integralColDetDayModel =findTodayIntegralByRedis(userId,createTime);
            IntegralColDetDayModel integralColDetDayModelNew = new IntegralColDetDayModel();
            integralColDetDayModelNew.setSumIncome(integralColDetDayModel.getSumIncome());
            integralColDetDayModelNew.setSumPay(integralColDetDayModel.getSumPay());
            integralColDetDayModelNew.setSumProfit(integralColDetDayModel.getSumProfit());
            integralColDetDayModelNew.setIncomeList(integralColDetDayModel.getIncomeList());

            List<IntegralCollectPluginModel> incomeDetailList=new ArrayList();
            incomeDetailList=integralColDetDayModel.getIncomeDetailList();
            //积分收入详情排序
            incomeDetailList = incomeDetailList.stream().sorted(Comparator.comparing(IntegralCollectPluginModel::getCreateTime).reversed()).collect(Collectors.toList());
            integralColDetDayModelNew.setIncomeDetailList(incomeDetailList);

            List<IntegralColDetDayModel> integralColDetDayList= Arrays.asList(integralColDetDayModelNew);
            return integralColDetDayList;

        }else{
            //不是今天的情况
            //首先从redis中拿到数据
            List<Object> listGet = redisUtil.lGet(RedisContants.dayIntegral + userId+createTime+pageNum+pageSize, 0, -1);
            //判断redis数据不为空
            if(listGet.size()==0){
                //从数据库中拿出收入详情和支出详情(按插件分组求和)
                List<IntegralCollectPluginModel> income=integralDetailDao.findIncomeCollectIntegral(userId,createTime,pageNum, pageSize);
                //List<IntegralCollectPluginModel> outcome=integralDetailDao.findOutcomeCollectIntegral(userId,createTime,pageNum, pageSize);

                //从数据库中拿出收入详情和支出详情(不按插件分组求和)
                List<IntegralCollectPluginModel> incomeDetail=integralDetailDao.findIncomeDetailIntegral(userId,createTime,pageNum, pageSize);
                //List<IntegralCollectPluginModel> outcomeDetail=integralDetailDao.findOutcomeDetailIntegral(userId,createTime,pageNum, pageSize);
                //查询收益
                IntegralColDetDayModel collectDetail=integralDetailDao.findCollectIntegral(userId,createTime,pageNum, pageSize);
                //处理空指针异常的情况
                if(income==null){
                    income=new ArrayList();
                }
                if(incomeDetail==null){
                    incomeDetail=new ArrayList();
                }
                if(collectDetail==null){
                    collectDetail=new IntegralColDetDayModel();
                }

                //积分详情排序
                incomeDetail = incomeDetail.stream().sorted(Comparator.comparing(IntegralCollectPluginModel::getCreateTime).reversed()).collect(Collectors.toList());

                //将拿到的收入详情，支出详情放到set中，将set当道实体中，实体放到一个list中，拼接数据。
                Set incomeSet=new HashSet(income);
                //按插件分组详情
                collectDetail.setIncomeList(incomeSet);
                //不按插件分组
                collectDetail.setIncomeDetailList(incomeDetail);
                List<IntegralColDetDayModel> list= Arrays.asList(collectDetail);
                //将得到的个人的所选择日期所生成的收入支出所有情况的list存储到redis
                redisUtil.lSet(RedisContants.dayIntegral+userId+createTime+pageNum+pageSize, JSON.toJSONString(list));
                //设置过期时间为1个小时
                redisUtil.expire(RedisContants.dayIntegral+userId+createTime+pageNum+pageSize,3600);
                return list;
            }else{
                //如果不是当天，且有数据，说明已经将那天的数据查询过，并且放到了redis中
                List<IntegralColDetDayModel> integralColDetDayPayModels =  JSONObject.parseArray(listGet.get(0).toString(),IntegralColDetDayModel.class);
                return  integralColDetDayPayModels;

            }
        }


    }

    /**
     * 手机端，通过用户id、日期查询支出详情
     * 崔晓鸿，2019年9月12日11:14:34
     *
     * @return List<IntegralCollectPluginModel>
     */

    public List<IntegralColDetDayPayModel> findByUserIdTodayExpendIntegral(String userId, String createTime, int pageNum, int pageSize){
        //判断是否是今天，今天要加上redis中的数据
        boolean isToday=IsToday(createTime);
        if(isToday){
            IntegralColDetDayModel integralColDetDayModel = new IntegralColDetDayModel();
            integralColDetDayModel =findTodayIntegralByRedis(userId,createTime);
            IntegralColDetDayPayModel collectDetail=new IntegralColDetDayPayModel();
            collectDetail.setExpendList(integralColDetDayModel.getOutcomeList());

            List<IntegralCollectPluginModel> outcomeDetailList=new ArrayList();
            outcomeDetailList=integralColDetDayModel.getOutcomeDetailList();
            //积分收入详情排序
            outcomeDetailList = outcomeDetailList.stream().sorted(Comparator.comparing(IntegralCollectPluginModel::getCreateTime).reversed()).collect(Collectors.toList());

            collectDetail.setExpendDetailList(outcomeDetailList);
            List<IntegralColDetDayPayModel> listNew= Arrays.asList(collectDetail);
            return listNew;
        }else{
            List<Object> listGet = redisUtil.lGet(RedisContants.monthIntegralOut + userId+createTime+pageNum+pageSize, 0, -1);
            if(listGet.size()==0){
                List<IntegralCollectPluginModel> outcome=integralDetailDao.findOutcomeCollectIntegral(userId,createTime,pageNum, pageSize);
                List<IntegralCollectPluginModel> outcomeDetail=integralDetailDao.findOutcomeDetailIntegral(userId,createTime,pageNum, pageSize);
                IntegralColDetDayPayModel collectDetail=new IntegralColDetDayPayModel();
                Set outcomeSet=new HashSet(outcome);
                collectDetail.setExpendList(outcomeSet);
                //积分支出降序排序
                outcomeDetail = outcomeDetail.stream().sorted(Comparator.comparing(IntegralCollectPluginModel::getCreateTime).reversed()).collect(Collectors.toList());
                collectDetail.setExpendDetailList(outcomeDetail);
                List<IntegralColDetDayPayModel> list= Arrays.asList(collectDetail);
                redisUtil.lSet(RedisContants.monthIntegralOut+userId+createTime+pageNum+pageSize,JSON.toJSONString(list));
                redisUtil.expire(RedisContants.monthIntegralOut+userId+createTime+pageNum+pageSize,3600);

                return list;
            }else{

                List<IntegralColDetDayPayModel> integralColDetDayPayModelsList =  JSONObject.parseArray(listGet.get(0).toString(),IntegralColDetDayPayModel.class);
                return  integralColDetDayPayModelsList;

            }
        }

    }

    /**
     * 通过用户id、日期查询所得总分、收入详情、支出详情、上个月各插件分、本月各插件分
     * 崔晓鸿，2019年9月13日20:18:34
     *
     * @return List<IntegralCollectMonthModel>
     */

    public List<IntegralCollectMonthModel> findByUserIdMonth(String userId, String createTime, int pageNum, int pageSize) {
            //收入支出详情
            List<IntegralCollectPluginModel> income=integralDetailDao.findIncomeMonthCollectIntegral(userId,createTime,pageNum, pageSize);
            List<IntegralCollectPluginModel> outcome=integralDetailDao.findOutcomeMonthCollectIntegral(userId,createTime,pageNum, pageSize);
            //上月本月各个插件分
            List<IntegralPluginlMonthModel> lashmonth=integralDetailDao.findLastMonthCollectIntegral(userId,createTime,pageNum, pageSize);
            List<IntegralPluginlMonthModel> nowmonth=integralDetailDao.findNowMonthCollectIntegral(userId,createTime,pageNum, pageSize);

            //汇总月分
            List<IntegralCollectPluginModel> incomeAll=integralDetailDao.findIncomeMonthCollectIntegralAll(userId,createTime);
            List<IntegralCollectPluginModel> outcomeAll=integralDetailDao.findOutcomeMonthCollectIntegralAll(userId,createTime);


        //处理空指针异常的情况
        if(income==null){
            income=new ArrayList();
        }
        if(outcome==null){
            outcome=new ArrayList();
        }
        if(lashmonth==null){
            lashmonth=new ArrayList();
        }
        if(nowmonth==null){
            nowmonth=new ArrayList();
        }
        if(incomeAll==null){
            incomeAll=new ArrayList();
        }
        if(outcomeAll==null){
            outcomeAll=new ArrayList();
        }

            IntegralCollectMonthModel collectDetail=new IntegralCollectMonthModel();
            collectDetail.setMonthincome(incomeAll.stream().mapToInt(o->o.getSumIntegral()).sum());
            collectDetail.setMonthexpend(outcomeAll.stream().mapToInt(o->o.getSumIntegral()).sum());
            collectDetail.setMonthearmings(incomeAll.stream().mapToInt(o->o.getSumIntegral()).sum()-outcomeAll.stream().mapToInt(o->o.getSumIntegral()).sum());



            //当前时间
            Date now = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
            //获取今天的日期
            String nowDay = sf.format(now);
            //String getDay=createTime;
            Date getDayDate= null;
            try {
                getDayDate = sf.parse(createTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String getDayString=sf.format(getDayDate);
            boolean isThisMonth=getDayString.equals(nowDay);
            //如果是本月
            if(isThisMonth){
                //从Redis取当日积分
                IntegralColDetDayModel integralColDetDayModel = new IntegralColDetDayModel();
                integralColDetDayModel =findTodayIntegralByRedis(userId,createTime);
                List<IntegralColDetDayModel> integralColDetDayList= Arrays.asList(integralColDetDayModel);

                income.addAll(integralColDetDayModel.getIncomeDetailList());
                outcome.addAll(integralColDetDayModel.getIncomeDetailList());

                collectDetail.setMonthincome(collectDetail.getMonthincome()+integralColDetDayList.stream().mapToInt(o->o.getSumIncome()).sum());
                collectDetail.setMonthexpend(collectDetail.getMonthexpend()+integralColDetDayList.stream().mapToInt(o->o.getSumPay()).sum());
                collectDetail.setMonthearmings(collectDetail.getMonthearmings()+integralColDetDayList.stream().mapToInt(o->o.getSumProfit()).sum());

            }
            //不是本月
            else{
                collectDetail.setMonthincome(collectDetail.getMonthincome());
                collectDetail.setMonthexpend(collectDetail.getMonthexpend());
                collectDetail.setMonthearmings(collectDetail.getMonthearmings());

            }

           //积分按时间降序排列
        income = income.stream().sorted(Comparator.comparing(IntegralCollectPluginModel::getCreateTime).reversed()).collect(Collectors.toList());
        outcome = outcome.stream().sorted(Comparator.comparing(IntegralCollectPluginModel::getCreateTime).reversed()).collect(Collectors.toList());


        Set lashmonthSet=new HashSet(lashmonth);
            Set nowmonthSet=new HashSet(nowmonth);
            collectDetail.setIncome(income);
            collectDetail.setExpend(outcome);
            collectDetail.setLastMonth(lashmonthSet);
            collectDetail.setNowMonth(nowmonthSet);
            List<IntegralCollectMonthModel> list= Arrays.asList(collectDetail);
            redisUtil.lSet(RedisContants.monthIntegralInOut+userId+createTime+pageNum+pageSize,JSON.toJSONString(list));
            redisUtil.expire(RedisContants.monthIntegralInOut+userId+createTime+pageNum+pageSize,3600);

            return list;
    }

    /**
     * 根据userId、日期查询各插件分别所获得积分和所在部门平均值
     * 崔晓鸿，2019年9月14日11:57:59
     *
     * @return List<IntegralCollectAllModel>
     */

    public List<IntegralCollectAllModel> findIntegralDetailByUserId(String userId, String startTime,String endTime) {
        List<Object> listGet = redisUtil.lGet(RedisContants.allIntegral + userId+startTime+endTime, 0, -1);
        //if(redisUtil.hasKey(RedisContants.allIntegral + userId)){
        if(listGet.size()==0){
            List<IntegralCollectAllSelfAvgModel> selfintegral=integralDetailDao.findCollectSelfIntegral(userId,startTime,endTime);
            List<IntegralCollectAllSelfAvgModel> avgintegral=integralDetailDao.findCollectAvgIntegral(userId,startTime,endTime);

            //处理空指针异常的情况
            if(selfintegral==null){
                selfintegral=new ArrayList();
            }
            if(avgintegral==null){
                avgintegral=new ArrayList();
            }

            IntegralCollectAllModel collectSelfAvg=new IntegralCollectAllModel();

            Set selfintegralSet=new HashSet(selfintegral);
            collectSelfAvg.setSelfintegral(selfintegralSet);
            Set avgintegralSet=new HashSet(avgintegral);
            collectSelfAvg.setAvgintegral(avgintegralSet);
            List<IntegralCollectAllModel> list= Arrays.asList(collectSelfAvg);
            redisUtil.lSet(RedisContants.allIntegral+userId+startTime+endTime,JSON.toJSONString(list));
            redisUtil.expire(RedisContants.allIntegral+userId+startTime+endTime,3600);
            return list;
        }else{
            //List<Object> listGet = redisUtil.lGet(RedisContants.allIntegral + userId, 0, -1);
            List<IntegralCollectAllModel> integralColDetDayPayModels =  JSONObject.parseArray(listGet.get(0).toString(),IntegralCollectAllModel.class);
            return  integralColDetDayPayModels;
        }
    }

    /**
     * 根据当天分数从Redis取
     * 崔晓鸿，2019年10月6日11:09:00
     *
     * @return List<IntegralCollectAllModel>
     */
    public IntegralColDetDayModel findTodayIntegralByRedis(String userId, String createTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Object> redisTodayOut = redisUtil.lGet(RedisContants.addScores + userId, 0, -1);
        List<IntegralDetailModel> integralDetailModelListOut =  JSONObject.parseArray(redisTodayOut.toString(),IntegralDetailModel.class);
        // 利用map的分组求和求支出分值的总和
        Map<String, IntSummaryStatistics> collectOut = integralDetailModelListOut.stream().collect(Collectors.groupingBy(IntegralDetailModel::getPluginId, Collectors.summarizingInt(IntegralDetailModel::getIntegral)));

        //收入-根据key从redis中取出
        List<Object> redisTodayIn = redisUtil.lGet(RedisContants.payIncome + userId, 0, -1);
        List<IntegralDetailModel> integralDetailModelListIn =  JSONObject.parseArray(redisTodayIn.toString(),IntegralDetailModel.class);
        //利用map的分组求和方法求收入分值的总和
        Map<String, IntSummaryStatistics> collectIn = integralDetailModelListIn.stream().collect(Collectors.groupingBy(IntegralDetailModel::getPluginId, Collectors.summarizingInt(IntegralDetailModel::getIntegral)));
        // 如果求其中某一个map中key的积分的和，则getSum()

        //得到所有的插件的信息
        List<PluginModel> pluginModelList=pluginDao.selectAllPlugin();
        //实例化两个list，存储日详细收入和详细支出
        List<IntegralCollectPluginModel> collectPluginListOut=new ArrayList();
        List<IntegralCollectPluginModel> collectPluginListIn=new ArrayList();
        //根据插件的数量循环查询自己的插件加分并且计算数据
        for(int i=0;i<pluginModelList.size();i++){
            //支出
            IntSummaryStatistics statisticsPlugOut = collectOut.get(pluginModelList.get(i).getPlugin_id());
            //判断插件是否为空，然后判断当前插件今日的减分情况
            if(statisticsPlugOut!=null){
                IntegralCollectPluginModel collectPluginOut=new IntegralCollectPluginModel();
                collectPluginOut.setPluginName(pluginModelList.get(i).getName());
                collectPluginOut.setSumIntegral((int)statisticsPlugOut.getSum());
                try {
                    collectPluginOut.setCreateTime(sfTime.parse(createTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                collectPluginListOut.add(collectPluginOut);
            }
            //收入
            IntSummaryStatistics statisticsPluginIn = collectIn.get(pluginModelList.get(i).getPlugin_id());
            //判断插件是否为空，然后判断当前插件今日的加分情况
            if(statisticsPluginIn!=null){
                IntegralCollectPluginModel collectPluginIn=new IntegralCollectPluginModel();
                collectPluginIn.setPluginName(pluginModelList.get(i).getName());
                collectPluginIn.setSumIntegral((int)statisticsPluginIn.getSum());
                try {
                    collectPluginIn.setCreateTime(sfTime.parse(createTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                collectPluginListIn.add(collectPluginIn);
            }
        }



        //IntegralColDetDayModel collectDetailAll=integralDetailDao.findCollectIntegral(userId,createTime,pageNum, pageSize);
        IntegralColDetDayModel collectDetailAll=new IntegralColDetDayModel();

        //获取今天的收入，支出，收益
        collectDetailAll.setSumIncome(integralDetailModelListIn.stream().mapToInt(o->o.getIntegral()).sum());
        collectDetailAll.setSumPay(integralDetailModelListOut.stream().mapToInt(o->o.getSelfIntegral()).sum());
        collectDetailAll.setSumProfit(integralDetailModelListIn.stream().mapToInt(o->o.getIntegral()).sum()-integralDetailModelListOut.stream().mapToInt(o->o.getSelfIntegral()).sum());

        //真实详情（不根据插件分类）

        List<IntegralCollectPluginModel> collectPluginListDetailOut=new ArrayList();
        List<IntegralCollectPluginModel> collectPluginListDetailIn=new ArrayList();
        for (int i=0;i<integralDetailModelListIn.size();i++){
            IntegralCollectPluginModel collectPluginDetailIn=new IntegralCollectPluginModel();
            for(int j=0;j<pluginModelList.size();j++){
                if(pluginModelList.get(j).getPlugin_id().equals(integralDetailModelListIn.get(i).getPluginId())){
                    collectPluginDetailIn.setPluginName(pluginModelList.get(j).getName());
                }
            }

            collectPluginDetailIn.setSumIntegral(integralDetailModelListIn.get(i).getIntegral());
            try {
                collectPluginDetailIn.setCreateTime(sfTime.parse(integralDetailModelListIn.get(i).getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            collectPluginListDetailIn.add(collectPluginDetailIn);
        }
        for (int i=0;i<integralDetailModelListOut.size();i++){
            IntegralCollectPluginModel collectPluginDetailOut=new IntegralCollectPluginModel();
            for(int j=0;j<pluginModelList.size();j++){
                if(pluginModelList.get(j).getPlugin_id().equals(integralDetailModelListOut.get(i).getPluginId())){
                    collectPluginDetailOut.setPluginName(pluginModelList.get(j).getName());
                }
            }
            collectPluginDetailOut.setSumIntegral(integralDetailModelListOut.get(i).getIntegral());
            try {
                collectPluginDetailOut.setCreateTime(sfTime.parse(integralDetailModelListOut.get(i).getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            collectPluginListDetailOut.add(collectPluginDetailOut);
        }
        collectDetailAll.setIncomeDetailList(collectPluginListDetailIn);
        collectDetailAll.setOutcomeDetailList(collectPluginListDetailOut);
        //collectDetail.setExpendList(outcomeSet);
        //实例化一个set存储收入详情
        Set incomeSet=new HashSet(collectPluginListIn);
        //实例化一个set存储支出详情
        Set outcomeSet=new HashSet(collectPluginListOut);
        collectDetailAll.setIncomeList(incomeSet);
        collectDetailAll.setOutcomeList(outcomeSet);

        return collectDetailAll;
    }
    public boolean IsToday(String createTime){
        //获取当前时间
        Date dateNow = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //当前时间格式化
        String dateNowString=simpleDateFormat.format(dateNow);
        //获取传入的日期
        Date getDate=null;
        try {
            getDate=simpleDateFormat.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //传入的日期格式化
        String getDateString=simpleDateFormat.format(getDate);
        return getDateString.equals(dateNowString);
    }


}
