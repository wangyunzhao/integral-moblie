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
 * SportRecord实体
 * sportRecord
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "SportRecordEntity:sportRecord")
@Data
@TableName(value = "tid_sport_record")
public class SportRecordEntity implements Serializable {

	/**
	 * 钉钉userid
	 */
    @ApiModelProperty(value = "钉钉userid")
	@Column(name = "user_ding_id")
	private String userDingId;

	/**
	 * 用户ID
	 */
    @ApiModelProperty(value = "用户ID")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 统计的时间
	 */
    @ApiModelProperty(value = "统计的时间")
	@Column(name = "stat_date")
	private String statDate;

	/**
	 * 步数
	 */
    @ApiModelProperty(value = "步数")
	@Column(name = "step_count")
	private Integer stepCount;

	/**
	 * 相加的分数
	 */
    @ApiModelProperty(value = "相加的分数")
	@Column(name = "integral")
	private Integer integral;

	/**
	 * 是否成功
	 */
    @ApiModelProperty(value = "是否成功")
	@Column(name = "is_success")
	private Integer isSuccess;

	/**
	 * 加分成功的时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "加分成功的时间")
	@Column(name = "success_time")
	private Date successTime;

	/**
	 * 是否计算(加分的时候使用)
	 */
    @ApiModelProperty(value = "是否计算(加分的时候使用)")
	@Column(name = "is_calculate")
	private Integer isCalculate;


}
