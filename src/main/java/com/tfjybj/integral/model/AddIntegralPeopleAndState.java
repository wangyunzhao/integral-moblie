package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 加分人姓名和加分状态
 * @author: 王云召
 * @create: 2019-09-26 12:09
 **/
@Data
@ToString(callSuper = true)
public class AddIntegralPeopleAndState {
    @ApiModelProperty(value = "用户id", required = true)
    private String name;

    @ApiModelProperty(value = "用户姓名", required = true)
    private Integer state;

    @ApiModelProperty(value = "加分分值", required = true)
    private Integer integral;

    @ApiModelProperty(value = "被加分人员", required = true)
    List<String> users;
}
