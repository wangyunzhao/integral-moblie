package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.entity.MonthIntegralEntity;
import com.tfjybj.integral.model.MonthHashmapModel;
import com.tfjybj.integral.model.QueryMonthModel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MonthIntegralDao接口
 * monthIntegral表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("monthIntegralDao")
public interface MonthIntegralDao{

    /**
     * 向tik_month_integral表中添加当月净收入-赵芬-2019年9月14日19:32:42
     * @param MonthNetIncomes
     */
    int InsertMonthNetIncome( @Param("MonthNetIncomes") List<QueryMonthModel> MonthNetIncomes);

    /**
     * 向tik_month_integral表中更新上月净收入-赵芬-2019年9月14日19:57:45
     * @param LastMonthNetIncomes
     */
    int UpdateLastMonthNetIncome(@Param("LastMonthNetIncomes") List<QueryMonthModel> LastMonthNetIncomes);
    /**
     * 向tik_month_integral表中更新本月总收入-赵芬-2019年9月14日19:57:45
     * @param MonthIncomes
     */
    int UpdateMonthIncome(@Param("MonthIncomes") List<QueryMonthModel> MonthIncomes);
    /**
     * 向tik_month_integral表中更新本月总支出-赵芬-2019年9月14日19:57:45
     * @param Monthexpenditures
     */
    int UpdateMonthexpenditure(@Param("Monthexpenditures") List<QueryMonthModel> Monthexpenditures);
    /**
     * 向tik_month_integral表中更新本月各个插件的加分-赵芬-2019年9月14日19:57:45
     * @param MonthPlugins
     */
    int UpdateMonthPlugin(@Param("MonthPlugins") MonthHashmapModel MonthPlugins);

}
