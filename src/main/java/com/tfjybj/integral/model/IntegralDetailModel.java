package com.tfjybj.integral.model;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IntegralDetailModel
 * 积分详情表实体
 * @author 赵雷
 * @since 2019年9月12日20:24:34
 */
@ApiModel(value = "积分详情表实体")
@Data
public class IntegralDetailModel implements Serializable {

	@ApiModelProperty(value = "雪花算法id",hidden = true)
	private String id= IdWorker.getIdStr();

	@ApiModelProperty(value = "赠送积分人",hidden = true)
	private String givingUserId;
	// private String givingUserId= "91T5M5oxNCEef1kLLFtKVo";

	@ApiModelProperty(value = "接收积分人")
	private String userId;

	@ApiModelProperty(value = "赠送积分分值")
	private Integer integral;

	@ApiModelProperty(value = "可赠的积分",hidden = true)
	private Integer givingIntegral=0;

	@ApiModelProperty(value = "自身的积分",hidden = true)
	private Integer selfIntegral=0;

	@ApiModelProperty(value = "赠送原因")
	private String reason="";

	@ApiModelProperty(value = "插件id")
	private String pluginId="kernel-free";

	@ApiModelProperty(value = "插件类型")
	private String typeKey="typeKey_free_self";

	@ApiModelProperty(value = "插件主键id")
	private String primaryId="typeKey_free_self";

	@ApiModelProperty(value = "创建时间",hidden = true)
	private String createTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	@ApiModelProperty(value = "创建人员",hidden = true)
	private String creator;
	// private String creator= "91T5M5oxNCEef1kLLFtKVo";

	@ApiModelProperty(value = "更新时间",hidden = true)
	private String updateTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	@ApiModelProperty(value = "操作人",hidden = true)
	private String operator;
	// private String operator= "91T5M5oxNCEef1kLLFtKVo";
	@ApiModelProperty(value = "记录",hidden = true)
	private String remark="";

	@ApiModelProperty(value = "是否删除",hidden = true)
	private Integer isDelete=0;



}
