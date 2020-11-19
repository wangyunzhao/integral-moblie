package com.tfjybj.integral.provider.job;

import com.tfjybj.integral.provider.service.AddIntegralService;
import com.tfjybj.integral.provider.service.SportJobService;
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
 * @description: 每晚定时将redis数据同步到数据库中
 * @author: 赵雷
 * @create: 2019-09-16 11:02
 **/
@JobHandler(value = "SportRecordJob")
@Component
@Slf4j
public class SportRecordJob extends IJobHandler implements Serializable {
	@Autowired
	private SportJobService sportJobService;


	@Override
	public ReturnT<String> execute(String s) throws Exception {
		XxlJobLogger.log(this.getClass().getSimpleName() + "--start");

		try {
			sportJobService.syncAllSportRecord();
			XxlJobLogger.log(this.getClass().getSimpleName() + "--end");
			return ReturnT.SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw e;
		}

	}
}
