/**
 * @program: integralV3.0-sprint1.0
 * @description: 钉钉考勤所有人的今日加分详情表
 * @author: 14_赵芬
 * @create: 2019-10-09 10:39
 **/
package com.tfjybj.integral.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class DingTodayIntegralDetailModel {
    @Id
    private String id;
    //用户ID
    private String userId;
    //加分分值
    private int integral;
    //钉钉加分原因
    private String remark;

    //打卡时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private String dingTime;
}
