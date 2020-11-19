package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.tfjybj.integral.entity.AllusersEntity;
import com.tfjybj.integral.model.*;
import com.tfjybj.integral.provider.dao.IntegralDetailDao;
import com.tfjybj.integral.provider.dao.UserDingDao;
import com.tfjybj.integral.provider.dao.ZtUserDao;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import com.tfjybj.integral.utils.http.HttpUtils;
import com.tfjybj.integral.utils.http.ResponseWrap;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service("dingAttendanceService")
public class DingAttendanceService {
    @Resource
    private UserDingDao userDingDao;
    @Resource
    private ZtUserDao ztUserDao;
    @Resource
    private IntegralDetailDao integralDetailDao;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 手机端，根据用户id查询该人的每天的加分详情
     * 崔晓鸿，2019年9月10日16:28:20
     *
     * @return
     */

    public List<DingIntegralModel> findMyIntegral(String userId, int pageNum, int pageSize) {

//        List<DingIntegralDetailModel> detail=userDingDao.findMyIntegral(userId,pageNum, pageSize);
//        DingIntegralModel dingIntegralModel=new DingIntegralModel();
//        dingIntegralModel.setTotal(detail.size());
//        Set detailSet=new HashSet(detail);
//        dingIntegralModel.setDetail(detailSet);
//        List<DingIntegralModel> list= Arrays.asList(dingIntegralModel);
//        return list;


        //if(redisUtil.hasKey(RedisContants.allIntegral + userId)){
        List<Object> listGet = redisUtil.lGet(RedisContants.dingAttendanceSelf + userId + pageNum + pageSize, 0, -1);
        if (listGet.size() == 0) {
            List<DingIntegralDetailModel> detail = userDingDao.findMyIntegral(userId, pageNum, pageSize);
            //处理空指针异常的情况
            if (detail == null) {
                detail = new ArrayList();
            }
            DingIntegralModel dingIntegralModel = new DingIntegralModel();
            dingIntegralModel.setTotal(detail.size());
            //Set detailSet=new HashSet(detail);
            dingIntegralModel.setDetail(detail);
            List<DingIntegralModel> list = Lists.newArrayList();
            list.add(dingIntegralModel);
            redisUtil.lSet(RedisContants.dingAttendanceSelf + userId + pageNum + pageSize, JSON.toJSONString(list));
            redisUtil.expire(RedisContants.dingAttendanceSelf + userId + pageNum + pageSize, 3600);
            return list;
        } else {
            //List<Object> listGet = redisUtil.lGet(RedisContants.allIntegral + userId, 0, -1);

            List<DingIntegralModel> dingIntegralModel = JSONObject.parseArray(listGet.get(0).toString(), DingIntegralModel.class);
            return dingIntegralModel;
        }


    }

    /**
     * 手机端，根据用户id查询该人所在期的所有人的这个月的积分和上个月的积分及该人的加分详情
     * 崔晓鸿，2019年9月11日23:07:09
     *
     * @return
     */

    public List<DingMonthDetailIntegralModel> findAllIntegral(String userId, int pageNum, int pageSize) {
//        List<DingIntegralDetailModel> detail=userDingDao.findMyIntegral(userId,pageNum, pageSize);
//        List<DingMonthIntegralModel> month=userDingDao.findAllIntegral(userId,pageNum, pageSize);
//
//        DingMonthDetailIntegralModel dingMonthDetailModel=new DingMonthDetailIntegralModel();
//
//        Set detailSet=new HashSet(detail);
//        Set monthSet=new HashSet(month);
//        dingMonthDetailModel.setMonth(monthSet);
//        dingMonthDetailModel.setEveryDay(detailSet);
//        List<DingMonthDetailIntegralModel> list= Arrays.asList(dingMonthDetailModel);
//        return list;


        //if(redisUtil.hasKey(RedisContants.allIntegral + userId)){
        List<Object> listGet = redisUtil.lGet(RedisContants.dingAttendanceAll + userId + pageNum + pageSize, 0, -1);
        if (listGet.size() == 0) {
            List<DingIntegralDetailModel> detail = userDingDao.findMyIntegral(userId, pageNum, pageSize);
            List<DingMonthIntegralModel> month = userDingDao.findAllIntegral(userId, pageNum, pageSize);

            //处理空指针异常的情况
            if (detail == null) {
                detail = new ArrayList();
            }
            if (month == null) {
                month = new ArrayList();
            }

            DingMonthDetailIntegralModel dingMonthDetailModel = new DingMonthDetailIntegralModel();

//            Set detailSet=new HashSet(detail);
//            Set monthSet=new HashSet(month);
            dingMonthDetailModel.setMonth(month);
            dingMonthDetailModel.setEveryDay(detail);
            List<DingMonthDetailIntegralModel> list = Lists.newArrayList();
            list.add(dingMonthDetailModel);
            redisUtil.lSet(RedisContants.dingAttendanceAll + userId + pageNum + pageSize, JSON.toJSONString(list));
            redisUtil.expire(RedisContants.dingAttendanceAll + userId + pageNum + pageSize, 3600);
            return list;
        } else {
            //List<Object> listGet = redisUtil.lGet(RedisContants.allIntegral + userId, 0, -1);
            List<DingMonthDetailIntegralModel> dingMonthDetailIntegralModel = JSONObject.parseArray(listGet.get(0).toString(), DingMonthDetailIntegralModel.class);
            return dingMonthDetailIntegralModel;
        }
    }

    /**
     * 手机端，根据userdingid查询usercode
     * 崔晓鸿，2019年9月10日16:19:26
     *
     * @return
     */
    public LoginResultModel findUsercodeByUserdingid(String userDingId) {

        String Usercode = userDingDao.findUsercodeByUserdingid(userDingId);

        String findUserByOrganizationId = "http://192.168.96.187:8080/auth-web/access/noPwdLogin/" + Usercode;
        HttpUtils http1 = HttpUtils.get(findUserByOrganizationId);
        http1.addHeader("Content-Type", "application/json; charset=utf-8");
        ResponseWrap responseWrap1 = http1.execute();
        System.out.println(responseWrap1.getString());
        LoginResultModel loginResultModel = JSON.parseObject(responseWrap1.getString(), LoginResultModel.class);
        return loginResultModel;
    }

    /**
     * 将钉钉考勤数据同步到tik_integral表和tik_user表
     * 赵芬，2019年10月9日11:00:17
     *
     * @return
     */
    public void synchronizationDingData() {
        //1.查询当天的钉钉考勤加分记录
        List<DingTodayIntegralDetailModel> DingcheckDetail = userDingDao.findUserDingIntegralDetail();
        //2.将钉钉考勤加分记录插入到加分详情表
        for (int i = 0; i < DingcheckDetail.size(); i++) {
            DingcheckDetail.get(i).setId(IdWorker.getIdStr());
        }
        if (DingcheckDetail.size() != 0) {
            int insertDingData = integralDetailDao.InsertUserYestodayDingIntegralDetail(DingcheckDetail);
//        3.汇总单个人的钉钉考勤的加分记录
            List<MonthPluginSumModel> UserDingCheckSunIntegral = userDingDao.SumUserDingIntegralDetail();
            //4.更新单个人的钉钉汇总积分到总积分
            for (int i = 0; i < UserDingCheckSunIntegral.size(); i++) {
                int updateDingcheck = ztUserDao.UpdateTotalSummary(UserDingCheckSunIntegral.get(i));
            }
        }else {
            XxlJobLogger.log(this.getClass().getSimpleName()+"---钉钉考勤数据为空，查看钉钉考勤数据是否正确！");
        }
    }

}
