package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * @author  崔晓鸿
 * @since 2019年9月12日17:38:31
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralCollectAllSelfAvgModel implements Serializable {
    /**
     * 插件名
     */
    @ApiModelProperty(value = "插件名称")
    private String pluginName;
    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private int integral;

}
