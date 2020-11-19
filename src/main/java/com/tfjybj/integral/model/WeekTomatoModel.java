package com.tfjybj.integral.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


@ApiModel(value="WeekTomatoModel:返回的-周-番茄信息")
@NoArgsConstructor
@Data
@Accessors(chain=true)
@ToString(callSuper=true)

/**
* @Description:  返回的-周-番茄信息
* @Param:
* @return:
* @Author: 张文慧
* @Date: 2019/9/15
*/
public class WeekTomatoModel {
    private Integer thisIntegral;
    private  Integer beforeIntegral;
    private String week;
}
