package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.MonthPluginSumModel;
import org.springframework.stereotype.Repository;

/**
 * ZtUserDao接口
 * ztUser表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("ztUserDao")
public interface ZtUserDao{
    /**
     * 将用户的钉钉数据更新用户的总积分字段
     * @return
     */
    int UpdateTotalSummary(MonthPluginSumModel monthPluginSumModel);

}
