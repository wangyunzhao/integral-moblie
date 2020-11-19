/**
 * @program: integral_mobile_parent
 * @description: 单人插件月积分汇总
 * @author: 14_赵芬
 * @create: 2019-09-14 14:14
 **/
package com.tfjybj.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class QueryMonthPluginModel {
    //用户的user_id
    private String UserId;
    //插件名称
    private String PluginId;
    //用户的月积分汇总(代表：净收入，总收入，总支出）
    private int SumIntegral;

}
