package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Evaluate实体
 * 组织月积分表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "EvaluateEntity:组织月积分表")
@Data
@TableName(value = "tik_evaluate")
public class EvaluateEntity implements Serializable {

	/**
	 * 组织机构id
	 */
    @ApiModelProperty(value = "组织机构id")
	@Column(name = "organization_id")
	private String organizationId;

	/**
	 * 月份
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "月份")
	@Column(name = "month")
	private Date month;

	/**
	 * 部门插件加分
	 */
    @ApiModelProperty(value = "部门插件加分")
	@Column(name = "plugin_integral")
	private String pluginIntegral;

	/**
	 * 每月总收入
	 */
    @ApiModelProperty(value = "每月总收入")
	@Column(name = "month_income")
	private Integer monthIncome;

	/**
	 * 每月总支出
	 */
    @ApiModelProperty(value = "每月总支出")
	@Column(name = "month_expend")
	private Integer monthExpend;

	/**
	 * 每月净收益
	 */
    @ApiModelProperty(value = "每月净收益")
	@Column(name = "month_earmings")
	private Integer monthEarmings;


}
