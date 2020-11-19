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
 * Project实体
 * project
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "ProjectEntity:project")
@Data
@TableName(value = "zt_project")
public class ProjectEntity implements Serializable {

	/**
	 * isCat
	 */
	@ApiModelProperty(value = "isCat",required = true)
	@Column(name = "isCat")
	private String iscat;

	/**
	 * catID
	 */
	@ApiModelProperty(value = "catID",required = true)
	@Column(name = "catID")
	private Integer catid;

	/**
	 * type
	 */
	@ApiModelProperty(value = "type",required = true)
	@Column(name = "type")
	private String type;

	/**
	 * parent
	 */
	@ApiModelProperty(value = "parent",required = true)
	@Column(name = "parent")
	private Integer parent;

	/**
	 * name
	 */
	@ApiModelProperty(value = "name",required = true)
	@Column(name = "name")
	private String name;

	/**
	 * code
	 */
	@ApiModelProperty(value = "code",required = true)
	@Column(name = "code")
	private String code;

	/**
	 * begin
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "begin",required = true)
	@Column(name = "begin")
	private Date begin;

	/**
	 * end
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "end",required = true)
	@Column(name = "end")
	private Date end;

	/**
	 * days
	 */
	@ApiModelProperty(value = "days",required = true)
	@Column(name = "days")
	private Integer days;

	/**
	 * status
	 */
	@ApiModelProperty(value = "status",required = true)
	@Column(name = "status")
	private String status;

	/**
	 * statge
	 */
	@ApiModelProperty(value = "statge",required = true)
	@Column(name = "statge")
	private String statge;

	/**
	 * pri
	 */
	@ApiModelProperty(value = "pri",required = true)
	@Column(name = "pri")
	private String pri;

	/**
	 * desc
	 */
	@ApiModelProperty(value = "desc",required = true)
	@Column(name = "desc")
	private String desc;

	/**
	 * openedBy
	 */
	@ApiModelProperty(value = "openedBy",required = true)
	@Column(name = "openedBy")
	private String openedby;

	/**
	 * openedDate
	 */
	@ApiModelProperty(value = "openedDate",required = true)
	@Column(name = "openedDate")
	private Integer openeddate;

	/**
	 * openedVersion
	 */
	@ApiModelProperty(value = "openedVersion",required = true)
	@Column(name = "openedVersion")
	private String openedversion;

	/**
	 * closedBy
	 */
	@ApiModelProperty(value = "closedBy",required = true)
	@Column(name = "closedBy")
	private String closedby;

	/**
	 * closedDate
	 */
	@ApiModelProperty(value = "closedDate",required = true)
	@Column(name = "closedDate")
	private Integer closeddate;

	/**
	 * canceledBy
	 */
	@ApiModelProperty(value = "canceledBy",required = true)
	@Column(name = "canceledBy")
	private String canceledby;

	/**
	 * canceledDate
	 */
	@ApiModelProperty(value = "canceledDate",required = true)
	@Column(name = "canceledDate")
	private Integer canceleddate;

	/**
	 * PO
	 */
	@ApiModelProperty(value = "PO",required = true)
	@Column(name = "PO")
	private String po;

	/**
	 * PM
	 */
	@ApiModelProperty(value = "PM",required = true)
	@Column(name = "PM")
	private String pm;

	/**
	 * QD
	 */
	@ApiModelProperty(value = "QD",required = true)
	@Column(name = "QD")
	private String qd;

	/**
	 * RD
	 */
	@ApiModelProperty(value = "RD",required = true)
	@Column(name = "RD")
	private String rd;

	/**
	 * team
	 */
	@ApiModelProperty(value = "team",required = true)
	@Column(name = "team")
	private String team;

	/**
	 * acl
	 */
	@ApiModelProperty(value = "acl",required = true)
	@Column(name = "acl")
	private String acl;

	/**
	 * whitelist
	 */
	@ApiModelProperty(value = "whitelist",required = true)
	@Column(name = "whitelist")
	private String whitelist;

	/**
	 * order
	 */
	@ApiModelProperty(value = "order",required = true)
	@Column(name = "order")
	private Integer order;

	/**
	 * deleted
	 */
	@ApiModelProperty(value = "deleted",required = true)
	@Column(name = "deleted")
	private String deleted;


}
