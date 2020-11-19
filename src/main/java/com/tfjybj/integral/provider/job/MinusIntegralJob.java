package com.tfjybj.integral.provider.job;

import com.tfjybj.integral.provider.service.AddIntegralService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 定时减分的任务
 * @author: 赵雷
 * @create: 2019-10-06 16:37
 **/
@JobHandler(value = "MinusIntegralJob")
@Component
@Slf4j
public class MinusIntegralJob extends IJobHandler implements Serializable {
	@Autowired
	private AddIntegralService addIntegralService;

	@Override
	public ReturnT<String> execute(String s) throws Exception {
		XxlJobLogger.log(this.getClass().getSimpleName() + "--start");

		try {
			addIntegralService.minusIntegralJob();
			XxlJobLogger.log(this.getClass().getSimpleName() + "--end");
			return ReturnT.SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw e;
		}
	}
}
