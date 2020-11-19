package com.tfjybj.integral.model.scoreResult;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DetailBugModel {

    @ApiModelProperty(value = "bug详细类型",required = true)
    private String action;

    @ApiModelProperty(value = "bug详细加分原因",required = true)
    private  String description;

    @ApiModelProperty(value = "bug详细计算结果",required = true)
    private  String result;
}
