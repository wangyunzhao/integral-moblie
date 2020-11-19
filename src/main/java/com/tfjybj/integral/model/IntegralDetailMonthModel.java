package com.tfjybj.integral.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  崔晓鸿
 * @since 2019年9月13日17:51:17
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralDetailMonthModel implements Serializable {
    /**
     * 创建时间create_time
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间create_time")
    private Date createTime;
    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private int integral;

    /**
     * 加分描述
     */
    @ApiModelProperty(value = "加分描述")
    private String reason;

}
