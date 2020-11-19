package com.tfjybj.integral.provider.job;

import com.tfjybj.integral.provider.service.AddIntegralService;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 更新用户可赠积分
 * @author: 赵雷
 * @create: 2019-10-13 10:03
 **/
@JobHandler(value = "UpdateUserGivingIntegralJob")
@Component
@Slf4j
public class UpdateUserGivingIntegralJob extends IJobHandler implements Serializable {

	@Autowired
	private AddIntegralService addIntegralService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Override
	public ReturnT<String> execute(String s) throws Exception {
		XxlJobLogger.log(this.getClass().getSimpleName() + "--start");
		// 获取xxl-job获取的参数
		if("".equals(s)){
			s="100";
		}
		int updateGivingIntegral=Integer.parseInt(s);
		try {
			addIntegralService.updateGivingIntegral(updateGivingIntegral);
		} catch (Exception e) {
			XxlJobLogger.log(e.getMessage(),e);
		}
		// 清空库里面的所有人的加分记录
		Set<String> payIncomeKeys = redisUtil.keys(RedisContants.givingIntegral + "*");
		redisTemplate.delete(payIncomeKeys);
		XxlJobLogger.log(this.getClass().getSimpleName() + "--end");
		return ReturnT.SUCCESS;
	}
}
