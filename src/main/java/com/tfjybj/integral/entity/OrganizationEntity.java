package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Organization实体
 * 组织机构表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "OrganizationEntity:组织机构表")
@Data
@TableName(value = "tc_organization")
public class OrganizationEntity implements Serializable {

	/**
	 * 钉钉id
	 */
    @ApiModelProperty(value = "钉钉id")
	@Column(name = "ding_id")
	private String dingId;

	/**
	 * 组织机构名称
	 */
    @ApiModelProperty(value = "组织机构名称")
	@Column(name = "organization_name")
	private String organizationName;

	/**
	 * 节点顺序
	 */
    @ApiModelProperty(value = "节点顺序")
	@Column(name = "node_order")
	private String nodeOrder;

	/**
	 * 父节点
	 */
    @ApiModelProperty(value = "父节点")
	@Column(name = "p_id")
	private String pId;

	/**
	 * 钉钉pid
	 */
    @ApiModelProperty(value = "钉钉pid")
	@Column(name = "ding_p_id")
	private String dingPId;

	/**
	 * 租户id
	 */
    @ApiModelProperty(value = "租户id")
	@Column(name = "company_id")
	private String companyId;


}
