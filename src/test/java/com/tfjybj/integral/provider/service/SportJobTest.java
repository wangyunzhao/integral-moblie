package com.tfjybj.integral.provider.service;

import com.tfjybj.integral.provider.TestBaseProject;
import com.tfjybj.integral.provider.dao.IntegralDetailDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 钉钉运动service测试
 * @author: 赵雷
 * @create: 2019-10-09 11:31
 **/
public class SportJobTest extends TestBaseProject {
	@Resource
	private SportJobService sportJobService;
	@Test
	public void SportJobServiceTest(){
		Boolean aBoolean = sportJobService.syncAllSportRecord();
		System.out.println(aBoolean);

	}
}
