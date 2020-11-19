package com.tfjybj.integral.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


@ApiModel(value="TomatoIntegralModel:返回的番茄信息")
@NoArgsConstructor
@Data
@Accessors(chain=true)
@ToString(callSuper=true)

/**
* @Description: 返回的番茄信息
* @Param:
* @return:
* @Author: 张文慧
* @Date: 2019/9/15
*/
public class TomatoIntegralModel {
    private Integer todayIntegral;
    private Integer sumIntegral;
    private List<TodayTomatoModel> todayTomatoList;
}
