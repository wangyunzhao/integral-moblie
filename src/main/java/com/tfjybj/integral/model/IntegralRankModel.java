package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 王梦瑶
 * @DESCRIPTION 加分排名实体
 * @create 2019/9/13
*/
//@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IntegralRankModel:加分排名实体")
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class IntegralRankModel implements Serializable {
//    @ApiModelProperty(value = "用户id")
//    private String userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
//    @ApiModelProperty(value = "部门名称")
//    private String orgName;
//    @ApiModelProperty(value = "部门id")
//    private String orgId;
//
//    @ApiModelProperty(value = "开始时间")
//    private String startDate;
//    @ApiModelProperty(value = "结束时间")
//    private String endDate;

    @ApiModelProperty(value = "积分")
    private Integer integral;
    @ApiModelProperty(value = "排名")
    private Integer sort;
}
