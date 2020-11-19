package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.FrequentUserModel;
import com.tfjybj.integral.model.PluginModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PluginDao接口
 * plugin表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("pluginDao")
public interface PluginDao{
    /**
     * @Description:查询插件id和插件名
     * @Param:
     * @return:
     * @Author: 崔晓鸿
     * @Date:
     */
    List<PluginModel> selectAllPlugin();
	
}
