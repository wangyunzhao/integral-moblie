package com.tfjybj.integral.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

import java.io.Serializable;
import java.util.List;

@Data
public class ApprovalLoadingListModel  implements Serializable {
    /**
     * 审批Id
     */
    @ApiModelProperty(value = "id",required = true)
    private String id;
    /**
     * 发起人
     */
    @ApiModelProperty(value = "creator",required = true)
    private String creator;
    /**
     * 操作者
     */
    @ApiModelProperty(value = "operator",required = true)
    private String operator;
    /**
     * 发起时间
     */
    @ApiModelProperty(value = "create_time",required = true)
    private String createTime;
    /**
     * 审批状态
     */
    @ApiModelProperty(value = "approval_status",required = true)
    private Integer approvalStatus;
    /**
     * 审批人
     */
    @ApiModelProperty(value = "approval_person",required = true)
    private String approvalPerson;
    /**
     * 加分类型
     */
    @ApiModelProperty(value = "type_key_name",required = true)
    private String typeKeyName;
    /**
     * 加分详情
     */
    @ApiModelProperty(value = "integral_detail",required = true)
    private String integralDetail;

    @ApiModelProperty(value = "update_time",required = true)
    private String updateTime;

    @ApiModelProperty(value = "approval_opinion",required = true)
    private String approvalOpinion;

    @ApiModelProperty(value = "remark",required = true)
    private String remark;
}