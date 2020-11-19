package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * TagsUser实体
 * tagsUser
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "TagsUserEntity:tagsUser")
@Data
@TableName(value = "tik_tags_user")
public class TagsUserEntity implements Serializable {

	/**
	 * 用户ID
	 */
    @ApiModelProperty(value = "用户ID")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 属性ID
	 */
    @ApiModelProperty(value = "属性ID")
	@Column(name = "tags_id")
	private String tagsId;


}
