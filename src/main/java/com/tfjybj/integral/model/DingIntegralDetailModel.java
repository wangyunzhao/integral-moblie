package com.tfjybj.integral.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  崔晓鸿
 * @since 2019年9月10日20:30:35
 */
@Data
public class DingIntegralDetailModel implements Serializable{
    /**
     * 时间结果(Normal：正常 Early：早退; Late：迟到;SeriousLate：严重迟到；Absenteeism：旷工迟到； )
     */
    @ApiModelProperty(value = "时间结果")
    private String timeResult;

    /**
     * 实际打卡时间
     */
    @ApiModelProperty(value = "实际打卡时间")
    private String userCheckTime;

    /**
     * 实际得分
     */
    @ApiModelProperty(value = "实际得分")
    private int integral;
}
