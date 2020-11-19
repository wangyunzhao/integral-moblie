package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

import java.io.Serializable;
import java.util.List;

@Data
public class ApprovalIntegralModel  implements Serializable {
    /**
     * 审批id
     */
    @ApiModelProperty(value = "id", required = true)
    private String  id;
    /**
     * 审批状态(0-未审批，1-通过，2-未通过）
     */
    @ApiModelProperty(value = "approval_status", required = true)
    private Integer approvalStatus;
    /**
     * 审批意见
     */
    @ApiModelProperty(value = "approval_opinion", required = true)
    private String approvalOpinion;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "create_time", required = true)
    private String  createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "update_time", required = true)
    private String updateTime;
    /**
     * 申请人id
     */
    @ApiModelProperty(value = "creator_id", required = true)
    private String creatorId;
    /**
     * 申请人姓名
     */
    @ApiModelProperty(value = "creator", required = true)
    private String creator;
    /**
     * 审批人id
     */
    @ApiModelProperty(value = "approval_person_id", required = true)
    private String approvalPersonId;
    /**
     * 加分类型
     */
    @ApiModelProperty(value = "type_key_name", required = true)
    private String typeKeyName;
    /**
     * 加分详情
     */
    @ApiModelProperty(value = "integral_detail", required = true)
    private String integralDetail;

    @ApiModelProperty
    private List<ApprovalIntegralDetailModel> approvalIntegralDetailModelList;

    @ApiModelProperty(value = "approval_people",required = true)
    private String approvalPerson;

    @ApiModelProperty(value = "operator",required = true)
    private String operator;

    @ApiModelProperty(value = "remark",required = true)
    private String remark;

}