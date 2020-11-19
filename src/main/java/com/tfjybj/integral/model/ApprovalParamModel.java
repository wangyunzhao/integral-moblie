package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批参数-曹祥铭-2019年9月15日08:28:11
 */
@Data
public class ApprovalParamModel  implements Serializable {
    /**
     * 审批id
     */
    @ApiModelProperty
    private String id;
    /**
     * 发起人id
     */
    @ApiModelProperty(value = "creator_id", required = true)
    private String creatorId;
    /**
     * 审批人
     */
    @ApiModelProperty(value = "approval_person", required = true)
    private String approvalPerson;
    /**
     * 页码
     */
    @ApiModelProperty(value = "pageNum", required = true)
    private Integer pageNum;
    /**
     * 页大小
     */
    @ApiModelProperty(value = "pageSize", required = true)
    private Integer pageSize;
    /**
     * 审批人id
     */
    @ApiModelProperty(value = "approval_person_id", required = true)
    private String approvalPersonId;
    /**
     * 发起人
     */
    @ApiModelProperty(value = "creator", required = true)
    private String creator;
    /**
     * 审批状态
     */
    @ApiModelProperty
    private Integer approvalStatus;
    /**
     * 加分详情
     */
    @ApiModelProperty
    private List<ApprovalIntegralDetailModel> integralDetailModelList;
    /**
     * 审批意见
     */
    @ApiModelProperty
    private String approvalOpinion;
    //加分详情
    @ApiModelProperty
    private List<ApprovalIntegralDetailModel> approvalIntegralDetailModelList;

    @ApiModelProperty
    private String typeKey;

    @ApiModelProperty
    private String createTime;
}
