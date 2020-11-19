package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.itoo.tool.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * IntegralDetail实体
 * 用户积分详情表
 *
 * @author 王云召 
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@ApiModel(value = "IntegralDetailEntity:用户积分详情表")
@Data
@TableName(value = "tik_integral_detail")
public class IntegralDetailEntity implements Serializable {

	/**
	 * 加分人id
	 */
	@ApiModelProperty(value = "加分人id",required = true)
	@Column(name = "giving_user_id")
	private String givingUserId;

	/**
	 * 被加分人id
	 */
	@ApiModelProperty(value = "被加分人id",required = true)
	@Column(name = "user_id")
	private String userId;

	/**
	 * 加分值
	 */
	@ApiModelProperty(value = "加分值",required = true)
	@Column(name = "integral")
	private Integer integral;

	/**
	 * 加分原因
	 */
    @ApiModelProperty(value = "加分原因")
	@Column(name = "reason")
	private String reason;

	/**
	 * 插件id
	 */
	@ApiModelProperty(value = "插件id",required = true)
	@Column(name = "plugin_id")
	private String pluginId;

	/**
	 * 类型key
	 */
	@ApiModelProperty(value = "类型key",required = true)
	@Column(name = "type_key")
	private String typeKey;

	/**
	 * 数据来源主键
	 */
    @ApiModelProperty(value = "数据来源主键")
	@Column(name = "primary_id")
	private String primaryId;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@Column(name = "create_time")
	private String createTime;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	@Column(name = "creator")
	private String creator;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	@Column(name = "id")
	private String id;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@Column(name = "update_time")
	private String updateTime;
	/**
	 * 操作员
	 */
	@ApiModelProperty(value = "操作员")
	@Column(name = "operator")
	private String operator;
	/**
	 * 记录
	 */
	@ApiModelProperty(value = "记录")
	@Column(name = "remark")
	private String remark;

	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	@Column(name = "is_delete")
	private String isDelete;
}
