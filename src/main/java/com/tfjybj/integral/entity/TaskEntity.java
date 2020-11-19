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
 * Task实体
 * task
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "TaskEntity:task")
@Data
@TableName(value = "zt_task")
public class TaskEntity implements Serializable {

	/**
	 * project
	 */
	@ApiModelProperty(value = "project",required = true)
	@Column(name = "project")
	private Integer project;

	/**
	 * module
	 */
	@ApiModelProperty(value = "module",required = true)
	@Column(name = "module")
	private Integer module;

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
	 * fromBug
	 */
	@ApiModelProperty(value = "fromBug",required = true)
	@Column(name = "fromBug")
	private Integer frombug;

	/**
	 * name
	 */
	@ApiModelProperty(value = "name",required = true)
	@Column(name = "name")
	private String name;

	/**
	 * type
	 */
	@ApiModelProperty(value = "type",required = true)
	@Column(name = "type")
	private String type;

	/**
	 * pri
	 */
	@ApiModelProperty(value = "pri",required = true)
	@Column(name = "pri")
	private Integer pri;

	/**
	 * estimate
	 */
	@ApiModelProperty(value = "estimate",required = true)
	@Column(name = "estimate")
	private Float estimate;

	/**
	 * consumed
	 */
	@ApiModelProperty(value = "consumed",required = true)
	@Column(name = "consumed")
	private Float consumed;

	/**
	 * left
	 */
	@ApiModelProperty(value = "left",required = true)
	@Column(name = "left")
	private Float left;

	/**
	 * deadline
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "deadline",required = true)
	@Column(name = "deadline")
	private Date deadline;

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
	 * mailto
	 */
	@ApiModelProperty(value = "mailto",required = true)
	@Column(name = "mailto")
	private String mailto;

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
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "openedDate",required = true)
	@Column(name = "openedDate")
	private Date openeddate;

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
	 * estStarted
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "estStarted",required = true)
	@Column(name = "estStarted")
	private Date eststarted;

	/**
	 * realStarted
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "realStarted",required = true)
	@Column(name = "realStarted")
	private Date realstarted;

	/**
	 * finishedBy
	 */
	@ApiModelProperty(value = "finishedBy",required = true)
	@Column(name = "finishedBy")
	private String finishedby;

	/**
	 * finishedDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "finishedDate",required = true)
	@Column(name = "finishedDate")
	private Date finisheddate;

	/**
	 * canceledBy
	 */
	@ApiModelProperty(value = "canceledBy",required = true)
	@Column(name = "canceledBy")
	private String canceledby;

	/**
	 * canceledDate
	 */
	@JsonFormat(
        pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8"
    )
	@ApiModelProperty(value = "canceledDate",required = true)
	@Column(name = "canceledDate")
	private Date canceleddate;

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
	 * closedReason
	 */
	@ApiModelProperty(value = "closedReason",required = true)
	@Column(name = "closedReason")
	private String closedreason;

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
