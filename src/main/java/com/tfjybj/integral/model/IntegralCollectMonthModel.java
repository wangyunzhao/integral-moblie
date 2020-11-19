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
 * @since 2019年9月13日17:51:17
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralCollectMonthModel implements Serializable {

    /**
     * 总收入
     */
    @ApiModelProperty(value = "总收入")
    private int monthincome;

    /**
     * 总支出
     */
    @ApiModelProperty(value = "总支出")
    private int monthexpend;

    /**
     * 总收益
     */
    @ApiModelProperty(value = "总收益")
    private int monthearmings;

    /**
     * 收入详情
     */
    @ApiModelProperty(value = "收入详情")
    //private Set<IntegralDetailMonthModel> income;
    private List<IntegralCollectPluginModel> income;
    /**
     * 支出详情
     */
    @ApiModelProperty(value = "支出详情")
    //private Set<IntegralDetailMonthModel> expend;
    private List<IntegralCollectPluginModel> expend;

    /**
     * 上个月各插件分
     */
    @ApiModelProperty(value = "上个月各插件分")
    private Set<IntegralPluginlMonthModel> lastMonth;
    /**
     * 本月各插件分
     */
    @ApiModelProperty(value = "本个月各插件分")
    private Set<IntegralPluginlMonthModel> nowMonth;
}
