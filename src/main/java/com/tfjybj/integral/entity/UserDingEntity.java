package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * UserDing实体
 * 钉钉用户表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "UserDingEntity:钉钉用户表")
@Data
@TableName(value = "tid_user_ding")
public class UserDingEntity implements Serializable {

	/**
	 * 钉钉userid
	 */
    @ApiModelProperty(value = "钉钉userid")
	@Column(name = "user_ding_id")
	private String userDingId;

	/**
	 * 手机号
	 */
    @ApiModelProperty(value = "手机号")
	@Column(name = "user_code")
	private String userCode;


}
