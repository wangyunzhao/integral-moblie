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
 * @since 2019年9月10日20:03:40
 */

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DingIntegralModel implements Serializable{
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private int total;
    /**
     * 加分详情
     */
    @ApiModelProperty(value = "加分详情")
    private List<DingIntegralDetailModel> detail;
}
