package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class TypeKeyModel implements Serializable {
    @ApiModelProperty(value = "加分类型",required = true)
    private String value;
    @ApiModelProperty(value = "插件名称",required = true)
    private String text;
}
