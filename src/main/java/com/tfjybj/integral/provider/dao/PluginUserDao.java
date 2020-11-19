package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.PluginModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PluginUserDao接口
 * pluginUser表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("pluginUserDao")
public interface PluginUserDao{
    /**
     * 根据用户id查询用户所绑定的插件-曹祥铭-2019年9月12日15:20:02
     * @param usrId  用户id
     * @return
     */
    List<PluginModel> queryUserBindPlugin(String usrId);
	
}
