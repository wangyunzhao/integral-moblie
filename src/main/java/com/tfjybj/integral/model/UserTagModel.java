package com.tfjybj.integral.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 用户角色信息
 * @author: 王云召
 * @create: 2019-09-14 21:20
 **/
@Data
public class UserTagModel implements Serializable {
    /**
     * 用户姓名
     * */
    private String name;

    /**
     * 用户职位
     * */
    private String position;

    /**
     * 用户code
     * */
    private String userCode;

    /**
     * 用户邮箱
     * */
    private String email;
}
