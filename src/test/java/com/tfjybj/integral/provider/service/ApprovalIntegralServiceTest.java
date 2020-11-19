/*
package com.tfjybj.integral.provider.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tfjybj.integral.entity.AllusersEntity;
import com.tfjybj.integral.entity.MessageEntity;
import com.tfjybj.integral.model.IntegralDetailModel;
import com.tfjybj.integral.model.OrganizaitonsInCompany;
import com.tfjybj.integral.provider.TestBaseProject;
import com.tfjybj.integral.provider.dao.IntegralDetailDao;
import com.tfjybj.integral.provider.dao.TikUserDao;
import com.tfjybj.integral.provider.job.AddIntegralJob;
import com.tfjybj.integral.utils.cache.JSONUtils;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import com.tfjybj.integral.utils.http.HttpUtils;
import com.tfjybj.integral.utils.http.ResponseWrap;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApprovalIntegralServiceTest extends TestBaseProject{
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private IntegralDetailDao integralDetailDao;
	@Autowired
	private AddIntegralService addIntegralService;
	@Autowired
	private TikUserDao tikUserDao;

	@Autowired
	private AddIntegralJob addIntegralJob;

	@Test
	public void outComeTest() {
		Set keys = redisUtil.keys(RedisContants.addScores + "*");
		for (Object str : keys) {
			List<IntegralDetailModel> integralDetailModelList = redisUtil.lGetAll(str.toString(), IntegralDetailModel.class);
			if (!CollectionUtils.isEmpty(integralDetailModelList) && integralDetailModelList.size() != 0) {
				if (integralDetailDao.insertIntegralDetail(integralDetailModelList) > 0) {
					// 计算该用户已经附赠出去的分数
					Integer sumGivingInteger = integralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral)) == null ? 0 : integralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral));
					// 取这个人的可赠积分,如果积分大于可赠人的,直接删除,否则,扣除这个积分,然后,把他本身的积分再做删除
					String givingUserId = integralDetailModelList.get(0).getGivingUserId();
					String userId = integralDetailModelList.get(0).getUserId();

					// 取出这个人的可赠积分
					Integer givingInteger = tikUserDao.selectGivingById(givingUserId) == null ? 0 : tikUserDao.selectGivingById(givingUserId);
					Integer userInteger = givingInteger - sumGivingInteger;
					if (userInteger >= 0) {
						//	表明直接扣除赠的积分,不用更新用户原有的积分,更新该用户的可赠积分
						if (tikUserDao.updateGivingIntegral(givingUserId, userInteger)) {

						}
					} else {
						//	扣除该用户的可赠积分,然后利用本地积分加上userInteger,就是该用户的现在积分,然后更新用户的可赠积分和本地积分
						if (tikUserDao.updateAllIntegral(givingUserId, givingInteger + tikUserDao.selectIntegerById(givingUserId))) {

						}
					}
					redisUtil.del(RedisContants.addScores + givingUserId);

				} else {
					System.out.println("插入数据失败");
				}
			} else {
				System.out.println("redis中数据为空");
			}


		}
		redisUtil.del(RedisContants.payIncome+"*");
	}

	@Test
	public void inComeTest() {
		Set keys = redisUtil.keys(RedisContants.payIncome + "*");
		for (Object str : keys) {
			List<IntegralDetailModel> integralDetailModelList = redisUtil.lGetAll(str.toString(), IntegralDetailModel.class);
			if (!CollectionUtils.isEmpty(integralDetailModelList) && integralDetailModelList.size() != 0) {
				if (integralDetailDao.insertIntegralDetail(integralDetailModelList) > 0) {
					// 计算该用户已经收到的分数
					Integer sumGivingInteger = integralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral)) == null ? 0 : integralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral));

					String userId = integralDetailModelList.get(0).getUserId();

					// 取出这个人的自身积分
					Integer userIdInteger = tikUserDao.selectIntegerById(userId) == null ? 0 : tikUserDao.selectGivingById(userId);
					Integer userInteger = userIdInteger + sumGivingInteger;
					if (tikUserDao.updateAllIntegral(userId, userInteger)) {
						redisUtil.del(RedisContants.payIncome + userId);
					} else {
						System.out.println("插入数据失败");
					}
				} else {
					System.out.println("redis中数据为空");
				}
			}
		}
	}

	@Test
	public void test3(){
		// String findUserByOrganizationId="http://192.168.22.52/auth-web/organizationUser/findUserByOrganizationId/"+organizaitonsInPartlyCompany.getId() +"/23VAJAVAXT492QKFsfUBru";
		String findUserByOrganizationId="http://192.168.22.52/auth-web/organizationUser/findUserByOrganizationId/7mXzHqUyFEBkE6U647gmvV/23VAJAVAXT492QKFsfUBru";
		HttpUtils http1 = HttpUtils.get(findUserByOrganizationId);
		http1.addHeader("Content-Type", "application/json; charset=utf-8");
		ResponseWrap responseWrap1 = http1.execute();
		List<String> collect = JSON.parseArray(responseWrap1.getString(), AllusersEntity.class).stream().map(AllusersEntity::getUserName).collect(Collectors.toList());

		System.out.println(collect);

	}

	@Test
	public void test4(){
		ReturnT<String> ga = null;
		try {
			ga = addIntegralJob.execute("ga");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ga);

	}
}*/
