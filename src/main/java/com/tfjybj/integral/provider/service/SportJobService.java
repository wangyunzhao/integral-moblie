package com.tfjybj.integral.provider.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tfjybj.integral.constant.ALLPlugInfo;
import com.tfjybj.integral.entity.IntegralDetailEntity;
import com.tfjybj.integral.model.DingSportRecordModel;
import com.tfjybj.integral.provider.dao.IntegralDetailDao;
import com.tfjybj.integral.provider.dao.SportRecordDao;
import com.tfjybj.integral.provider.dao.TikUserDao;
import com.tfjybj.integral.utils.exception.ApplicationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * @program: integralV3.0-sprint1.0
 * @description: 编写同步钉钉运动的jog服务
 * @author: 赵雷
 * @create: 2019-10-09 10:11
 **/
@Service("SportJobService")
public class SportJobService {
	@Resource
	private SportRecordDao sportRecordDao;
	@Resource
	private IntegralDetailDao integralDetailDao;
	@Resource
	private TikUserDao tikUserDao;

	public Boolean syncAllSportRecord() {
		//	分组查询今天的所有加分记录
		List<DingSportRecordModel> dingSportRecordModelList = sportRecordDao.selectAllSportRecord();
		if(CollectionUtils.isEmpty(dingSportRecordModelList)){
			return true;
		}
		//	然后转换为integralAddModel,也是一个list
		List<IntegralDetailEntity> integralDetailEntityList = dingSportRecordModelList.stream().map(dingSportRecordModel -> {
			IntegralDetailEntity integralDetailEntity = new IntegralDetailEntity();
			integralDetailEntity.setId(IdWorker.getIdStr());
			integralDetailEntity.setGivingUserId(ALLPlugInfo.DingSport_Info_GivingUserId);
			integralDetailEntity.setUserId(dingSportRecordModel.getUserId());
			integralDetailEntity.setIntegral(dingSportRecordModel.getIntegral());
			integralDetailEntity.setPluginId(ALLPlugInfo.DingSport_Info_PluGinId);
			integralDetailEntity.setTypeKey(ALLPlugInfo.DingSport_Info_TypeKey);
			integralDetailEntity.setPrimaryId(ALLPlugInfo.DingSport_Info_PrimaryId);
			integralDetailEntity.setCreateTime(dingSportRecordModel.getCreateTime());
			integralDetailEntity.setCreator(dingSportRecordModel.getCreator());
			integralDetailEntity.setUpdateTime(dingSportRecordModel.getUpdateTime());
			integralDetailEntity.setOperator(dingSportRecordModel.getOpterator());
			return integralDetailEntity;
		}).collect(Collectors.toList());
		// 更新所有用户的tikUser的总积分
		Boolean isUpdateAllIntegral;
		try {
			isUpdateAllIntegral=tikUserDao.updateIntegralList(integralDetailEntityList)>0;
		} catch (Exception e) {
			throw new ApplicationException(IntegralResult.FAIL,"批量更新所有用户的总积分异常");
		}
		if (!isUpdateAllIntegral) {
			throw new ApplicationException(IntegralResult.FAIL,"批量更新所有用户的总积分失败");
		}
		//  然后把这个list批量插入到实体表里面
		Boolean isInsertSuccess;
		try {
			isInsertSuccess = integralDetailDao.insertIntegralDetail(integralDetailEntityList) > 0;
		} catch (Exception e) {
			throw new ApplicationException(IntegralResult.FAIL,"同步钉钉数据到加分详情表异常");
		}
		if (!isInsertSuccess) {
			throw new ApplicationException(IntegralResult.FAIL,"同步钉钉数据到加分详情表失败");
		}
		return isInsertSuccess;
	}
}
