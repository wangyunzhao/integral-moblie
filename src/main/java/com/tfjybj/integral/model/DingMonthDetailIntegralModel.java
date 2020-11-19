package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;
import java.io.Serializable;

/**
 * @author  崔晓鸿
 * @since 2019年9月11日22:04:51
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DingMonthDetailIntegralModel implements Serializable{

    /**
     * 月分
     */
    @ApiModelProperty(value = "月分")
    private List<DingMonthIntegralModel> month;
    /**
     * 加分详情
     */
    @ApiModelProperty(value = "加分详情")
    private List<DingIntegralDetailModel> everyDay;
}
