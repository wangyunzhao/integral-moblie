/**
 * @program: integral_mobile_parent
 * @description: 汇总积分详情到月积分Job
 * @author: 14_赵芬
 * @create: 2019-09-14 15:17
 **/
package com.tfjybj.integral.provider.job;
import com.tfjybj.integral.provider.service.HomeLoadingService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname  SummaryMonthIntegral
 * @Auther 14_zhaofen
 * @Date 2019年9月14日15:20:16
 */
@Slf4j
@Component
@JobHandler(value = "SummaryMonthIntegral")
public class SummaryMonthIntegral extends IJobHandler {
    @Resource
    private HomeLoadingService HomeLoadingService;
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log(this.getClass().getSimpleName()+"---start");
        try {
            //执行汇总detail表数据到月汇总表
            HomeLoadingService.SummaryMonthIntegral();
            XxlJobLogger.log(this.getClass().getSimpleName()+"---end");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }
}
