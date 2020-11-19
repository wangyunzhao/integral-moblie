package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.TodayTomatoModel;
import com.tfjybj.integral.model.WeekTomatoModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TomatoRecordDao接口
 * tomatoRecord表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("tomatoRecordDao")
public interface TomatoRecordDao{
    /**
     * 查询登录用户今日的番茄积分和总积分以及番茄数量和名称
     * @author 张文慧
     * @since 2019年9月12日16:34:33
     * @return
     */
    List<TodayTomatoModel> queryHistory(String userId);
    Integer queryToday(String userId);
    Integer queryAll(String userId);

    /**
     * 查询本周和上周的积分情况
     * @author 张文慧
     * @since 2019年9月12日16:34:33
     * @return
     */
    List<WeekTomatoModel> queryWeekIntegral(String userId);
}
