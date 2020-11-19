package com.tfjybj.integral.provider.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.cloud.tool.json.JsonUtil;
import com.tfjybj.integral.model.TodayTomatoModel;
import com.tfjybj.integral.model.TomatoIntegralModel;
import com.tfjybj.integral.model.TomatoPercentModel;
import com.tfjybj.integral.model.WeekTomatoModel;
import com.tfjybj.integral.provider.dao.TomatoRecordDao;

import com.tfjybj.integral.utils.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.json.JsonObject;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service("TomatoService")
public class TomatoService {
    @Resource
    private TomatoRecordDao tomatoRecordDao;
//    @Autowired
//    RedisUtil redisUtil;

    /**
    * @Description:  查询登录用户今日的番茄积分和总积分以及番茄数量和名称
    * @Param:
    * @return:
    * @Author: 张文慧
    * @Date: 2019/9/15
    */
    public TomatoIntegralModel queryHistory(String userId){
        TomatoIntegralModel tomatoIntegralModel = new TomatoIntegralModel();
        Integer tomatoAll =  tomatoRecordDao.queryAll(userId);
        Integer tomatoToday =  tomatoRecordDao.queryToday(userId);
        List<TodayTomatoModel> todayTomatoModels =  tomatoRecordDao.queryHistory(userId);

        if(tomatoAll == null ){
            tomatoAll=0;
        }
        if( tomatoToday == null){
            tomatoToday=0;
        }

        tomatoIntegralModel.setTodayIntegral(tomatoToday);
        tomatoIntegralModel.setSumIntegral(tomatoAll);
        tomatoIntegralModel.setTodayTomatoList(todayTomatoModels);
        /**此代码为Redis*/
//        redisUtil.set("Integral:tomato", JSON.toJSONString(tomatoIntegralModel));
//        redisUtil.expire("Integral:tomato",600);
//        TomatoIntegralModel tomatoIntegralModel1 = new TomatoIntegralModel();
//        tomatoIntegralModel1 = JSONObject.parseObject(redisUtil.get("Integral:tomato"),TomatoIntegralModel.class);
        return tomatoIntegralModel;
    }

    /**
    * @Description:  查询登录用户本周和上周的积分
    * @Param:
    * @return:
    * @Author: 张文慧
    * @Date: 2019/9/15
    */
    public TomatoPercentModel queryWeekIntegral(String userId){
        TomatoPercentModel tomatoPercentModel = new TomatoPercentModel();
        List<WeekTomatoModel> weekTomatoList = tomatoRecordDao.queryWeekIntegral(userId);
        /**此代码为设置补缺星期，没有成功，保留以后用*/
//        String[] week=null;
//        for (int z = 0; z <weekTomatoList.size(); z++) {
//            week[z]=weekTomatoList.get(z).getWeek();
//        }
//        for (int j = 0; j <weekEnum.length ; j++) {
//            if (!CheckIsIn(week,weekEnum[j]))
//            {
//                //false则添加
//                WeekTomatoModel to=new WeekTomatoModel();
//                to.setWeek(weekEnum[j]);
//                weekTomatoList.add(to);
//            }
//
//        }

        int lastWeekSum = 0 ;
        int weekSum = 0 ;

        for (int i = 0; i < weekTomatoList.size(); i++) {
            /** 本周和上周积分，没有数据的，补充0 */
            if (weekTomatoList.get(i).getBeforeIntegral()==null ||weekTomatoList.get(i).getBeforeIntegral().toString()== "" ){
                weekTomatoList.get(i).setBeforeIntegral(0);
            }
            if (weekTomatoList.get(i).getThisIntegral()==null ||weekTomatoList.get(i).getThisIntegral().toString()== "" ){
                weekTomatoList.get(i).setThisIntegral(0);
            }

            /** 计算本周和上周总分数 、百分比*/

            Object obj1 = weekTomatoList.get(i).getThisIntegral();
            weekSum += Integer.parseInt(obj1.toString());

            Object obj2 = weekTomatoList.get(i).getBeforeIntegral();
            lastWeekSum += Integer.parseInt(obj2.toString());
        }
        if((weekSum - lastWeekSum)>0){
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String percent =numberFormat.format( (float)(weekSum - lastWeekSum)/(float)weekSum * 100);
            String status ="提升";

            tomatoPercentModel.setPercent(percent);
            tomatoPercentModel.setStatus(status);

        }
        tomatoPercentModel.setWeekTomatoList(weekTomatoList);
        return tomatoPercentModel;
    }



    /**此代码为设置补缺星期，没有成功*/
//    public static boolean CheckIsIn(String[] arr,String week)
//    {
//        return Arrays.asList(arr).contains(week);
//    }
//    public String[] weekEnum={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};



}

