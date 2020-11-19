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
 * IntegralCollect实体
 * 每日积分汇总表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "IntegralCollectEntity:每日积分汇总表")
@Data
@TableName(value = "tik_integral_collect")
public class IntegralCollectEntity implements Serializable {

	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 当天日期
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "当天日期")
	@Column(name = "now_date")
	private Date nowDate;

	/**
	 * 插件id
	 */
    @ApiModelProperty(value = "插件id")
	@Column(name = "plugin_id")
	private String pluginId;

	/**
	 * 今日得分
	 */
    @ApiModelProperty(value = "今日得分")
	@Column(name = "today_integral")
	private Integer todayIntegral;

	/**
	 * 当前总分
	 */
    @ApiModelProperty(value = "当前总分")
	@Column(name = "now_integral")
	private Integer nowIntegral;


}
