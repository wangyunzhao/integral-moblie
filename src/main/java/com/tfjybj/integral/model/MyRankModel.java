package com.tfjybj.integral.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 王梦瑶 on 2019/9/10.
 */
@Data
public class MyRankModel implements Serializable {
    private String myName;
    private Integer myIntegral;
    private Integer myRank;
    private List<RankListModel> rankListModels;
}
