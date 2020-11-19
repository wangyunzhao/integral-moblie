package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.entity.IntegralDetailEntity;
import com.tfjybj.integral.model.*;
import feign.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * IntegralDetailDao接口
 * integralDetail表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("integralDetailDao")
public interface IntegralDetailDao{
  /**
   * 汇总用户今日积分的方法-曹祥铭-2019年9月12日15:07:10
   * @param userId  用户id
   * @return  今日积分
   */
  int  findByUserIdTodayIntegral(String userId);
  /**
   * 手机端，日积分——通过用户id、日期查询所得总分、收入详情和支出详情
   * 崔晓鸿，2019年9月12日11:20:11
   *
   * @param userId 用户积分id
   * @return
   */
  //求总分
  IntegralColDetDayModel findCollectIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  //按插件分组求和
  List<IntegralCollectPluginModel> findIncomeCollectIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  List<IntegralCollectPluginModel> findOutcomeCollectIntegral(@Param("userId") String userId,@Param("createTime") String createTime,@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  //不按插件分类
  List<IntegralCollectPluginModel> findIncomeDetailIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  List<IntegralCollectPluginModel> findOutcomeDetailIntegral(@Param("userId") String userId,@Param("createTime") String createTime,@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

  /**
   * 手机端，月积分——通过用户id、日期查询所得总分、收入详情和支出详情、本月和上月情况
   * 崔晓鸿，2019年9月15日10:00:34
   *
   * @param userId 用户积分id
   * @return IntegralCollectMonthModel
   */
  IntegralCollectMonthModel findCollectMonthIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  /**
   * 手机端，月积分——通过用户id、日期查询所得总分、收入详情和支出详情、本月和上月情况
   * 崔晓鸿，2019年9月15日10:00:34
   *
   * @param userId 用户积分id
   * @return List<IntegralDetailMonthModel>
   */
  List<IntegralCollectPluginModel> findIncomeMonthCollectIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  List<IntegralCollectPluginModel> findOutcomeMonthCollectIntegral(@Param("userId") String userId,@Param("createTime") String createTime,@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

  //所有的月积分收入支出详情
  List<IntegralCollectPluginModel> findIncomeMonthCollectIntegralAll(@Param("userId") String userId, @Param("createTime") String createTime);
  List<IntegralCollectPluginModel> findOutcomeMonthCollectIntegralAll(@Param("userId") String userId,@Param("createTime") String createTime);
  /**
   * 手机端，月积分——通过用户id、日期查询本月和上月情况
   * 崔晓鸿，2019年9月15日10:00:34
   *
   * @param userId 用户积分id
   * @return List<IntegralPluginlMonthModel>
   */
  List<IntegralPluginlMonthModel> findLastMonthCollectIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
  List<IntegralPluginlMonthModel> findNowMonthCollectIntegral(@Param("userId") String userId, @Param("createTime") String createTime, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

  /**
   * 手机端，总结分——根据userId、日期查询各插件分别所获得积分和所在部门平均值
   * 崔晓鸿，2019年9月14日14:26:08
   *
   * @param userId 用户积分id
   * @return List<IntegralCollectAllSelfAvgModel>
   */
  List<IntegralCollectAllSelfAvgModel> findCollectSelfIntegral(@Param("userId") String userId, @Param("startTime") String startTime,@Param("endTime") String endTime);
  List<IntegralCollectAllSelfAvgModel> findCollectAvgIntegral(@Param("userId") String userId, @Param("stratTime") String startTime,@Param("endTime") String endTime);


  /**
   * @Description:查询该用户最近联系人(三个)
   * @Param:
   * @return:
   * @Author: 雨田
   * @Date:
   */
  List<IntegralDetailEntity> selectFrequentUserMonth(String userId);
  /**
   * @Description:查询该用户最近联系人(二十)
   * @Param:
   * @return:
   * @Author: 雨田
   * @Date:
   */
  List<IntegralDetailEntity> selectFrequentUsersMonth(String userId);

  Integer insertIntegralDetail(@Param("integralDetailEntityList") List<IntegralDetailEntity> integralDetailEntityList);

  /**
   * 查询当月所有人的积分净收入并数据放到积分月统计表中
   * 赵芬，2019年9月14日14:02:32
   *
   * @param
   * @return
   */
  List<QueryMonthModel> QueryMonthNetIncome();

  /**
   * 查询上月所有人的积分净收入并数据放到积分月统计表中
   * 赵芬，2019年9月14日14:02:32
   *
   * @param
   * @return
   */
  List<QueryMonthModel> QueryLastMonthNetIncome();

  /**
   * 查询当月所有人的积分总收入并数据放到积分月统计表中
   * 赵芬，2019年9月14日14:02:32
   *
   * @param
   * @return
   */
  List<QueryMonthModel> QueryMonthIncome();

  /**
   * 查询当月所有人的积分总支出并数据放到积分月统计表中
   * 赵芬，2019年9月14日14:02:32
   *
   * @param
   * @return
   */
  List<QueryMonthModel> QueryMonthexpenditure();

  /**
   * 查询当月所有人的插件加分并数据放到积分月统计表中
   * 赵芬，2019年9月14日14:02:32
   *
   * @param
   * @return
   */
  List<QueryMonthPluginModel> QueryMonthPlugin();

  /**
   * 查询当月组织机构的的积分总收入并数据放到积分月统计表中
   * 赵芬，2019年9月14日16:07:03
   *
   * @param
   * @return
   */
  List<QueryMonthOrganizationModel> QueryMonthOrganizationIncome();

  /**
   * 查询当月组织机构的的积分总支出并数据放到积分月统计表中
   * 赵芬，2019年9月14日16:07:10
   *
   * @param
   * @return
   */
  List<QueryMonthOrganizationModel> QueryMonthOrganizationExpenditure();

  /**
   * 查询当月组织机构的的积分净收益并数据放到积分月统计表中
   * 赵芬，2019年9月14日16:07:17
   *
   * @param
   * @return
   */
  List<QueryMonthOrganizationModel> QueryMonthOrganizationNetIncome();

  /**
   * 查询当月组织机构的的该组织机构的加分并数据放到积分月统计表中
   * 赵芬，2019年9月14日16:07:20
   *
   * @param
   * @return
   */
  List<QueryMonthOrganizationPluginModel> QueryMonthOrganizationPlugin();

  /**
   * 插入钉钉考勤的加分记录到tik_integral_detail表
   * 赵芬，2019年10月9日11:43:11
   * @return
   */
  int InsertUserYestodayDingIntegralDetail(@Param("DingTodayIntegralDetails") List<DingTodayIntegralDetailModel> DingTodayIntegralDetails);
}
