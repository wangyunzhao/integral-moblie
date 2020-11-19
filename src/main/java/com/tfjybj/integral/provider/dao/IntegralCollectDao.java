package com.tfjybj.integral.provider.dao;

import org.springframework.stereotype.Repository;

/**
 * IntegralCollectDao接口
 * integralCollect表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("integralCollectDao")
public interface IntegralCollectDao {
    /**
     * 根据用户id查询用户本月所获得的积分-曹祥铭-2019年9月12日15:14:44
     * @param userId  用户id
     * @return
     */
	int queryUserMonthIntegralByUserId(String userId);

    /**
     * 根据用户id 查询本月所支出的积分-曹祥铭-2019-10-8 10:18:51
     * @param userId
     * @return
     */
    Integer queryUserMonthIntegralOutByUserId(String userId);
}

