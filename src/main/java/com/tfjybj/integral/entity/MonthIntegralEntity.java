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
 * MonthIntegral实体
 * 每月积分汇总表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "MonthIntegralEntity:每月积分汇总表")
@Data
@TableName(value = "tik_month_integral")
public class MonthIntegralEntity implements Serializable {

	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id")
	@Column(name = "user_id")
	private Integer userId;

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
	 * 每月净收益
	 */
    @ApiModelProperty(value = "每月净收益")
	@Column(name = "month_earmings")
	private String monthEarmings;

	/**
	 * 每月总支出
	 */
    @ApiModelProperty(value = "每月总支出")
	@Column(name = "month_expend")
	private String monthExpend;

	/**
	 * 每月总收入
	 */
    @ApiModelProperty(value = "每月总收入")
	@Column(name = "month_income")
	private String monthIncome;

	/**
	 * 上月总积分
	 */
    @ApiModelProperty(value = "上月总积分")
	@Column(name = "last_month_integral")
	private String lastMonthIntegral;

	/**
	 * 插件加分json
	 */
    @ApiModelProperty(value = "插件加分json")
	@Column(name = "plugin_integral")
	private String pluginIntegral;


}
