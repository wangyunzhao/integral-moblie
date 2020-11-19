package com.tfjybj.integral.model;

import com.tfjybj.integral.entity.AllusersEntity;
import lombok.Data;

import java.io.Serializable;


/**
 * @program: integralV3.0-sprint1.0
 * @description: 个人信息
 * @author: 王云召
 * @create: 2019-09-15 08:38
 **/

@Data
public class PersonInfoModel implements Serializable {
    //返回状态码
    private  String code;

    //返回状态信息
    private  String message;

    //返回的实体信息
    private  AllusersEntity data;

}
