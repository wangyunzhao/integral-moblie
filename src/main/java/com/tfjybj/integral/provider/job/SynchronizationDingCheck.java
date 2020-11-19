/**
 * @program: integral_mobile_parent
 * @description: 汇总积分详情到月积分Job
 * @author: 14_赵芬
 * @create: 2019-09-14 15:17
 **/
package com.tfjybj.integral.provider.job;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname  SynchronizationDingCheck
 * @Auther 14_zhaofen
 * @Date 2019年10月9日10:55:52
 */
@Slf4j
@Component
@JobHandler(value = "SynchronizationDingCheck")
public class SynchronizationDingCheck extends IJobHandler {
    @Resource
    private com.tfjybj.integral.provider.service.DingAttendanceService DingAttendanceService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log(this.getClass().getSimpleName()+"---start");
        try {
            //汇总钉钉考勤的分值到integral表和tik_user表
            DingAttendanceService.synchronizationDingData() ;
            XxlJobLogger.log(this.getClass().getSimpleName()+"---end");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }
}