package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginResultModel {
    @ApiModelProperty
    private String code;

    @ApiModelProperty
    private String message;

    @ApiModelProperty
    private LoginInfoModel data;
}
