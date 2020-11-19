package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;
import java.io.Serializable;

/**
 * @author  崔晓鸿
 * @since 2019年9月11日22:02:13
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DingMonthIntegralModel implements Serializable{
    /**
     * 实际打卡时间
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 本月积分
     */
    @ApiModelProperty(value = "本月积分")
    private String sumIntegral;

    /**
     * 上月积分
     */
    @ApiModelProperty(value = "上月积分")
    private String sumIntegralLast;
}

