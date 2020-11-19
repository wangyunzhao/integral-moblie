package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.ProjectPluginModel;
import org.springframework.stereotype.Repository;

/**
 * ProjectDao接口
 * project表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("projectDao")
public interface ProjectDao {

    //查询项目的信息
    public ProjectPluginModel queryProjectInfo(String secretId);
}
