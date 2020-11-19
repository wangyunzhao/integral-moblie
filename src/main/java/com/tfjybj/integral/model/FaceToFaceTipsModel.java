package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 带key的面对面加分model
 * @author: 王云召
 * @create: 2019-09-12 15:59
 **/
@Data
@ToString(callSuper = true)
public class FaceToFaceTipsModel {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "key文本", required = true)
    private String keyText;
}
