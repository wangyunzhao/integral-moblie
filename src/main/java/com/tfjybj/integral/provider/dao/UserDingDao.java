package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.DingIntegralDetailModel;
import com.tfjybj.integral.model.DingMonthIntegralModel;
import com.tfjybj.integral.model.DingTodayIntegralDetailModel;
import com.tfjybj.integral.model.MonthPluginSumModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDingDao接口
 * userDing表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("userDingDao")
public interface UserDingDao{
    /**
     * 手机端，根据用户id查询该人的每天的加分详情
     * 崔晓鸿，2019年9月10日16:29:32
     *
     * @param userId 用户积分id
     * @return
     */
    List<DingIntegralDetailModel> findMyIntegral(@Param("userId") String userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 手机端，根据用户id查询该人所在期的所有人的这个月的积分和上个月的积分及该人的加分详情
     * 崔晓鸿，2019年9月11日23:05:08
     *
     * @param userId 用户积分id
     * @return
     */
    List<DingMonthIntegralModel> findAllIntegral(@Param("userId") String userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);


    String findUsercodeByUserdingid(@Param("userDingId") String userDingId);

    /**
     * 查询tid_detail_record表获取每个人的当天钉钉加分详情信息
     * @return
     */
    List<DingTodayIntegralDetailModel>findUserDingIntegralDetail();

    /**
     * 查询tid_detail_record表获取每个人的当天钉钉加分汇总信息
     * @return
     */
    List<MonthPluginSumModel>SumUserDingIntegralDetail();
}
