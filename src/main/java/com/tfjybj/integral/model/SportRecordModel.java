package com.tfjybj.integral.model;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * SportRecord实体
 * sportRecord
 *
 * @author 董思莲
 * @version 0.0.2
 * @since 0.0.1  2019年9月12日16:06:52
 */
@Data
public class SportRecordModel {
    /**
     * 钉钉userid
     */
//    @ApiModelProperty(value = "钉钉userid")
//    private String userDingId;

    /**
     * 统计的时间，注意时间格式是YYYYMMDD
     */
    @ApiModelProperty(value = "统计的时间，注意时间格式是YYYYMMDD")
    private String statDate;

    /**
     * 步数
     */
    @ApiModelProperty(value = "步数")
    private Integer stepCount;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer integral;


    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 用户ID
     */
//    @ApiModelProperty(value = "用户ID")
//    private String userId;
    /**
     * 用户的头像
     */
//    @ApiModelProperty(value = "用户的头像")
//    private String userImg;

    /**
     * 名次
     */
//    @ApiModelProperty(value = "用户的名次")
//    private Integer ranking;
}
