/**
 * @program: integral_mobile_parent
 * @description: HashMap转为List的Model
 * @author: 14_赵芬
 * @create: 2019-09-15 14:40
 **/
package com.tfjybj.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MonthHashmapModel {

    //user_id 或者 organization_id
    private String UserId;
    //用户的月积分汇总(代表：净收入，总收入，总支出）
    private String pluginJson;
}
