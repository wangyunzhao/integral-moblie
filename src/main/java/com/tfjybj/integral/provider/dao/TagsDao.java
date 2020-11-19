package com.tfjybj.integral.provider.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * TagsDao接口
 * tags表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("tagsDao")
public interface TagsDao{

    /**
     * @Description: 根据用户code角色信息
     * @Param:  userCode
     * @return:  List<UserTagModel>
     * @Author: 王云召
     * @Date: 2019/9/14
     */
     String queryUserTag(@Param("userCode") String userCode);
	
}
