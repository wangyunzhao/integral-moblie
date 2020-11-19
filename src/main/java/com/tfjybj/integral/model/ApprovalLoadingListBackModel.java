package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApprovalLoadingListBackModel implements Serializable {
    /**
     * 审批Id
     */
    @ApiModelProperty
    private String id;
    /**
     * 发起人
     */
    @ApiModelProperty
    private String creator;
    /**
     * 操作者
     */
    @ApiModelProperty
    private String operator;
    /**
     * 发起时间
     */
    @ApiModelProperty
    private String createTime;
    /**
     * 审批状态
     */
    @ApiModelProperty
    private Integer approvalStatus;
    /**
     * 审批人
     */
    @ApiModelProperty
    private String approvalPerson;
    /**
     * 加分类型
     */
    @ApiModelProperty
    private String typeKeyName;
    /**
     * 加分详情
     */
    @ApiModelProperty
    private List<ApprovalIntegralDetailModel> integralDetail;
}
