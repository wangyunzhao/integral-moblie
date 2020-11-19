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
public class IntegralCollectAllModel implements Serializable {
    @ApiModelProperty(value = "个人分数")
    private Set<IntegralCollectAllSelfAvgModel> selfintegral;


    @ApiModelProperty(value = "平均分数")
    private Set<IntegralCollectAllSelfAvgModel> avgintegral;
}
