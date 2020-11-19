package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * ApprovalIntegral实体
 * 审批加分审批表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "ApprovalIntegralEntity:审批加分审批表")
@Data
@TableName(value = "tik_approval_integral")
public class ApprovalIntegralEntity implements Serializable {
	/**
	 * 审批id
	 */
	@ApiModelProperty(value = "审批id",required = true)
	@Column(name = "id")
	private String id;

	/**
	 * 审批状态（0未审批；1已通过；2未通过；3已撤回）
	 */
	@ApiModelProperty(value = "审批状态（0未审批；1已通过；2未通过；3已撤回）",required = true)
	@Column(name = "approval_status")
	private String approvalStatus;

	/**
	 * 审批意见
	 */
    @ApiModelProperty(value = "审批意见")
	@Column(name = "approval_opinion")
	private String approvalOpinion;

	/**
	 * 工作流id
	 */
    @ApiModelProperty(value = "工作流id")
	@Column(name = "work_flow_id")
	private String workFlowId;

	/**
	 * 创建人id
	 */
    @ApiModelProperty(value = "创建人id")
	@Column(name = "creator_id")
	private String creatorId;

	/**
	 * 审批人id
	 */
    @ApiModelProperty(value = "审批人id")
	@Column(name = "approval_person_id")
	private String approvalPersonId;

	/**
	 * 审批人
	 */
    @ApiModelProperty(value = "审批人")
	@Column(name = "approval_person")
	private String approvalPerson;

	/**
	 * 加分类别
	 */
    @ApiModelProperty(value = "加分类别")
	@Column(name = "type_key_name")
	private String typeKeyName;

	/**
	 * 分数详情
	 */
    @ApiModelProperty(value = "分数详情")
	@Column(name = "integral_detail")
	private String integralDetail;


    @ApiModelProperty(value="申请人")
	@Column(name ="creator")
	private String creator;

    /**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	@Column(name = "remark")
	private String remark;
}
