package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * FrenquentUserModel
 * 加分排名实体
 * @author 赵雷
 * @since 2019年9月12日20:24:34
 */
@ApiModel(value = "FrenquentUserModel:加分联系实体")
@Data
public class OrganizaitonsInPartlyCompany implements Serializable {


	@ApiModelProperty(value = "组织id")
	private String id;

	@ApiModelProperty(value = "组织名称")
	private String name;

	@ApiModelProperty(value = "组织包含人员")
	private List<FaceToFaceModel> stringList;

}
