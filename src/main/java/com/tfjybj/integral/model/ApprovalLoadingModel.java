package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApprovalLoadingModel  implements Serializable {
    /**
     * 进入哪个审批界面的标识（0-发起审批，1-我发起的，2-我审批的）
     */
    @ApiModelProperty
    private Integer priority;
    /**
     * 审批集合
     */
    @ApiModelProperty
    private List<ApprovalIntegralModel> list;
}