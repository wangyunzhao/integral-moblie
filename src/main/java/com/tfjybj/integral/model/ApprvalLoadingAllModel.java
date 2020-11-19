package com.tfjybj.integral.model;

import com.tfjybj.integral.model.ApprovalIntegralDetailModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApprvalLoadingAllModel implements Serializable {
    private List<ApprovalIntegralDetailModel> integraldetailModeList;
}
