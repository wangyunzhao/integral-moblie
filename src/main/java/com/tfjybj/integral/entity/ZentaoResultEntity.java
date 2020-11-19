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
 * ZentaoResult实体
 * 禅道积分计算结果表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "ZentaoResultEntity:禅道积分计算结果表")
@Data
@TableName(value = "tiz_zentao_result")
public class ZentaoResultEntity implements Serializable {

	/**
	 * 日志记录id
	 */
    @ApiModelProperty(value = "日志记录id")
	@Column(name = "record_id")
	private String recordId;

	/**
	 * 对象状态
	 */
    @ApiModelProperty(value = "对象状态")
	@Column(name = "action")
	private String action;

	/**
	 * 要加分的人
	 */
    @ApiModelProperty(value = "要加分的人")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 计算结果
	 */
    @ApiModelProperty(value = "计算结果")
	@Column(name = "result")
	private String result;

	/**
	 * 结果描述(加分理由)
	 */
    @ApiModelProperty(value = "结果描述(加分理由)")
	@Column(name = "description")
	private String description;

	/**
	 * 对象id primary_id
	 */
    @ApiModelProperty(value = "对象id primary_id")
	@Column(name = "object_id")
	private Integer objectId;

	/**
	 * 对象类型type_key
	 */
    @ApiModelProperty(value = "对象类型type_key")
	@Column(name = "object_type")
	private String objectType;

	/**
	 * 调用接口是否成功
	 */
    @ApiModelProperty(value = "调用接口是否成功")
	@Column(name = "is_success")
	private Integer isSuccess;

	/**
	 * is_success_message
	 */
    @ApiModelProperty(value = "is_success_message")
	@Column(name = "is_success_message")
	private String isSuccessMessage;

	/**
	 * 调用接口时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "调用接口时间")
	@Column(name = "success_time")
	private Date successTime;


}
