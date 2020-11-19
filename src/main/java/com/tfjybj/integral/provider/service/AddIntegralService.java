package com.tfjybj.integral.provider.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Lists;
import com.tfjybj.integral.Application;
import com.tfjybj.integral.constant.ErrorCodeConstant;
import com.tfjybj.integral.constant.ErrorMessageConstant;
import com.tfjybj.integral.entity.AllusersEntity;
import com.tfjybj.integral.entity.DingMessageEntity;
import com.tfjybj.integral.entity.IntegralDetailEntity;
import com.tfjybj.integral.entity.MessageEntity;
import com.tfjybj.integral.model.*;
import com.tfjybj.integral.provider.dao.DingMessageDao;
import com.tfjybj.integral.provider.dao.IntegralDetailDao;
import com.tfjybj.integral.provider.dao.ProjectDao;
import com.tfjybj.integral.provider.dao.TikUserDao;
import com.tfjybj.integral.utils.cache.JSONUtils;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import com.tfjybj.integral.utils.exception.ApplicationException;
import com.tfjybj.integral.utils.http.HttpUtils;
import com.tfjybj.integral.utils.http.ResponseWrap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("addIntegralService")
public class AddIntegralService {
	@Resource
	private TikUserDao tikUserDao;
	@Resource
	private IntegralDetailDao integralDetailDao;
	@Resource
	private MessageService messageService;
	@Resource
	private DingMessageDao dingMessageDao;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ProjectDao projectDao;

	@Value("${permission.organization}")
	private String Organization_Address;

	@Value("${permission.personsinorganization}")
	private String PersonsInOrganization_Address;

	/**
	 * 根据当前用户查询所剩积分-赵雷-2019年9月18日14:37:57
	 *
	 * @param userId
	 * @return
	 */
	public Integer selectGivingById(String userId) {
		// Redis获取该用户的可赠积分
		String givingUserIntegral = redisUtil.get(RedisContants.givingIntegral + userId);
		if (StringUtils.isEmpty(givingUserIntegral)) {
			// 数据库中获取该用户的可赠积分
			Integer selectGivingIntegral = tikUserDao.selectGivingById(userId);
			redisUtil.set(RedisContants.givingIntegral + userId, selectGivingIntegral.toString());
			return selectGivingIntegral;
		}
		return Integer.parseInt(givingUserIntegral);
	}

	/**
	 * 查询最近加分联系人(数据库三人+Redis中当前加分人数)-赵雷-2019年9月18日14:38:21
	 *
	 * @param userId
	 * @return
	 */
	public List<FrequentUserModel> selectFrequentUserMonth(String userId) {
		// 首先判断是否redis中存储了最近联系人信息
		List<FrequentUserModel> frequentUserModelList = JSONObject.parseArray(redisUtil.get(RedisContants.frequentThreeUser + userId), FrequentUserModel.class);
		if (!CollectionUtils.isEmpty(frequentUserModelList)) {
			return frequentUserModelList;
		}
		Set<IntegralDetailEntity> integralDetailEntitySet = new HashSet<>();
		// 分数来源于两个地方,一个是redis中找个人的,另外一个是数据库中的;
		//region 首先判断来源于Redis中,放入某个地方
		List<IntegralDetailModel> integralDetailModelList = redisUtil.lGetAll(RedisContants.addScores + userId, IntegralDetailModel.class);
		if (!CollectionUtils.isEmpty(integralDetailModelList)) {
			List<IntegralDetailEntity> integralDetailEntityList = modelTranslateEntity(integralDetailModelList);
			integralDetailEntitySet.addAll(integralDetailEntityList);
		}
		//endregion
		// 然后再获取数据库中的
		List<IntegralDetailEntity> integralDetailEntityList = integralDetailDao.selectFrequentUserMonth(userId);
		if (!CollectionUtils.isEmpty(integralDetailEntityList)) {
			integralDetailEntitySet.addAll(integralDetailEntityList);
		}
		if (CollectionUtils.isEmpty(integralDetailEntitySet)) {
			return Lists.newArrayList();
		}
		// 汇总redis和数据库中查询的总和并且进行排序
		ArrayList<IntegralDetailEntity> integralDetailEntityArrayList = new ArrayList<>(integralDetailEntitySet);
		Collections.sort(integralDetailEntityArrayList, Comparator.comparing(IntegralDetailEntity::getCreateTime));
		Collections.reverse(integralDetailEntityArrayList);
		// redis中数据转换成FrequentUserModel实体
		List<FrequentUserModel> frequentUserDBModelList = tikUserDao.selectUserInfo(integralDetailEntityArrayList);
		redisUtil.set(RedisContants.frequentThreeUser + userId, JSONUtils.toJSONPrettyString(frequentUserDBModelList), 30);
		return frequentUserDBModelList;
	}

	/**
	 * 查询最近二十条加分联系人-赵雷-2019年9月18日14:38:49
	 *
	 * @param userId
	 * @return 最近二十个联系人
	 */
	public List<FrequentUserModel> selectFrequentUsersMonth(String userId) {
		// 首先判断是否redis中存储了最近联系人信息
		List<FrequentUserModel> frequentUserModelList = JSONObject.parseArray(redisUtil.get(RedisContants.frequentUsers + userId), FrequentUserModel.class);
		if (!CollectionUtils.isEmpty(frequentUserModelList)) {
			return frequentUserModelList;
		}
		Set<IntegralDetailEntity> integralDetailEntitySet = new HashSet<>();
		// 分数来源于两个地方,一个是redis中找个人的,另外一个是数据库中的;
		//region 首先判断来源于Redis中,放入某个地方
		List<IntegralDetailModel> integralDetailModelList = redisUtil.lGetAll(RedisContants.addScores + userId, IntegralDetailModel.class);
		if (!CollectionUtils.isEmpty(integralDetailModelList)) {
			List<IntegralDetailEntity> integralDetailEntityList = modelTranslateEntity(integralDetailModelList);
			integralDetailEntitySet.addAll(integralDetailEntityList);
		}
		//endregion
		// 然后再获取数据库中的
		List<IntegralDetailEntity> integralDetailEntityList = integralDetailDao.selectFrequentUsersMonth(userId);
		if (!CollectionUtils.isEmpty(integralDetailEntityList)) {
			integralDetailEntitySet.addAll(integralDetailEntityList);
		}
		// 如果为空的话,直接返回为空
		if (CollectionUtils.isEmpty(integralDetailEntitySet)) {
			return Lists.newArrayList();
		}
		// 汇总redis和数据库中查询的总和并且进行排序
		ArrayList<IntegralDetailEntity> integralDetailEntityArrayList = new ArrayList<>(integralDetailEntitySet);
		Collections.sort(integralDetailEntityArrayList, Comparator.comparing(IntegralDetailEntity::getCreateTime));
		Collections.reverse(integralDetailEntityArrayList);
		// redis中数据转换成FrequentUserModel实体
		List<FrequentUserModel> frequentUserDBModelList = tikUserDao.selectUserInfo(integralDetailEntityArrayList);
		redisUtil.set(RedisContants.frequentUsers + userId, JSONUtils.toJSONPrettyString(frequentUserDBModelList), 30);
		return frequentUserDBModelList;
	}

	/**
	 * 查询组织结构树(包含人员)-赵雷-2019年9月18日14:39:49
	 *
	 * @return 包含人员的组织结构
	 */
	public List<OrganizaitonsInPartlyCompany> selectOrganizationInCompany(String authorization) {
		// 判断是否redis中存储了该数据,存储的话,从redis中进行存储
		if (CollectionUtils.isEmpty(JSONObject.parseArray(redisUtil.get(RedisContants.organizationsInCompany)))) {
			//region 获取组织结构的HttpAPI请求
			String str = Organization_Address;
			HttpUtils http = HttpUtils.get(str);
			http.addHeader("Content-Type", "application/json; charset=utf-8");
			http.addHeader("Authorization",authorization);
			ResponseWrap responseWrap = http.execute();
			List<OrganizaitonsInCompany> organizaitons = JSON.parseArray(responseWrap.getString(), OrganizaitonsInCompany.class);
			List<OrganizaitonsInPartlyCompany> organizaitonsInPartlyCompanyList = Lists.newArrayList();
			//endregion
			// 转换前端需要的数据实体
			if (!CollectionUtils.isEmpty(organizaitons) && organizaitons.size() != 0) {
				organizaitonsInPartlyCompanyList = organizaitons.get(0).getChildren().stream().filter(child->!child.getName().equals("各期执行CEO")&&!child.getName().equals("师范学院")).map(child -> {
					OrganizaitonsInPartlyCompany organizaitonsInPartlyCompany = new OrganizaitonsInPartlyCompany();
					organizaitonsInPartlyCompany.setId(child.getId());
					organizaitonsInPartlyCompany.setName(child.getName());
					return organizaitonsInPartlyCompany;
				}).collect(Collectors.toList());

				// 根据组织去查询该组织下所有的人员
				for (OrganizaitonsInPartlyCompany organizaitonsInPartlyCompany :
						organizaitonsInPartlyCompanyList) {
					//region 发送请求获取数据
					String findUserByOrganizationId = PersonsInOrganization_Address + organizaitonsInPartlyCompany.getId() + "/23VAJAVAXT492QKFsfUBru";
					HttpUtils http1 = HttpUtils.get(findUserByOrganizationId);
					http1.addHeader("Content-Type", "application/json; charset=utf-8");
					http1.addHeader("Authorization",authorization);
					ResponseWrap responseWrap1 = http1.execute();
					List<AllusersEntity> allusersEntities = JSON.parseArray(responseWrap1.getString(), AllusersEntity.class);
					//endregion
					// 转换前端需要的实体
					if (!CollectionUtils.isEmpty(allusersEntities)) {
						List<FaceToFaceModel> faceToFaceModelList = allusersEntities.stream().map(all -> {
							FaceToFaceModel faceToFaceModel = new FaceToFaceModel();
							faceToFaceModel.setId(all.getId());
							faceToFaceModel.setName(all.getUserName());
							return faceToFaceModel;
						}).collect(Collectors.toList());
						organizaitonsInPartlyCompany.setStringList(faceToFaceModelList);
					}
				}
				redisUtil.set(RedisContants.organizationsInCompany, JSONUtils.toJSONPrettyString(organizaitonsInPartlyCompanyList), 1800);
			}
			return organizaitonsInPartlyCompanyList;
		}
		return JSON.parseArray(redisUtil.get(RedisContants.organizationsInCompany), OrganizaitonsInPartlyCompany.class);
	}


	/**
	 * 根据加分记录实现多人加分-赵雷-2019年9月18日14:41:09
	 *
	 * @param integralDetailModelList
	 * @return
	 */
	public IntegralResult addPeoplesIntegral(List<IntegralDetailModel> integralDetailModelList, String givingUserId) {
		// 抽离出核心代码,然后做两个service服务!
		// 有利于分开处理givingUserId和多个被加分人
		return addIntegral(integralDetailModelList, givingUserId);
	}

	/**
	 * 核心加分功能-供多人加分-赵雷-2019年10月5日10:44:00
	 *
	 * @param integralDetailModelList
	 * @param givingUserId
	 * @return
	 */
	public IntegralResult addIntegral(List<IntegralDetailModel> integralDetailModelList, String givingUserId) {
		// 判断用户是否有赠分权限
		Integer result = tikUserDao.selectUserGivingSelf(givingUserId);
		if(result!=1){
			return IntegralResult.build(ErrorCodeConstant.AddPoints_Point_Less, ErrorMessageConstant.AddPoints_Point_Less);
		}
		// 判断当前用户积分是否可赠
		Integer willGivingIntegral = integralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral));
		IntegralResult integralResult = isAddIntegral(givingUserId, willGivingIntegral);
		if (!"0000".equals(integralResult.getCode())) {
			return integralResult;
		}
		//region 循环增加Redis中加分记录和加分收入记录
		for (IntegralDetailModel integralDetailModel : integralDetailModelList) {
			IntegralResult succAddIntegral = isSuccAddIntegral(integralDetailModel, givingUserId);
			if (!"0000".equals(succAddIntegral.getCode())) {
				return integralResult;
			}
		}
		// 查询加分用户的姓名
		String givingUserName = tikUserDao.selectUserName(givingUserId);
		// 调用发送消息的service接口;
		IntegralResult sendMessagesResult = sendMessages(integralDetailModelList, givingUserName);
		if(!"0000".equals(sendMessagesResult.getCode())){
			return sendMessagesResult;
		}
		//给每个人的群里发送红包消息
		//TODO：
		List<String> userIds =
				integralDetailModelList.stream().map(IntegralDetailModel::getUserId).collect(Collectors.toList());
		//查询dingid
		List<String> dingIds = dingMessageDao.getUserId(userIds);
		List<DingMessageEntity> dingMessageEntityList = getGroupId(dingIds);

		RestTemplate restTemplate = new RestTemplate();
		JSONObject jsonOb = new JSONObject();
		String url = "http://192.168.50.98:9021/message/DingMessage/dingMessage";

		for (DingMessageEntity dingMessageEntity:dingMessageEntityList){
			jsonOb.put("author", "天赋吉运·积分项目组·技术支持");
			jsonOb.put("chatId", dingMessageEntity.getGroupId());
			jsonOb.put("head", dingMessageEntity.getGroupName());
			jsonOb.put("rich", "☞ 立即领取");
			jsonOb.put("title", "亲~"+givingUserName+"给您加分了");
			jsonOb.put("url", "http://points2.dmsd.tech/integral-mobile3/#/redEnvelopes");

			JSONObject resultJson = restTemplate.postForEntity(url, jsonOb,JSONObject.class).getBody();

			String code = (String) resultJson.get("code");
			System.out.println(code);
		}
		
		return IntegralResult.build(IntegralResult.SUCCESS, "加分成功");
	}

	/**
	 * 判断该用户积分是否可赠送-赵雷-2019年10月6日22:06:46
	 *
	 * @param givingUserId
	 * @return
	 */
	public IntegralResult isAddIntegral(String givingUserId, Integer willGivingIntegral) {
		// 获取该用户DB中的原积分
		Integer userinteger = tikUserDao.selectIntegerById(givingUserId);
		// 获取该用户的可赠积分
		Integer givingUserinteger = selectGivingById(givingUserId);
		// 接收总分数
		Integer sumGivedIntegral = 0;
		// 赠送总分数
		Integer sumGivingIntegral = 0;

		//region 获取该用户已经赠送的积分总和
		List<IntegralDetailModel> givingIntegralDetailModelList = redisUtil.lGetAll(RedisContants.addScores + givingUserId, IntegralDetailModel.class);
		if (!CollectionUtils.isEmpty(givingIntegralDetailModelList)) {
			sumGivingIntegral = givingIntegralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral));
		}
		//endregion

		//region 获取该用户已经接受的积分综合
		List<IntegralDetailModel> givedIntegralDetailModelList = redisUtil.lGetAll(RedisContants.payIncome + givingUserId, IntegralDetailModel.class);
		if (!CollectionUtils.isEmpty(givedIntegralDetailModelList)) {
			sumGivedIntegral = givedIntegralDetailModelList.stream().collect(Collectors.summingInt(IntegralDetailModel::getIntegral));
		}
		//endregion
		// 计算该用户是否积分可赠
		Integer isEnoughGiving = userinteger + givingUserinteger + sumGivedIntegral - sumGivingIntegral - willGivingIntegral;
		if (isEnoughGiving < 0) {
			return IntegralResult.build(ErrorCodeConstant.AddPoints_Point_Less, ErrorMessageConstant.AddPoints_Point_Less);
		}
		return IntegralResult.build(IntegralResult.SUCCESS, "积分充足");
	}

	/**
	 * 判断该用户是否加分成功-赵雷-2019年10月6日22:13:03
	 *
	 * @param integralDetailModel
	 * @return
	 */
	public IntegralResult isSuccAddIntegral(IntegralDetailModel integralDetailModel, String givingUserId) {
		Integer givedIntegral = integralDetailModel.getIntegral();
		// 查询该用户当前的可赠积分
		Integer givingInteger = selectGivingById(givingUserId);
		// 该用户可赠积分减去当前赠送用户的积分
		Integer calculateIntegral = givingInteger - givedIntegral;
		if (calculateIntegral >= 0) {
			// 说明通过可赠积分送的分数
			integralDetailModel.setGivingIntegral(givedIntegral);
			// 更新redis中该用户的可赠积分
			redisUtil.set(RedisContants.givingIntegral + givingUserId, calculateIntegral.toString());
		} else {
			// 此时该用户可赠积分不足以支付赠送用户的积分
			calculateIntegral = calculateIntegral * (-1);
			integralDetailModel.setGivingIntegral(givingInteger);
			integralDetailModel.setSelfIntegral(calculateIntegral);
			// 更新redis中该用户的可赠积分
			redisUtil.set(RedisContants.givingIntegral + givingUserId, "0");
		}
		//region 存储该用户的加分记录
		Boolean isSuccGivingUserId = redisUtil.lSet(RedisContants.addScores + givingUserId, JSONUtils.toJSONPrettyString(integralDetailModel)) > 0;
		if (!isSuccGivingUserId) {
			return IntegralResult.build(ErrorCodeConstant.AddPoints_GiviedRecord_Fail, ErrorMessageConstant.AddPoints_GiviedRecord_Fail);
		}
		//endregion
		//region 存储被加分用户的加分记录
		Boolean isSuccGivedUserId = redisUtil.lSet(RedisContants.payIncome + integralDetailModel.getUserId(), JSONUtils.toJSONPrettyString(integralDetailModel)) > 0;
		if (!isSuccGivedUserId) {
			// 其实这里应该打入日志
			return IntegralResult.build(ErrorCodeConstant.AddPoints_GivingRecord_Fail, ErrorMessageConstant.AddPoints_GivingRecord_Fail);
		}
		//endregion
		return IntegralResult.build(IntegralResult.SUCCESS, "加分成功");
	}

	/**
	 * 加分后调用消息通知-赵雷-2019年10月8日14:37:49
	 * @param integralDetailModelList
	 * @param givingUserName
	 * @return
	 */
	public IntegralResult sendMessages(List<IntegralDetailModel> integralDetailModelList,String givingUserName){
		List<MessageEntity> messageEntityList = integralDetailModelList.stream().map(integralDetailModel -> {
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setId(IdWorker.getIdStr());
			messageEntity.setIntegral(integralDetailModel.getIntegral());
			messageEntity.setAcceptorId(integralDetailModel.getUserId());
			messageEntity.setSenderId(integralDetailModel.getGivingUserId());
			messageEntity.setMessageContent("由于"+integralDetailModel.getReason()+"已收到"+givingUserName+"给您"+integralDetailModel.getIntegral()+
					"分");
			messageEntity.setStartTime(integralDetailModel.getCreateTime());
			messageEntity.setEndTime(integralDetailModel.getCreateTime());
			messageEntity.setMessageType(1);
			messageEntity.setIsRead(0);
			messageEntity.setUpdateTime(integralDetailModel.getUpdateTime());
			return messageEntity;
		}).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(messageEntityList)) {
			return IntegralResult.build(IntegralResult.FAIL, "转换加分实体失败");
		}
		Boolean isSendMessage = messageService.insertMessages(messageEntityList) > 0;
		if(!isSendMessage){
			return IntegralResult.build(IntegralResult.FAIL, "加分成功,消息通知失败");
		}
		return IntegralResult.build(IntegralResult.SUCCESS, "消息通知成功");
	}
	/**
	 * 根据加分记录实现多人减分-赵雷-2019年10月6日11:42:37
	 *
	 * @param integralDetailModelList
	 * @return
	 */
	public IntegralResult minusPeoplesIntegral(List<IntegralDetailModel> integralDetailModelList, String givingUserId) {
		// 调用减分的功能
		IntegralResult integralResult = minusIntegral(integralDetailModelList, givingUserId);
		if(!"0000".equals(integralResult.getCode())){
			return integralResult;
		}
		// 查询加分用户的姓名
		String givingUserName = tikUserDao.selectUserName(givingUserId);
		// 调用发送消息的service接口;
		IntegralResult sendMessagesResult = sendMessages(integralDetailModelList, givingUserName);
		if(!"0000".equals(sendMessagesResult.getCode())){
			return sendMessagesResult;
		}
		return IntegralResult.build(IntegralResult.SUCCESS, "减分成功");
	}

	/**
	 * 核心加分功能-供多人减分-赵雷-2019年10月5日10:44:00
	 *
	 * @param integralDetailModelList
	 * @param givingUserId
	 * @return
	 */
	public IntegralResult minusIntegral(List<IntegralDetailModel> integralDetailModelList, String givingUserId) {
		// 这里需要判断积分是否为负值,如果为负值的话,不扣除该加分人的分数,但是更新被加分人的积分值;
		// 判断存入加分人送分记录(增加循环即可)
		for (IntegralDetailModel integralDetailModel : integralDetailModelList) {
			Integer givedIntegral = integralDetailModel.getIntegral();
			if (givedIntegral > 0) {
				throw new ApplicationException(ErrorCodeConstant.MinusPoints_Params_Exception, ErrorMessageConstant.MinusPoints_Params_Exception);
			}
			Boolean isSuccGivingUserId = redisUtil.lSet(RedisContants.minusScores + givingUserId, JSONUtils.toJSONPrettyString(integralDetailModel)) > 0;
			if (!isSuccGivingUserId) {
				return IntegralResult.build(ErrorCodeConstant.MinusPoints_GiviedRecord_Fail, ErrorMessageConstant.MinusPoints_GiviedRecord_Fail);
			}
			//region 存储被减分用户的加分记录
			Boolean isSuccGivedUserId = redisUtil.lSet(RedisContants.payIncome + integralDetailModel.getUserId(), JSONUtils.toJSONPrettyString(integralDetailModel)) > 0;
			if (!isSuccGivedUserId) {
				// 其实这里应该打入日志
				return IntegralResult.build(ErrorCodeConstant.AddPoints_GivingRecord_Fail, ErrorMessageConstant.AddPoints_GivingRecord_Fail);
			}
		}
		return new IntegralResult(IntegralResult.SUCCESS, "减分成功");
	}

	/**
	 * 根据加分记录实现单人加分-赵雷-2019年10月5日10:44:05
	 *
	 * @param integralDetailModel
	 * @return
	 */
	public IntegralResult addPeoplesIntegral(IntegralDetailModel integralDetailModel, String givingUserId) {
		// 抽离出核心代码,然后做两个service服务!
		// 有利于分开处理givingUserId和多个被加分人
		return addIntegral(integralDetailModel, givingUserId);
	}

	/**
	 * 核心加分功能-供单个人加分-赵雷-2019年10月5日10:44:10
	 *
	 * @param integralDetailModel
	 * @param givingUserId
	 * @return
	 */
	public IntegralResult addIntegral(IntegralDetailModel integralDetailModel, String givingUserId) {
		Integer willGivingIntegral = integralDetailModel.getIntegral();
		// 判断当前用户积分是否可赠
		IntegralResult integralResult = isAddIntegral(givingUserId, willGivingIntegral);
		if (!"0000".equals(integralResult.getCode())) {
			return integralResult;
		}
		// 判断当前用户是否加分成功
		IntegralResult succAddIntegral = isSuccAddIntegral(integralDetailModel, givingUserId);
		if (!"0000".equals(succAddIntegral.getCode())) {
			return integralResult;
		}
		return IntegralResult.build(IntegralResult.SUCCESS, "加分成功");
	}


	/**
	 * xxb执行插入加分数据到数据库中并且清空redis中的数据 -赵雷-2019年9月18日14:41:44
	 *
	 * @return
	 */
	public Boolean addIntegralJob() {
		// 获取redis中所有加分人的key值
		Set keys = redisUtil.keys(RedisContants.addScores + "*");
		for (Object str : keys) {
			List<IntegralDetailModel> integralDetailEntityList = null;
			try {
				integralDetailEntityList = redisUtil.lGetAll(str.toString(), IntegralDetailModel.class);
			} catch (Exception e) {
				throw new ApplicationException("Redis转换实体失败");
			}
			if (!CollectionUtils.isEmpty(integralDetailEntityList) && integralDetailEntityList.size() > 0) {
				// 抽离插入数据库操作,增加事务控制
				Boolean insertSuccess = false;

				//region 将redis中加分记录存入数据库
				try {
					insertSuccess = insertIntegralDetails(integralDetailEntityList);
				} catch (Exception e) {
					throw new ApplicationException("更新数据库失败");
				}
				//endregion

				if (insertSuccess) {
					// 计算该用户已经附赠出去的分数
					Integer sumGivingInteger = integralDetailEntityList.stream().collect(Collectors.summingInt(IntegralDetailModel::getSelfIntegral));

					//region 取这个人的可赠积分,如果积分大于可赠人的,直接删除;否则,扣除这个积分,然后,删除他本身的积分

					//region 取出这个人的可赠积分
					String givingUserId = integralDetailEntityList.get(0).getGivingUserId();
					Integer givingInteger = selectGivingById(givingUserId);
					//endregion

					//region 计算是否赠送完可赠积分,并更新数据库中该用户的可赠积分和本身积分
					if (givingInteger > 0) {
						//	表明直接扣除赠的积分,不用更新用户原有的积分,更新该用户的可赠积分
						try {
							updateUserIntegral(givingUserId, givingInteger);
						} catch (Exception e) {
							throw new ApplicationException("更新加分用户积分失败", e);
						}

					} else {
						//	扣除该用户的可赠积分,然后利用本地积分加上userInteger,就是该用户的现在积分,然后更新用户的可赠积分和本地积分
						tikUserDao.updateAllIntegral(givingUserId, tikUserDao.selectIntegerById(givingUserId) - sumGivingInteger);
					}
					//endregion
					try {
						updateUsersIntegral(integralDetailEntityList);
					} catch (Exception e) {
						throw new ApplicationException("批量更新被加分用户失败", e);
					}
					//endregion

					// 清空redis中的积分赠送记录和积分收入记录
					redisUtil.del(RedisContants.addScores + givingUserId);
					Set<String> payIncomeKeys = redisUtil.keys(RedisContants.payIncome + "*");
					redisTemplate.delete(payIncomeKeys);
				}
			}
		}
		return true;

	}

	/**
	 * xxb执行插入减分数据到数据库中并且清空redis中的数据 -赵雷-2019年10月6日11:47:23
	 *
	 * @return
	 */
	public Boolean minusIntegralJob() {
		// 获取redis中所有加分人的key值
		Set keys = redisUtil.keys(RedisContants.minusScores + "*");
		for (Object str : keys) {
			List<IntegralDetailModel> integralDetailEntityList = null;
			try {
				integralDetailEntityList = redisUtil.lGetAll(str.toString(), IntegralDetailModel.class);
			} catch (Exception e) {
				throw new ApplicationException("Redis转换实体失败");
			}
			if (!CollectionUtils.isEmpty(integralDetailEntityList) && integralDetailEntityList.size() > 0) {
				// 抽离插入数据库操作,增加事务控制
				Boolean insertSuccess = false;

				//region 将redis中加分记录存入数据库
				try {
					insertSuccess = insertIntegralDetails(integralDetailEntityList);
				} catch (Exception e) {
					throw new ApplicationException("批量插入数据库失败");
				}

				if (insertSuccess) {
					//	批量更新list所有用户的积分
					Boolean updateSuccess = false;
					try {
						updateSuccess = updateUsersIntegral(integralDetailEntityList);
					} catch (Exception e) {
						throw new ApplicationException("批量更新数据库失败");
					}
					redisUtil.del(str.toString());
				}
				//endregion
			}
		}
		return true;
	}

	/**
	 * 批量插入用户加分详情-赵雷-2019年9月18日14:43:06
	 *
	 * @param integralDetailModelList
	 * @return
	 */
	@Transactional
	public Boolean insertIntegralDetails(List<IntegralDetailModel> integralDetailModelList) {
		List<IntegralDetailEntity> integralDetailEntityList = modelTranslateEntity(integralDetailModelList);
		return integralDetailDao.insertIntegralDetail(integralDetailEntityList) > 0;
	}

	/**
	 * 批量更新被加分用户的积分-赵雷-2019年10月6日14:22:55
	 *
	 * @param integralDetailModelList
	 * @return
	 */
	@Transactional
	public Boolean updateUsersIntegral(List<IntegralDetailModel> integralDetailModelList) {
		List<IntegralDetailEntity> integralDetailEntityList = modelTranslateEntity(integralDetailModelList);
		return tikUserDao.updateIntegralList(integralDetailEntityList) > 0;
	}

	/**
	 * 实现IntegralModel和IntegralEntity转换-赵雷-2019年10月7日10:01:53
	 *
	 * @param integralDetailModelList
	 * @return
	 */
	public List<IntegralDetailEntity> modelTranslateEntity(List<IntegralDetailModel> integralDetailModelList) {
		List<IntegralDetailEntity> integralDetailEntityList = Lists.newArrayList();
		integralDetailModelList.stream().forEach(integralDetailModel -> {
			IntegralDetailEntity integralDetailEntity = new IntegralDetailEntity();
			try {
				BeanUtils.copyProperties(integralDetailEntity, integralDetailModel);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			integralDetailEntityList.add(integralDetailEntity);
		});
		return integralDetailEntityList;
	}

	/**
	 * 更新用户可赠积分-赵雷-2019年9月18日14:42:06
	 *
	 * @param givingUserId 赠送积分人员ID
	 * @param userInteger  积分
	 * @return
	 */
	@Transactional
	public Boolean updateUserIntegral(String givingUserId, Integer userInteger) {
		return tikUserDao.updateGivingIntegral(givingUserId, userInteger);
	}


	/**
	 * @Description: 查询全部人员
	 * @Param:
	 * @return:
	 * @Author: 王云召
	 * @Date: 2019/9/17
	 */
	public List<AllUserModel> queryAllUser() {


		List<AllUserModel> allUser = tikUserDao.queryAllUser();
		return allUser;
	}

	/**
	 * @Description: 查询减分权限
	 * @Param:
	 * @return:
	 * @Author: 王云召
	 * @Date: 2019/9/17
	 */
	public int queryReduceIntegral(String userId) {
		return tikUserDao.queryReduceIntegral(userId);
	}

	/**
	 * 按月执行job,更新所有人的可赠积分
	 * @param givingIntegral
	 * @return
	 */
	public void updateGivingIntegral(int givingIntegral){
		try {
			tikUserDao.updateGivingIntegralByMonth(givingIntegral);
		} catch (Exception e) {
			throw new ApplicationException("更新失败");
		}
	}


	/**
	 * 对外提供加分接口，需要接口鉴权-曹祥铭
	 * @param request http请求
	 * @param signature 签名
	 * @param nonceStr 一次性随机码
	 * @param timestamp 时间戳
	 * @param integralDetailModelList 加分信息
	 * @return
	 */
	public IntegralResult addIntegralByAuthentication(HttpServletRequest request, String signature, String nonceStr , String timestamp, List<IntegralDetailModel> integralDetailModelList){
		//0、ip地址校验
		//1、验证签名是否有效
		//2、判断时间是否大于60s
		//3、判断nonceStr是否已经存在于redis中
		//4、将本次请求的nonceStr参数存到redis中，并设置过期时间为60s
		//5、判断加分参数是否合法
		//6、调用加分接口

		if(signature==null||nonceStr==null||signature==""||nonceStr==""||timestamp==""||timestamp==null){
			return IntegralResult.build(IntegralResult.FAIL,"参数不合法！");
		};
		if(!checkIp(request)){
			return IntegralResult.build(IntegralResult.FAIL,"ip地址不在白名单内！");
		}
		if(!checkSignature(signature)){
			return IntegralResult.build(IntegralResult.FAIL,"未授权的请求！");
		};
		try{
			if(checkTimeStamp(Long.valueOf(timestamp))){
			return IntegralResult.build(IntegralResult.FAIL,"请求超时！");
		}
		}catch(Exception e){
			return IntegralResult.build(IntegralResult.FAIL,"参数不合法！");
		}

		if(checkNonceStr(nonceStr)){
			return IntegralResult.build(IntegralResult.FAIL,"请求超时！");
		}
		if(integralDetailModelList.isEmpty()||integralDetailModelList==null){
			return IntegralResult.build(IntegralResult.FAIL,"参数不合法！");
		}
		//将nonceStr存入redis并设置过期时间
		redisUtil.set(RedisContants.nonceStr+nonceStr,signature,60);
		//拿到secretId，查询项目信息
		String secretId = redisUtil.get(RedisContants.signature+signature);
		if(secretId==null&&"".equals(secretId)){
			return IntegralResult.build(IntegralResult.FAIL,"未授权的请求！");
		}
		ProjectPluginModel projectPluginModel = queryProjectInfo(secretId);
		//替换项目信息
		integralDetailModelList=integralDetailModelList.stream().peek(e->{
			e.setPluginId(projectPluginModel.getId()) ;
			e.setPrimaryId("".equals(projectPluginModel.getName())?projectPluginModel.getProjectId():projectPluginModel.getName());
		}).collect(Collectors.toList());
		return  addIntegralMain(integralDetailModelList);
	}


	/**
	 * IP地址获取和校验-曹祥铭
	 * @param request
	 * @return
	 */
	public boolean checkIp(HttpServletRequest request){
		String ip=request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = "";
		}
		//判断ip地址白名单中是否有此IP
		return redisUtil.hasKey(RedisContants.ipList+ip);
	}


	/**
	 * 接口鉴权签名验证-曹祥铭
	 * @param signature 签名
	 * @return
	 */
	public boolean checkSignature(String signature){
		//去redis中查找是否有该签名信息
		return redisUtil.hasKey(RedisContants.signature + signature);

	}

	/**
	 * 	计算时间差-曹祥铭
	 * @param timestamp
	 * @return
	 */
	public boolean checkTimeStamp(Long timestamp){
		return (System.currentTimeMillis()-timestamp)/1000 >60;

	}

	/**
	 * 	//查询nonceStr随机码是否存在-曹祥铭
	 * @param nonceStr 随机码
	 * @return
	 */
	public boolean checkNonceStr(String nonceStr){
		return  redisUtil.hasKey(RedisContants.nonceStr+nonceStr);
	}

	/**
	 * 鉴权加分接口-曹祥铭
	 * @param integralDetailModelList 加分实体集合
	 * @return 加分结果
	 */
	public IntegralResult addIntegralMain(List<IntegralDetailModel> integralDetailModelList){
		String givingUserId = UserUtil.getCurrentUser().getUserId();
		boolean addFlag=false;
		boolean milFlag=false;
		List<IntegralDetailModel> minusIntegralDetail = integralDetailModelList.stream().filter(integralDetailModel -> integralDetailModel.getIntegral() < 0).collect(Collectors.toList());
		if (minusIntegralDetail != null && minusIntegralDetail.size() > 0) {
			minusIntegralDetail = minusIntegralDetail.stream().map(integralDetailModel -> {
				integralDetailModel.setGivingUserId(givingUserId);
				integralDetailModel.setCreator(givingUserId);
				integralDetailModel.setOperator(givingUserId);
				return integralDetailModel;
			}).collect(Collectors.toList());
			IntegralResult integralResult = minusPeoplesIntegral(minusIntegralDetail, givingUserId);
			if("0000".equals(integralResult.getCode())){
				milFlag=true;
			}
		}else{
			milFlag=true;
		}
		List<IntegralDetailModel> addIntegralDetail = integralDetailModelList.stream().filter(integral -> integral.getIntegral() > 0).collect(Collectors.toList());
		if (addIntegralDetail != null && addIntegralDetail.size() > 0) {
			addIntegralDetail = addIntegralDetail.stream().map(integralDetailModel -> {
				integralDetailModel.setGivingUserId(givingUserId);
				integralDetailModel.setCreator(givingUserId);
				integralDetailModel.setOperator(givingUserId);
				return integralDetailModel;
			}).collect(Collectors.toList());
			// String givingUserId="91T5M5oxNCEef1kLLFtKVo";
			IntegralResult integralResult = addPeoplesIntegral(addIntegralDetail, givingUserId);
			if("0000".equals(integralResult.getCode())){
				addFlag=true;
			}
		}else{
			addFlag=true;
		}
		if(addFlag&&milFlag){
			return IntegralResult.build(IntegralResult.SUCCESS,"加分成功！");
		}else{
			return IntegralResult.build(IntegralResult.FAIL,"加分失败！");
		}

	}

	/**
	 * 根据SecretID和SecretKey查询项目的信息-曹祥铭
	 * @param secretId 项目唯一标识
	 * @return 项目信息
	 */
	public ProjectPluginModel queryProjectInfo(String secretId){
		return	projectDao.queryProjectInfo(secretId);
	}
	/**
	 * 获取群id
	 */
	//TODO:
	public List<DingMessageEntity> getGroupId(List<String> dingIds) {

		HttpUtils http =
				HttpUtils.get("https://oapi.dingtalk.com/gettoken?appkey=dingt452prfmxlwv9dsx&appsecret=HojvOyIB0eVdmLurfK4d8RNkxO_2JviKyT4MsKkpWVt81NkKRiP0Q0X_gXQlCKR-");
		http.addHeader("Content-Type","application/json;charset=utf-8");
		ResponseWrap execute = http.execute();
		String resultStr = execute.getString();
		Object parse = JSONObject.parse(resultStr);
		JSONObject jsonObject = (JSONObject) parse;
		String token = jsonObject.getString("access_token");
		String url = "https://oapi.dingtalk.com/chat/create?access_token=" + token;

		RestTemplate restTemplate = new RestTemplate();

		List<DingMessageEntity> dingMessageEntityList = dingMessageDao.getGroupId(dingIds);
		List<DingMessageEntity> updataeDingMessageList = new ArrayList<>();
		List<String> userid = new ArrayList<>();
		dingMessageEntityList.stream().map(dingMessageEntity -> {
			if ("0".equals(dingMessageEntity.getGroupId())) {
				JSONObject jsonOb = new JSONObject();
				jsonOb.put("name", "积分红包通知群");
				jsonOb.put("owner", dingMessageEntity.getUserId());
				userid.add(dingMessageEntity.getUserId());
				jsonOb.put("useridlist", userid);

				//获取返回数据
				JSONObject resultJson = restTemplate.postForEntity(url, jsonOb,JSONObject.class).getBody();
				//提取Json串中数据
				String chatid = (String) resultJson.get("chatid");
				dingMessageEntity.setGroupId(chatid);
				dingMessageEntity.setGroupName("积分红包接收群");
				updataeDingMessageList.add(dingMessageEntity);
				userid.clear();
			}
			return dingMessageEntity;
		}).collect(Collectors.toList());
		//更新数据
		if(updataeDingMessageList.size()>0) {
			boolean updateGroup = dingMessageDao.insertGroup(updataeDingMessageList);
		}
		//if判断
		return dingMessageEntityList;
	}
}
