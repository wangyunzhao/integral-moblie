package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * PluginUser实体
 * 插件用户配置表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "PluginUserEntity:插件用户配置表")
@Data
@TableName(value = "tik_plugin_user")
public class PluginUserEntity implements Serializable {

	/**
	 * 插件id
	 */
	@ApiModelProperty(value = "插件id",required = true)
	@Column(name = "plugin_id")
	private String pluginId;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",required = true)
	@Column(name = "user_id")
	private String userId;


}
