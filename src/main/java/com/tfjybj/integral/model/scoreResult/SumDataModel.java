package com.tfjybj.integral.model.scoreResult;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class SumDataModel {

    private List<SumTaskModel> sumTaskSet;

    private List<SumBugModel> sumBugSet;



}
