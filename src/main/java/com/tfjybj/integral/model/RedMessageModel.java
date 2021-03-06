package com.tfjybj.integral.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author 马珂
 * @version 1.0
 * @date 2020/8/8 17:18
 * @describe
 */
@ApiModel(value = "MessageEntity:私信表")
@Data
@TableName(value = "tik_message")
public class RedMessageModel  implements Serializable {
    @Id
    @Column(name="id")
    private String id;
    /**
     * 类型(0系统更新通知，1加分通知，2升级通知)
     */
    @ApiModelProperty(value = "类型(0系统更新通知，1加分通知，2升级通知)",required = true)
    @Column(name = "message_type")
    private Integer messageType;

    /**
     * 具体内容
     */
    @ApiModelProperty(value = "具体内容",required = true)
    @Column(name = "message_content")
    private String messageContent;

    /**
     * 发送者id
     */
    @ApiModelProperty(value = "发送者id",required = true)
    @Column(name = "sender_id")
    private String senderId;

    /**
     * 接收者id
     */
    @ApiModelProperty(value = "接收者id",required = true)
    @Column(name = "acceptor_id")
    private String acceptorId;

    /**
     * 时效(开始时间)
     */
    @ApiModelProperty(value = "时效(开始时间)",required = true)
    @Column(name = "start_time")
    private String startTime;

    /**
     * 时效(有效结束时间)
     */
    @ApiModelProperty(value = "时效(有效结束时间)",required = true)
    @Column(name = "end_time")
    private String endTime;

    /**
     * 是否阅读
     */
    @ApiModelProperty(value = "是否阅读",required = true)
    @Column(name = "is_read")
    private Integer isRead;
    @ApiModelProperty(value = "更新时间",required = true)
    @Column(name = "update_time")
    private String updateTime;
    @ApiModelProperty(value = "操作人",required = true)
    @Column(name = "operator")
    private String operator;
    @ApiModelProperty(value = "说明",required = true)
    @Column(name = "")
    private String remark;
    @ApiModelProperty(value = "是否删除",required = true)
    @Column(name = "is_delete")
    private int isDelete;

    @ApiModelProperty(value = "加减分值",required = true)
    @Column(name = "integral")
    private int integral;

    @ApiModelProperty(value = "发送人姓名")
    @Column(name = "user_name")
    private String userName;
}

