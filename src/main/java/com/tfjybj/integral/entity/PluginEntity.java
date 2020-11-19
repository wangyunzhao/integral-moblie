package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Plugin实体
 * 插件表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "PluginEntity:插件表")
@Data
@TableName(value = "tik_plugin")
public class PluginEntity implements Serializable {

	/**
	 * 插件名字
	 */
    @ApiModelProperty(value = "插件名字")
	@Column(name = "name")
	private String name;

	/**
	 * 插件图标url
	 */
    @ApiModelProperty(value = "插件图标url")
	@Column(name = "icon_url")
	private String iconUrl;

	/**
	 * 插件页面url
	 */
    @ApiModelProperty(value = "插件页面url")
	@Column(name = "page_url")
	private String pageUrl;

	/**
	 * 插件图片url
	 */
    @ApiModelProperty(value = "插件图片url")
	@Column(name = "image_url")
	private String imageUrl;

	/**
	 * 插件描述
	 */
    @ApiModelProperty(value = "插件描述")
	@Column(name = "description")
	private String description;


}
