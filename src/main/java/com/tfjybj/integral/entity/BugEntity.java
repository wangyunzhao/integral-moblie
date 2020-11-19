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
 * Bug实体
 * bug
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "BugEntity:bug")
@Data
@TableName(value = "zt_bug")
public class BugEntity implements Serializable {

	/**
	 * product
	 */
	@ApiModelProperty(value = "product",required = true)
	@Column(name = "product")
	private Integer product;

	/**
	 * branch
	 */
	@ApiModelProperty(value = "branch",required = true)
	@Column(name = "branch")
	private Integer branch;

	/**
	 * module
	 */
	@ApiModelProperty(value = "module",required = true)
	@Column(name = "module")
	private Integer module;

	/**
	 * project
	 */
	@ApiModelProperty(value = "project",required = true)
	@Column(name = "project")
	private Integer project;

	/**
	 * plan
	 */
	@ApiModelProperty(value = "plan",required = true)
	@Column(name = "plan")
	private Integer plan;

	/**
	 * story
	 */
	@ApiModelProperty(value = "story",required = true)
	@Column(name = "story")
	private Integer story;

	/**
	 * storyVersion
	 */
	@ApiModelProperty(value = "storyVersion",required = true)
	@Column(name = "storyVersion")
	private Integer storyversion;

	/**
	 * task
	 */
	@ApiModelProperty(value = "task",required = true)
	@Column(name = "task")
	private Integer task;

	/**
	 * toTask
	 */
	@ApiModelProperty(value = "toTask",required = true)
	@Column(name = "toTask")
	private Integer totask;

	/**
	 * toStory
	 */
	@ApiModelProperty(value = "toStory",required = true)
	@Column(name = "toStory")
	private Integer tostory;

	/**
	 * title
	 */
	@ApiModelProperty(value = "title",required = true)
	@Column(name = "title")
	private String title;

	/**
	 * keywords
	 */
	@ApiModelProperty(value = "keywords",required = true)
	@Column(name = "keywords")
	private String keywords;

	/**
	 * severity
	 */
	@ApiModelProperty(value = "severity",required = true)
	@Column(name = "severity")
	private Integer severity;

	/**
	 * pri
	 */
	@ApiModelProperty(value = "pri",required = true)
	@Column(name = "pri")
	private Integer pri;

	/**
	 * type
	 */
	@ApiModelProperty(value = "type",required = true)
	@Column(name = "type")
	private String type;

	/**
	 * os
	 */
	@ApiModelProperty(value = "os",required = true)
	@Column(name = "os")
	private String os;

	/**
	 * browser
	 */
	@ApiModelProperty(value = "browser",required = true)
	@Column(name = "browser")
	private String browser;

	/**
	 * hardware
	 */
	@ApiModelProperty(value = "hardware",required = true)
	@Column(name = "hardware")
	private String hardware;

	/**
	 * found
	 */
	@ApiModelProperty(value = "found",required = true)
	@Column(name = "found")
	private String found;

	/**
	 * steps
	 */
	@ApiModelProperty(value = "steps",required = true)
	@Column(name = "steps")
	private String steps;

	/**
	 * status
	 */
	@ApiModelProperty(value = "status",required = true)
	@Column(name = "status")
	private String status;

	/**
	 * color
	 */
	@ApiModelProperty(value = "color",required = true)
	@Column(name = "color")
	private String color;

	/**
	 * confirmed
	 */
	@ApiModelProperty(value = "confirmed",required = true)
	@Column(name = "confirmed")
	private Integer confirmed;

	/**
	 * activatedCount
	 */
	@ApiModelProperty(value = "activatedCount",required = true)
	@Column(name = "activatedCount")
	private Integer activatedcount;

	/**
	 * mailto
	 */
	@ApiModelProperty(value = "mailto",required = true)
	@Column(name = "mailto")
	private String mailto;

	/**
	 * openedBy
	 */
	@ApiModelProperty(value = "openedBy",required = true)
	@Column(name = "openedBy")
	private String openedby;

	/**
	 * openedDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "openedDate",required = true)
	@Column(name = "openedDate")
	private Date openeddate;

	/**
	 * openedBuild
	 */
	@ApiModelProperty(value = "openedBuild",required = true)
	@Column(name = "openedBuild")
	private String openedbuild;

	/**
	 * assignedTo
	 */
	@ApiModelProperty(value = "assignedTo",required = true)
	@Column(name = "assignedTo")
	private String assignedto;

	/**
	 * assignedDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "assignedDate",required = true)
	@Column(name = "assignedDate")
	private Date assigneddate;

	/**
	 * resolvedBy
	 */
	@ApiModelProperty(value = "resolvedBy",required = true)
	@Column(name = "resolvedBy")
	private String resolvedby;

	/**
	 * resolution
	 */
	@ApiModelProperty(value = "resolution",required = true)
	@Column(name = "resolution")
	private String resolution;

	/**
	 * resolvedBuild
	 */
	@ApiModelProperty(value = "resolvedBuild",required = true)
	@Column(name = "resolvedBuild")
	private String resolvedbuild;

	/**
	 * resolvedDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "resolvedDate",required = true)
	@Column(name = "resolvedDate")
	private Date resolveddate;

	/**
	 * closedBy
	 */
	@ApiModelProperty(value = "closedBy",required = true)
	@Column(name = "closedBy")
	private String closedby;

	/**
	 * closedDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "closedDate",required = true)
	@Column(name = "closedDate")
	private Date closeddate;

	/**
	 * duplicateBug
	 */
	@ApiModelProperty(value = "duplicateBug",required = true)
	@Column(name = "duplicateBug")
	private Integer duplicatebug;

	/**
	 * linkBug
	 */
	@ApiModelProperty(value = "linkBug",required = true)
	@Column(name = "linkBug")
	private String linkbug;

	/**
	 * caseVersion
	 */
	@ApiModelProperty(value = "caseVersion",required = true)
	@Column(name = "caseVersion")
	private Integer caseversion;

	/**
	 * result
	 */
	@ApiModelProperty(value = "result",required = true)
	@Column(name = "result")
	private Integer result;

	/**
	 * testtask
	 */
	@ApiModelProperty(value = "testtask",required = true)
	@Column(name = "testtask")
	private Integer testtask;

	/**
	 * lastEditedBy
	 */
	@ApiModelProperty(value = "lastEditedBy",required = true)
	@Column(name = "lastEditedBy")
	private String lasteditedby;

	/**
	 * lastEditedDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "lastEditedDate",required = true)
	@Column(name = "lastEditedDate")
	private Date lastediteddate;

	/**
	 * deleted
	 */
	@ApiModelProperty(value = "deleted",required = true)
	@Column(name = "deleted")
	private String deleted;


}
