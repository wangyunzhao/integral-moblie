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
 * TomatoRecord实体
 * 番茄记录表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "TomatoRecordEntity:番茄记录表")
@Data
@TableName(value = "tit_tomato_record")
public class TomatoRecordEntity implements Serializable {

	/**
	 * 番茄原始id
	 */
	@ApiModelProperty(value = "番茄原始id",required = true)
	@Column(name = "record_id")
	private Long recordId;

	/**
	 * 番茄名称
	 */
    @ApiModelProperty(value = "番茄名称")
	@Column(name = "tomato_name")
	private String tomatoName;

	/**
	 * 番茄开始时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "番茄开始时间")
	@Column(name = "tomato_start_time")
	private Date tomatoStartTime;

	/**
	 * 番茄结束时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "番茄结束时间")
	@Column(name = "tomato_finish_time")
	private Date tomatoFinishTime;

	/**
	 * 番茄时长
	 */
    @ApiModelProperty(value = "番茄时长")
	@Column(name = "tomato_time")
	private Integer tomatoTime;

	/**
	 * 用户id
	 */
    @ApiModelProperty(value = "用户id")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 用户名
	 */
    @ApiModelProperty(value = "用户名")
	@Column(name = "user_name")
	private String userName;

	/**
	 * 加分配置id
	 */
    @ApiModelProperty(value = "加分配置id")
	@Column(name = "config_id")
	private Long configId;

	/**
	 * 分数（符合条件所要加的分数）
	 */
    @ApiModelProperty(value = "分数（符合条件所要加的分数）")
	@Column(name = "integral")
	private Integer integral;

	/**
	 * 调用加分接口是否成功 （失败0   成功1）
	 */
    @ApiModelProperty(value = "调用加分接口是否成功 （失败0   成功1）")
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
	 * 加分成功的信息
	 */
    @ApiModelProperty(value = "加分成功的信息")
	@Column(name = "success_message")
	private String successMessage;


}
