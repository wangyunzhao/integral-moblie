package com.tfjybj.integral.model.ColleagueResult;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * @program: integralV3.0-sprint1.0
 * @description: 项目组内Task数据
 * @author: 谷浩樟
 * @create: 2019-09-13 09:44
 **/
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class TaskModel {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * task数量
     */
    private String number;
}
