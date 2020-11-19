package com.tfjybj.integral.model;

import lombok.Data;

import java.util.List;

/**
 * @program:integral_mobile
 * @description:百分比
 * @author:张文慧
 * @create:2019-09-18 10:03
 **/
@Data
public class TomatoPercentModel {
    private String percent;
    private String status;
    private List<WeekTomatoModel> weekTomatoList;
}
