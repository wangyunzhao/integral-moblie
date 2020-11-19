package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 钉钉运动所有人的加分记录
 * @author: 赵雷
 * @create: 2019-10-09 10:32
 **/
@ApiModel(value = "积分详情表实体")
@Data
public class DingSportRecordModel implements Serializable {

	@ApiModelProperty(value = "被加分人ID")
	private String userId;

	@ApiModelProperty(value = "加分值")
	private Integer integral;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "创建人")
	private String creator;

	@ApiModelProperty(value = "更新时间")
	private String updateTime;

	@ApiModelProperty(value = "操作人")
	private String opterator;

}
