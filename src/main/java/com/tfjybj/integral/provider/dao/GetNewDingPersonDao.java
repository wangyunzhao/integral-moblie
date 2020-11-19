package com.tfjybj.integral.provider.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository("getNewDingPersonDao")
public interface GetNewDingPersonDao {
    /**
     * 更新给分字段通过id
     * @param ids
     * @return
     */
    Integer updateIsGivingSelfById(@Param("ids") List<String> ids);

    /**
     * 更新给分字段为0
     * @return
     */
    Integer updateIsGivingSelfZero();

    List<String> selectIdByDingId(@Param("allUserDingId") HashSet<String> allUserDingId);
}
