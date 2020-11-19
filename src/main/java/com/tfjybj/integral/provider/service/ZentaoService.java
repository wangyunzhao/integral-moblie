package com.tfjybj.integral.provider.service;

import com.tfjybj.integral.model.ColleagueResult.BugAndTaskModel;
import com.tfjybj.integral.model.ColleagueResult.BugModel;
import com.tfjybj.integral.model.ColleagueResult.TaskModel;
import com.tfjybj.integral.model.scoreResult.*;
import com.tfjybj.integral.provider.dao.ZentaoResultDao;

import com.tfjybj.integral.provider.dao.TeamDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

@Slf4j
@Service("ZentaoService")
public class ZentaoService {

    @Resource
    private ZentaoResultDao zentaoResultDao;

    /**
    * @Description: 根据用户id，加分时间查询bug、task汇总及详细信息
    * @Param:  user_id,success_time
    * @return:  ObjectTypeResultModel
    * @Author: 谷浩樟
    * @Date: 2019/9/12
    */
    public ObjectTypeResultModel queryObjectTypeResult(String user_id, java.sql.Date success_time) {

        //实例化最终需返回的ObjectTypeResultModel
        ObjectTypeResultModel objectTypeResultModel = new ObjectTypeResultModel();

        //将查询到的bug、task汇总及详细数据放入model中拼接
        SumDataModel sumDataModel = new SumDataModel();
        DetailDataModel detailDataModel = new DetailDataModel();

        sumDataModel.setSumBugSet(zentaoResultDao.querySumBugByAction(user_id, success_time));
        sumDataModel.setSumTaskSet(zentaoResultDao.querySumTaskByAction(user_id, success_time));

        detailDataModel.setDetailBugSet(zentaoResultDao.queryDetailBugByAction(user_id, success_time));
        detailDataModel.setDetailTaskSet(zentaoResultDao.queryDetailTaskByAction(user_id, success_time));

        objectTypeResultModel.setDetailDataSet(detailDataModel);
        objectTypeResultModel.setSumDataSet(sumDataModel);

        //返回实例化最终需返回的ObjectTypeResultModel
        return objectTypeResultModel;
    }

    /**
     * @Description: 根据用户id，加分时间查询所在项目成员完成bug、Task数据
     * @Param:  user_id
     * @return:  ObjectTypeResultModel
     * @Author: 谷浩樟
     * @Date: 2019/9/12
     */
    public BugAndTaskModel selectBugAndTaskModel(String user_id) {
        //实例化BugAndTaskModel
        BugAndTaskModel bugAndTaskModel = new BugAndTaskModel();

        //将查询到的bug、task数据放入BugAndTaskModel
        bugAndTaskModel.setBugModel(zentaoResultDao.selectColleagueBugByDate(user_id));
        bugAndTaskModel.setTaskModel(zentaoResultDao.selectColleagueTaskByDate(user_id));

        //返回BugAndTaskModel
        return bugAndTaskModel;
    }

}
