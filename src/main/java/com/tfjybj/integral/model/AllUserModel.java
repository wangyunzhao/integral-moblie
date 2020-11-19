package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class AllUserModel {
    @ApiModelProperty(value = "用户编号", required = true)
    private int num;

    @ApiModelProperty(value = "用户id", required = true)
    private String id;

    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;
}
