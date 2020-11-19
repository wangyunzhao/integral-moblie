package com.tfjybj.integral.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@ApiModel(value="TodayTomatoModel:今日积分和总积分")
@NoArgsConstructor
@Data
@Accessors(chain=true)
@ToString(callSuper=true)
public class TodayTomatoModel {
    private Integer num;
    private String tomatoName;
}
