package com.tfjybj.integral.model;

import com.tfjybj.integral.model.PluginModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;


/**
 * 首页加载所需的用户信息的model
 */
@Data
public class UserHomeModel  implements Serializable{
    /**
     * 今日积分
     */
    @ApiModelProperty(value = "sumIntegral", required = true)
    private Integer sumIntegral;
    /**
     * 月积分
     */
    @ApiModelProperty(value = "monthearmings", required = true)
    private Integer monthearmings;
    /**
     * 总积分
     */
    @ApiModelProperty(value = "integral", required = true)
    private Integer integral;
    /**
     * 用户所绑定的插件
     */
    @ApiModelProperty(value = "setPlugin", required = true)
    private List<PluginModel> setPlugin;
    /**
     * 是否有未读消息
     */
    @ApiModelProperty(value = "messageCount",required = true)
    private Integer messageCount;
}