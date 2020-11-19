package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Tags实体
 * tags
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "TagsEntity:tags")
@Data
@TableName(value = "tik_tags")
public class TagsEntity implements Serializable {

	/**
	 * 属性名
	 */
    @ApiModelProperty(value = "属性名")
	@Column(name = "name")
	private String name;

	/**
	 * 固定加分
	 */
    @ApiModelProperty(value = "固定加分")
	@Column(name = "integral")
	private Integer integral;


}
