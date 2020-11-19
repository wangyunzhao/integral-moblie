package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.MonthHashmapModel;
import com.tfjybj.integral.model.QueryMonthOrganizationModel;
import com.tfjybj.integral.model.QueryMonthOrganizationPluginModel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EvaluateDao接口
 * evaluate表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("evaluateDao")
public interface EvaluateDao {

    /**
     * 向tik_month_integral表中添加不同组织的当月总收入-赵芬-2019年9月14日21:11:16
     * @param MonthOrganizationIncomes
     */
    int InsertMonthOrganizationIncome(@Param("MonthOrganizationIncomes") List<QueryMonthOrganizationModel> MonthOrganizationIncomes);

    /**
     * 向tik_month_integral表中更新不同组织的当月总支出-赵芬-2019年9月14日21:11:35
     * @param MonthOrganizationExpenditures
     */
    int UpdateMonthOrganizationExpenditure(List<QueryMonthOrganizationModel> MonthOrganizationExpenditures);
    /**
     * 向tik_month_integral表中更新不同组织的当月净收入-赵芬-2019年9月14日21:12:15
     * @param MonthOrganizationNetIncomes
     */
    int UpdateMonthOrganizationNetIncome(List<QueryMonthOrganizationModel> MonthOrganizationNetIncomes);
    /**
     * 向tik_month_integral表中更新不同组织不同插件本月积分-赵芬-2019年9月14日21:13:33
     * @param MonthOrganizationPlugins
     */
    int UpdateMonthOrganizationPlugin(List<MonthHashmapModel> MonthOrganizationPlugins);

}
