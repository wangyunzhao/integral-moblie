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

@JobHandler(value = "GetNewDingPersonJob")
@Component
@Slf4j
public class GetNewDingPersonJob extends IJobHandler {
    @Resource
    private GetNewDingPersonService getNewDingPersonService;
    @Resource
    private SyncUserService syncUserService;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log(this.getClass().getSimpleName()+"---start");
        try {
            //通过dingid更新是否可以给分字段
            getNewDingPersonService.allUserDingId();
            //更新无用人员为is_delete
            syncUserService.updateUselessIsdelete();
            XxlJobLogger.log(this.getClass().getSimpleName()+"---end");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }
}
