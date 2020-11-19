/**
 * @program: integral_mobile_parent
 * @description: 汇总单人月积分插件加分情况
 * @author: 14_赵芬
 * @create: 2019-09-15 13:45
 **/
package com.tfjybj.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
/*
 *汇总月积分插件加分情况-赵芬-2019年9月15日13:47:16
 */
public class MonthPluginSumModel {
    //用户id
    private String userId;
    //用户的月积分汇总(代表：净收入，总收入，总支出）
    private int SumIntegral;
}
