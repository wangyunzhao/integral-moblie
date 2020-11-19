package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.model.DingSportRecordModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.tfjybj.integral.model.SportRecordModel;
import java.util.List;
import java.util.Map;

/**
 * SportRecordDao接口
 * sportRecord表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("sportRecordDao")
public interface SportRecordDao{
    /**
     * 功能描述：查询用户个人的钉钉运动数据
     * @author:董思莲
     * @return
     */
    List<SportRecordModel> querySelfSport(String userId,int pageNum,int pageSize,String firstDay,String todayTime);

    /**
     * 查询当天所有人的加分记录-赵雷-2019年10月9日10:52:32
     * @return
     */
    List<DingSportRecordModel> selectAllSportRecord();

}
