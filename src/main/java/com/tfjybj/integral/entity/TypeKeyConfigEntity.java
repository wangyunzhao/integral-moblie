package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * TypeKeyConfig实体
 * typeKey配置表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "TypeKeyConfigEntity:typeKey配置表")
@Data
@TableName(value = "tik_type_key_config")
public class TypeKeyConfigEntity implements Serializable {

	/**
	 * 插件id
	 */
    @ApiModelProperty(value = "插件id")
	@Column(name = "plugin_id")
	private String pluginId;

	/**
	 * type_key类型
	 */
    @ApiModelProperty(value = "type_key类型")
	@Column(name = "type_key")
	private String typeKey;

	/**
	 * 加分原因名称
	 */
    @ApiModelProperty(value = "加分原因名称")
	@Column(name = "name")
	private String name;

	/**
	 * 图片地址
	 */
    @ApiModelProperty(value = "图片地址")
	@Column(name = "image_url")
	private String imageUrl;


}
