package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Team实体
 * team
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "TeamEntity:team")
@Data
@TableName(value = "zt_team")
public class TeamEntity implements Serializable {

	/**
	 * project
	 */
	@ApiModelProperty(value = "project",required = true)
	@Column(name = "project")
	private Integer project;

	/**
	 * account
	 */
	@ApiModelProperty(value = "account",required = true)
	@Column(name = "account")
	private String account;

	/**
	 * role
	 */
	@ApiModelProperty(value = "role",required = true)
	@Column(name = "role")
	private String role;

	/**
	 * join
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "join",required = true)
	@Column(name = "join")
	private Date join;

	/**
	 * days
	 */
	@ApiModelProperty(value = "days",required = true)
	@Column(name = "days")
	private Integer days;

	/**
	 * hours
	 */
	@ApiModelProperty(value = "hours",required = true)
	@Column(name = "hours")
	private Float hours;


}
