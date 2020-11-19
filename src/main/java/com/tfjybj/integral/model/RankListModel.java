package com.tfjybj.integral.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 王梦瑶 on 2019/9/10.
 */
@Data
public class RankListModel implements Serializable {
    private Integer integral;
    private String id;
    private String userName;


    public RankListModel(String id, String userName, Integer integral) {
        this.integral = integral;
        this.id = id;
        this.userName = userName;

    }
}
