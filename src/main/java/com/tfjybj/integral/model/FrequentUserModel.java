package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * FrenquentUserModel
 * 加分排名实体
 * @author 赵雷
 * @since 2019年9月12日20:24:34
 */
@ApiModel(value = "FrenquentUserModel:加分联系实体")
@Data
public class FrequentUserModel implements Serializable {
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "用户姓名")
	private String userName;
}
