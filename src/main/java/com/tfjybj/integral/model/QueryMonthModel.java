/**
 * @program: integral_mobile_parent
 * @description: 单人月积分汇总
 * @author: 14_赵芬
 * @create: 2019-09-14 14:14
 **/
package com.tfjybj.integral.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class QueryMonthModel implements Serializable {

    @Id
    @Column(name="id")
    private String id;
    //用户的user_id
    private String UserId;
    //用户的月积分汇总(代表：净收入，总收入，总支出）
    private int SumIntegral;
}
