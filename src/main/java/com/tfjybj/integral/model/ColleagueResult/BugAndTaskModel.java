package com.tfjybj.integral.model.ColleagueResult;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 项目组内数据汇总
 * @author: 谷浩樟
 * @create: 2019-09-13 09:53
 **/
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class BugAndTaskModel {

    List<TaskModel> taskModel;

    List<BugModel> bugModel;
}
