package com.tfjybj.integral.provider.dao;

import feign.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 更新已有数据tik_user表和插入没有的数据到tik_user表
 * 梁佳宝 2020年6月13日10点33分
 */
@Repository("syncUserDao")
public interface SyncUserDao {
    /**
     * 根据school_no去更新tik_user表
     * @param schoolNo
     * @return
     */
    void updateTikBySchoolNo(@Param("schoolNo") String schoolNo);


    /**
     * 插入数据到tik_user表
     * @param schoolNo
     * @return
     */
    void insertTikUser(@Param("schoolNo") String schoolNo);

    /**
     * 更新delete
     */
    void updateIsDelete();

    /**
     * 获取到没有用的人员id
     * @return
     */
    List<String> selectUselessIsdelete();

    /**
     * 更新没有用的人员通过id
     * @param uselessPersonlist
     */
    void updateUselessIsdelete(@Param("uselessPersonlist") List<String> uselessPersonlist);
}
