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
 * @since 2019年9月12日11:05:01
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralCollectPluginModel implements Serializable {
    /**
     * 插件名
     */
    @ApiModelProperty(value = "插件名")
    private String pluginName;

    /**
     * 总分
     */
    @ApiModelProperty(value = "总分")
    private int sumIntegral;

    /**
     * 创建时间create_time
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间create_time")
    private Date createTime;
}
