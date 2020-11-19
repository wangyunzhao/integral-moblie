package com.tfjybj.integral.model;

import com.tfjybj.integral.entity.EvaluateEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

@Data
public class ApprovalTypeKeyModel implements Serializable {
    @ApiModelProperty(value = "加分类型",required = true)
    private String typeKey;
    @ApiModelProperty(value = "插件名称",required = true)
    private String name;
}
