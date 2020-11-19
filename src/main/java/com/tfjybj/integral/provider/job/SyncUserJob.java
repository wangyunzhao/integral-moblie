package com.tfjybj.integral.provider.job;

import com.tfjybj.integral.provider.service.GetNewDingPersonService;
import com.tfjybj.integral.provider.service.SyncUserService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 根据tc_alluser表对tik_user表更新
 * 梁佳宝 2020年6月13日10点33分
 */
@JobHandler(value = "SyncUserJob")
@Component
@Slf4j
public class SyncUserJob extends IJobHandler {
    @Resource
    private SyncUserService syncUserService;
    @Resource
    private GetNewDingPersonService getNewDingPersonService;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log(this.getClass().getSimpleName()+"---start");
        try {
            //汇总钉钉考勤的分值到integral表和tik_user表
            syncUserService.syncUser();
            //通过dingid更新是否可以给分字段
//            getNewDingPersonService.allUserDingId();
            XxlJobLogger.log(this.getClass().getSimpleName()+"---end");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }
}
