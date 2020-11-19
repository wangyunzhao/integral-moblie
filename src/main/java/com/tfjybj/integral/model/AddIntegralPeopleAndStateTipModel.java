package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class AddIntegralPeopleAndStateTipModel {
    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;

    @ApiModelProperty(value = "加分状态", required = true)
    private Integer state;

    @ApiModelProperty(value = "key值", required = true)
    private String textKey;

    @ApiModelProperty(value = "加分分值", required = true)
    private Integer integral;

    @ApiModelProperty(value = "被加分人员", required = true)
    List<String> users;
}
