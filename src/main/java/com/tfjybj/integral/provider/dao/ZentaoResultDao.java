package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.ColleagueResult.BugModel;
import com.tfjybj.integral.model.ColleagueResult.TaskModel;
import com.tfjybj.integral.model.scoreResult.DetailBugModel;
import com.tfjybj.integral.model.scoreResult.DetailTaskModel;
import com.tfjybj.integral.model.scoreResult.SumBugModel;
import com.tfjybj.integral.model.scoreResult.SumTaskModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ZentaoResultDao接口
 * zentaoResult表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("zentaoResultDao")
public interface ZentaoResultDao {

    /**
     * 根据用户id，加分时间查询bug汇总信息
     * @param user_id 用户id
     * @param success_time 加分时间
     * @return
     */
    List<SumBugModel> querySumBugByAction(@Param("user_id") String user_id, @Param("success_time")java.sql.Date success_time);

    /**
     * 根据用户id，加分时间查询task汇总信息
     * @param user_id 用户id
     * @param success_time 加分时间
     * @return
     */
    List<SumTaskModel> querySumTaskByAction(@Param("user_id") String user_id, @Param("success_time")java.sql.Date success_time);

    /**
     * 根据用户id，加分时间查询bug详细信息
     * @param user_id 用户id
     * @param success_time 加分时间
     * @return
     */
    List<DetailBugModel> queryDetailBugByAction(@Param("user_id") String user_id, @Param("success_time")java.sql.Date success_time);

    /**
     * 根据用户id，加分时间查询task详细信息
     * @param user_id 用户id
     * @param success_time 加分时间
     * @return
     */
    List<DetailTaskModel> queryDetailTaskByAction(@Param("user_id") String user_id, @Param("success_time")java.sql.Date success_time);

    /**
     * 根据用户id，加分时间查询所在项目成员完成Bug数据
     * @param user_id 用户id
     * @return
     */
    List<BugModel> selectColleagueBugByDate(@Param("user_id") String user_id);

    /**
     * 根据用户id，加分时间查询所在项目成员完成Task数据
     * @param user_id 用户id
     * @return
     */
    List<TaskModel> selectColleagueTaskByDate(@Param("user_id") String user_id);

}
