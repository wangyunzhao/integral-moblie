package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 面对面加分人物model
 * @author: 王云召
 * @create: 2019-09-12 15:55
 **/
@Data
@ToString(callSuper = true)
public class FaceToFaceModel {

    @ApiModelProperty(value = "用户id", required = true)
    private String id;

    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;


}
