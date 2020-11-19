package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class MessageModel implements  Serializable {
    /**
     * 消息Id
     */
    @ApiModelProperty
    private String id;
    /**
     * 消息类型
     */
    @ApiModelProperty
    private int message_type;
    /**
     * 消息内容
     */
    @ApiModelProperty
    private String message_content;
    /**
     * 发送者Id
     */
    @ApiModelProperty
    private String sender_id;
    /**
     * 接受者id
     */
    @ApiModelProperty
    private String acceptor_id;
    /**
     * 是否已读（0-未读，1-已读）
     */
    @ApiModelProperty
    private int is_read;
    /**
     * 是否删除
     */
    @ApiModelProperty
    private int is_delete;
    /**
     * 更新时间
     */
    @ApiModelProperty
    private String update_time;
    /**
     * 创建时间
     */
    @ApiModelProperty
    private String create_time;
}
