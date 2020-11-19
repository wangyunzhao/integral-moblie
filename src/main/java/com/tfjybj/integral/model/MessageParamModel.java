package com.tfjybj.integral.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageParamModel implements Serializable {

    @ApiModelProperty(value = "消息id",required = true)
    private String id;
    @ApiModelProperty(value = "消息类型",required = true)
    private int message_type;
    @ApiModelProperty(value = "消息内容",required = true)
    private String message_content;
    @ApiModelProperty(value = "发送者id",required = true)
    private String sender_id;
    @ApiModelProperty(value = "接收者id",required = true)
    private String acceptor_id;
    @ApiModelProperty(value = "是否已读",required = true)
    private int is_read;
    @ApiModelProperty(value = "是否删除",required = true)
    private int is_delete;
    @ApiModelProperty(value = "更新时间",required = true)
    private String update_time;
    @ApiModelProperty(value = "开始时间",required = true)
    private String start_time;
    @ApiModelProperty(value = "结束时间",required = true)
    private String end_time;
    @ApiModelProperty(value = "发起时间",required = true)
    private String create_time;
}
