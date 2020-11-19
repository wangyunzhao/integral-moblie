package com.tfjybj.integral.provider.service;


import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.cloud.tool.json.JsonUtil;
import com.google.common.collect.Lists;
import com.tfjybj.integral.model.SportRecordModel;
import com.tfjybj.integral.provider.dao.SportRecordDao;
import com.tfjybj.integral.utils.cache.JSONUtils;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service("dingSportService")
public class DingSportService {
    @Resource
    private SportRecordDao sportRecordDao;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 功能描述: 查询个人的钉钉运动数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @auther: 董思莲
     * @since: 1.0.0 2019/9/11 20:04
     */

    public List<SportRecordModel> querySelfSport(String userId, int pageNum, int pageSize) {

        //从redis中获取数据，并判断redis中是否有数据，有数据直接传出，无数据从数据库查询
        List<SportRecordModel> sportRecordModelList = JSONObject.parseArray(redisUtil.get(RedisContants.dingSport + userId), SportRecordModel.class);
        if (sportRecordModelList != null && sportRecordModelList.size() != 0) {
            return sportRecordModelList;
        }
        //获取当前日期时间
         LocalDateTime todayTime= LocalDateTime.now();
        //格式化日期形如20191011
         DateTimeFormatter dTF= DateTimeFormatter.ofPattern("yyyyMMdd");
         String endTime=todayTime.format(dTF);
         //获取本月第一天
         LocalDateTime firstDay = todayTime.with(TemporalAdjusters.firstDayOfMonth());
         String startTime=firstDay.format(dTF);
        //limit分页查询通过每页数量pageSize设置页码
        pageNum = (pageNum - 1) * pageSize;
        sportRecordModelList = sportRecordDao.querySelfSport(userId, pageNum, pageSize,startTime,endTime);
        if (sportRecordModelList.size() > 0 && sportRecordModelList != null) {
            //将从数据库dao层中获取到的数据存入到redis
            redisUtil.set(RedisContants.dingSport + userId, JSONUtils.toJSONPrettyString(sportRecordModelList));
            //设置redis过期时间为600秒
            redisUtil.expire(RedisContants.dingSport + userId, 600);
            return sportRecordModelList;
        } else {
            return null;
        }
    }
}
