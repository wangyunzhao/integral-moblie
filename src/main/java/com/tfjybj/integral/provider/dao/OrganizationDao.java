package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.MonthHashmapModel;
import com.tfjybj.integral.model.QueryMonthOrganizationModel;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;


import java.util.HashMap;

import java.util.List;

/**
 * OrganizationDao接口
 * organization表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("organizationDao")
public interface OrganizationDao{

	/**
	 * 组织结构树-赵雷-2019年9月15日15:49:08
	 * @return
	 */
	List<HashMap> selectOrganization();


	/**
	 * 向表tik_evaluate添加本月不同组织机构的净收入数据-赵芬-2019年9月15日17:27:04
	 * @param MonthOrganizationNetIncomes
	 */
	int InsertMonthOrganizationNetIncome(@Param("MonthOrganizationNetIncomes") List<QueryMonthOrganizationModel> MonthOrganizationNetIncomes);

	/**
	 * 向表tik_evaluate更新本月不同组织的总收入数据-赵芬-2019年9月15日17:37:58
	 * @param MonthOrganizationIncomes
	 * @return
	 */
	int UpdateMonthOrganizationIncome(@Param("MonthOrganizationIncomes") List<QueryMonthOrganizationModel> MonthOrganizationIncomes);

	/**
	 * 向表tik_evaluate更新本月不同组织的总支出数据-赵芬-2019年9月15日17:38:30
	 * @param MonthOrganizationExpenditures
	 * @return
	 */
	int UpdateMonthOrganizationExpenditure(@Param("MonthOrganizationExpenditures") List<QueryMonthOrganizationModel> MonthOrganizationExpenditures);

	/**
	 * 向表tik_evaluate更新本月不同组织的各个插件的加分数据-赵芬-2019年9月15日17:39:56
	 * @param MonthPlugins
	 * @return
	 */
	int UpdateMonthOrganizationPlugin(@Param("MonthPlugins") MonthHashmapModel MonthPlugins);

}
