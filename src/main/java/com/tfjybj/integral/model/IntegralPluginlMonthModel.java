package com.tfjybj.integral.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  崔晓鸿
 * @since 2019年9月13日17:49:17
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralPluginlMonthModel implements Serializable {
    /**
     * 创建时间create_time
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间create_time")
    private Date createTime;

    /**
     * 自由加分
     */
    @ApiModelProperty(value = "自由加分")
    private int free;

    /**
     * 活动
     */
    @ApiModelProperty(value = "活动")
    private int activity;

    /**
     * 头脑风暴
     */
    @ApiModelProperty(value = "头脑风暴")
    private int sumIncome;
    /**
     * 钉钉
     */
    @ApiModelProperty(value = "钉钉")
    private int brainstorm;
    /**
     * 游戏
     */
    @ApiModelProperty(value = "游戏")
    private int dingtalk;
    /**
     * 今目标
     */
    @ApiModelProperty(value = "今目标")
    private int game;
    /**
     * 积分系统
     */
    @ApiModelProperty(value = "积分系统")
    private int jingoal;
    /**
     * 图书馆
     */
    @ApiModelProperty(value = "图书馆")
    private int kernel;
    /**
     * 抽奖
     */
    @ApiModelProperty(value = "抽奖")
    private int library;
    /**
     * 总收入
     */
    @ApiModelProperty(value = "总收入")
    private int lottery;
    /**
     * 蓝墨云班课
     */
    @ApiModelProperty(value = "蓝墨云班课")
    private int mosoteach;

    /**
     * 绩效考评
     */
    @ApiModelProperty(value = "绩效考评")
    private int performance;

    /**
     * 番茄
     */
    @ApiModelProperty(value = "番茄")
    private int tomato;

    /**
     * 培养计划
     */
    @ApiModelProperty(value = "培养计划")
    private int training;

    /**
     * 禅道
     */
    @ApiModelProperty(value = "禅道")
    private int zentao;

}
