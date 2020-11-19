package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HomePluginModel  implements Serializable {

    /**
     * 用户所绑定的插件
     */
    @ApiModelProperty(value = "setPlugin", required = true)
    private List<PluginModel> setPlugin;
}
