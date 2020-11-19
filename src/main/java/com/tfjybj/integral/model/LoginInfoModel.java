package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginInfoModel {

    @ApiModelProperty
    private String id;

    @ApiModelProperty
    private String userCode;

    @ApiModelProperty
    private String email;

    @ApiModelProperty
    private String userName;

    @ApiModelProperty
    private String companyId;

    @ApiModelProperty
    private String schoolNo;

    @ApiModelProperty
    private String token;

    @ApiModelProperty
    private String roleId;
    @ApiModelProperty
    private String qqOpenId;
    @ApiModelProperty
    private String weChatOpenId;
    @ApiModelProperty
    private String wxPlantForm;

}
