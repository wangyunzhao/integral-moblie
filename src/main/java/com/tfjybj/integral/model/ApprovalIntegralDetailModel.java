package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class ApprovalIntegralDetailModel implements Serializable {
    /**
     * Userid用户id
     */
    @ApiModelProperty
    private String userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty
    private String userName;
    /**
     * 所加分值
     */
    @ApiModelProperty
    private Integer integral;
}
