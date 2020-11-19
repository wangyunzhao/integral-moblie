package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author  崔晓鸿
 * @since 2019年9月12日17:38:31
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralColDetDayPayModel implements Serializable {
    @ApiModelProperty(value = "支出详情(按插件分类)")
    private Set<IntegralCollectPluginModel> expendList;

    /**
     * 支出详情
     */
    @ApiModelProperty(value = "支出详情")
    private List<IntegralCollectPluginModel> expendDetailList;
}
