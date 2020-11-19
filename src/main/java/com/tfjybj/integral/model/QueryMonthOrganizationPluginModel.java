/**
 * @program: integral_mobile_parent
 * @description: 组织机构插件月积分汇总
 * @author: 14_赵芬
 * @create: 2019-09-14 14:16
 **/
package com.tfjybj.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@NoArgsConstructor
public class QueryMonthOrganizationPluginModel implements Serializable {


    //组织结构id
    private String organizationId;
    //插件名称
    private String PluginId;
    //用户的月积分汇总(代表：净收入，总收入，总支出）
    private int SumIntegral;
}
