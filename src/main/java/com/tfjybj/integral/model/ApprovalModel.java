package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApprovalModel implements Serializable {
    // 审批用的model
    @ApiModelProperty(value = "id",required = true)
    private String id;
    @ApiModelProperty(value = "approval_opinion",required = true)
    private String approvalOpinion;
    @ApiModelProperty(value = "approval_Status",required = true)
    private int approvalStatus;
}
