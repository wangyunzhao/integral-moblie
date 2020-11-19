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
 * DetailRecord实体
 * 打卡结果表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "DetailRecordEntity:打卡结果表")
@Data
@TableName(value = "tid_detail_record")
public class DetailRecordEntity implements Serializable {

	/**
	 * 用户积分id
	 */
    @ApiModelProperty(value = "用户积分id")
	@Column(name = "user_ji_fen_id")
	private String userJiFenId;

	/**
	 * user_ding_id
	 */
    @ApiModelProperty(value = "user_ding_id")
	@Column(name = "user_ding_id")
	private String userDingId;

	/**
	 * 计算迟到和早退，基准时间
	 */
    @ApiModelProperty(value = "计算迟到和早退，基准时间")
	@Column(name = "base_check_time")
	private String baseCheckTime;

	/**
	 * user_check_time
	 */
    @ApiModelProperty(value = "user_check_time")
	@Column(name = "user_check_time")
	private String userCheckTime;

	/**
	 * 考勤类型(OnDuty：上班 OffDuty：下班)
	 */
    @ApiModelProperty(value = "考勤类型(OnDuty：上班 OffDuty：下班)")
	@Column(name = "check_type")
	private String checkType;

	/**
	 * 时间结果(Normal：正常 Early：早退; Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到；	)
	 */
    @ApiModelProperty(value = "时间结果(Normal：正常 Early：早退; Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到；	)")
	@Column(name = "time_result")
	private String timeResult;

	/**
	 * 实际得分
	 */
    @ApiModelProperty(value = "实际得分")
	@Column(name = "integral")
	private Integer integral;

	/**
	 * 用来确定该条考勤应该是哪一天的
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "用来确定该条考勤应该是哪一天的")
	@Column(name = "get_list_time")
	private Date getListTime;

	/**
	 * 是否加分(0:未加分,1:已经加分)
	 */
    @ApiModelProperty(value = "是否加分(0:未加分,1:已经加分)")
	@Column(name = "is_success")
	private Integer isSuccess;

	/**
	 * 加分成功时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "加分成功时间")
	@Column(name = "success_time")
	private Date successTime;

	/**
	 * 是否计算（0为计算，1已计算）
	 */
    @ApiModelProperty(value = "是否计算（0为计算，1已计算）")
	@Column(name = "is_calculate")
	private Integer isCalculate;

	/**
	 * 计算时间
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
    @ApiModelProperty(value = "计算时间")
	@Column(name = "calculate_time")
	private Date calculateTime;

	/**
	 * 关联的审批实例id，当该字段非空时，表示打卡记录与请假、加班等审批有关。可以与 获取单个审批数据配合使用
	 */
    @ApiModelProperty(value = "关联的审批实例id，当该字段非空时，表示打卡记录与请假、加班等审批有关。可以与 获取单个审批数据配合使用")
	@Column(name = "proc_inst_id")
	private String procInstId;

	/**
	 * 数据来源(ATM：考勤机;BEACON：IBeacon;DING_ATM：钉钉考勤机;USER：用户打卡;BOSS：老板改签;)
	 */
    @ApiModelProperty(value = "数据来源(ATM：考勤机;BEACON：IBeacon;DING_ATM：钉钉考勤机;USER：用户打卡;BOSS：老板改签;)")
	@Column(name = "source_type")
	private String sourceType;

	/**
	 * 考勤组id
	 */
    @ApiModelProperty(value = "考勤组id")
	@Column(name = "group_id")
	private String groupId;

	/**
	 * 排班id
	 */
    @ApiModelProperty(value = "排班id")
	@Column(name = "plan_id")
	private String planId;

	/**
	 * 打卡记录id
	 */
    @ApiModelProperty(value = "打卡记录id")
	@Column(name = "record_id")
	private String recordId;

	/**
	 * 工作日
	 */
    @ApiModelProperty(value = "工作日")
	@Column(name = "work_date")
	private String workDate;

	/**
	 * 位置结果(Normal：范围内；Outside：范围外；NotSigned：未打卡)
	 */
    @ApiModelProperty(value = "位置结果(Normal：范围内；Outside：范围外；NotSigned：未打卡)")
	@Column(name = "location_result")
	private String locationResult;

	/**
	 * 关联的审批id，当该字段非空时，表示打卡记录与请假、加班等审批有关
	 */
    @ApiModelProperty(value = "关联的审批id，当该字段非空时，表示打卡记录与请假、加班等审批有关")
	@Column(name = "approve_id")
	private String approveId;

	/**
	 * 连续全勤天数
	 */
	@ApiModelProperty(value = "连续全勤天数",required = true)
	@Column(name = "full_time_days")
	private Integer fullTimeDays;

	/**
	 * 本月全勤的总天数
	 */
	@ApiModelProperty(value = "本月全勤的总天数",required = true)
	@Column(name = "sum_full_time_days")
	private Integer sumFullTimeDays;

	/**
	 * 今日是否全勤（0为非全勤,1为全勤）
	 */
    @ApiModelProperty(value = "今日是否全勤（0为非全勤,1为全勤）")
	@Column(name = "is_full_time")
	private Integer isFullTime;


}
