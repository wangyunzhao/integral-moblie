package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * ZentaoIntegralUser实体
 * 禅道用户与积分用户关联表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "ZentaoIntegralUserEntity:禅道用户与积分用户关联表")
@Data
@TableName(value = "tiz_zentao_integral_user")
public class ZentaoIntegralUserEntity implements Serializable {

	/**
	 * 积分系统用户id
	 */
	@ApiModelProperty(value = "积分系统用户id",required = true)
	@Column(name = "user_id")
	private String userId;

	/**
	 * 禅道用户名
	 */
	@ApiModelProperty(value = "禅道用户名",required = true)
	@Column(name = "account")
	private String account;


}
