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
 * @since 2019年9月12日10:59:27
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralColDetDayModel implements Serializable {
    /**
     * 总收益
     */
    @ApiModelProperty(value = "总收益")
    private int sumProfit;

    /**
     * 总收入
     */
    @ApiModelProperty(value = "总收入")
    private int sumIncome;

    /**
     * 总支出
     */
    @ApiModelProperty(value = "总支出")
    private int sumPay;
    /**
     * 收入详情(按插件分类)
     */
    @ApiModelProperty(value = "收入详情(按插件分类)")
    private Set<IntegralCollectPluginModel> incomeList;
    /**
     * 支出详情(按插件分类)
     */
    @ApiModelProperty(value = "支出详情(按插件分类)")
    private Set<IntegralCollectPluginModel> outcomeList;

    /**
     * 收入详情
     */
    @ApiModelProperty(value = "收入详情")
    private List<IntegralCollectPluginModel> incomeDetailList;
    /**
     * 支出详情
     */
    @ApiModelProperty(value = "支出详情")
    private List<IntegralCollectPluginModel> outcomeDetailList;
}
