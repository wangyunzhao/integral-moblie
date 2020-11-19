package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * OrganizationUser实体
 * 组织机构人员表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "OrganizationUserEntity:组织机构人员表")
@Data
@TableName(value = "tc_organization_user")
public class OrganizationUserEntity implements Serializable {

	/**
	 * 组织_id
	 */
    @ApiModelProperty(value = "组织_id")
	@Column(name = "organization_id")
	private String organizationId;

	/**
	 * 用户_id
	 */
    @ApiModelProperty(value = "用户_id")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 租户id 
	 */
    @ApiModelProperty(value = "租户id ")
	@Column(name = "company_id")
	private String companyId;

	/**
	 * 是否激活（0/1 未激活/已激活）
	 */
    @ApiModelProperty(value = "是否激活（0/1 未激活/已激活）")
	@Column(name = "status")
	private Integer status;


}
