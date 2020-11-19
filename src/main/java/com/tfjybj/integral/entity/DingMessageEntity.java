package com.tfjybj.integral.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dmsdbj.cloud.tool.business.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Evaluate实体
 * 组织月积分表
 *
 * @author 梁佳宝
 * @version
 * @since  2020-08-06 15点22分
 */
@ApiModel(value = "DingMessageEntity:钉钉加分给每人发送消息表")
@Data
@TableName(value = "tik_ding_message")
public class DingMessageEntity extends BaseEntity implements Serializable {

    /**
     * 发送群名称
     */
    @ApiModelProperty(value = "发送群名称")
    @Column(name = "group_name")
    private String groupName;

    /**
     * 群组id
     */
    @ApiModelProperty(value = "群组id")
    @Column(name = "group_id")
    private String groupId;

    /**
     * 被加分人id
     */
    @ApiModelProperty(value = "被加分人id")
    @Column(name = "user_id")
    private String userId;



}
