package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class PluginModel  implements Serializable {
    /**
     * 插件id
     */
    @ApiModelProperty(value = "plugin_id", required = true)
    private String plugin_id;
    /**
     * 插件名称
     */
    @ApiModelProperty(value = "name", required = true)
    private String name;
    /**
     * 插件图标url
     */
    @ApiModelProperty(value = "icon_url", required = true)
    private String icon_url;
}