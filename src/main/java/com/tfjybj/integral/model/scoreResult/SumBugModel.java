package com.tfjybj.integral.model.scoreResult;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class SumBugModel {

    private String action;

    private  String result;

}
