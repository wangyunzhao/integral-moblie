package com.tfjybj.integral.provider.controller;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.constant.ErrorCodeConstant;
import com.tfjybj.integral.constant.ErrorMessageConstant;
import com.tfjybj.integral.model.AllUserModel;
import com.tfjybj.integral.model.FaceToFaceModel;
import com.tfjybj.integral.model.FrequentUserModel;
import com.tfjybj.integral.model.IntegralDetailModel;
import com.tfjybj.integral.provider.service.AddIntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = {"加分模块-选人加分"})
@RequestMapping(value = "/addIntegralController")
@RestController
public class AddIntegralController {
	@Autowired
	private AddIntegralService addIntegralService;

	/**
	 * 根据用户ID查询该用户可赠积分
	 *
	 * @return
	 * @author 赵雷
	 * @since 2019年9月12日17:11:46
	 */
	@ApiOperation(value = "根据用户ID查询该用户可赠积分")
	@GetMapping(value = {"/findGivingById"})
	public Integer selectGivingById() {
		// String userId="91T5M5oxNCEef1kLLFtKVo";
		String userId = UserUtil.getCurrentUser().getUserId();
		return addIntegralService.selectGivingById(userId);
	}

	/**
	 * 根据用户ID查询常用人员
	 *
	 * @return
	 * @author 赵雷
	 * @since 2019年9月12日17:11:46
	 */
	@ApiOperation(value = "根据用户ID查询常用三位人员")
	@GetMapping(value = {"/queryFrequentUserMonth"})
	public IntegralResult selectFrequentUserMonth() {
		// String userId = "A4TkLeJXZ8zK8ELeJAFn7j";
		String userId = UserUtil.getCurrentUser().getUserId();
		List<FrequentUserModel> frequentUserModelList = addIntegralService.selectFrequentUserMonth(userId);
		if (CollectionUtils.isEmpty(frequentUserModelList)) {
			return IntegralResult.build(IntegralResult.FAIL, "查无数据");
		}
		// 判断常用联系人是否超过三位
		if (frequentUserModelList.size() <= 3) {
			return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", frequentUserModelList);
		}
		return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", frequentUserModelList.subList(0, 3));
	}

	/**
	 * 根据用户ID查询常用人员和组织机构
	 *
	 * @return
	 * @author 赵雷
	 * @since 2019年9月12日17:11:46
	 */
	@ApiOperation(value = "根据用户ID查询常用二十位人员和组织机构")
	@GetMapping(value = {"/queryFrequentUserAndOrganization"})
	public IntegralResult queryFrequentUserAndOrganization(@RequestHeader(value="Authorization")String authorization) {
		// String userId = "A4TkLeJXZ8zK8ELeJAFn7j";
		String userId = UserUtil.getCurrentUser().getUserId();
		List<FrequentUserModel> frequentUserModelList = addIntegralService.selectFrequentUsersMonth(userId);
		Map<String, Object> map = new HashMap<>();
		// 判断常用联系人是否超过十位
		if (frequentUserModelList.size() <= 20) {
			map.put("frequentUser", frequentUserModelList);
		} else {
			map.put("frequentUser", frequentUserModelList.subList(0, 20));
		}
		map.put("organization", addIntegralService.selectOrganizationInCompany(authorization));
		return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", map);
	}

	/**
	 * 各种形式加分
	 *
	 * @return
	 * @author 赵雷
	 * @since 2019年9月12日17:11:46
	 */
	@ApiOperation(value = "加分")
	@PostMapping(value = {"/addIntegralDetail"})
	public IntegralResult addIntegralDetail(@RequestBody List<IntegralDetailModel> integralDetailModelList) {
		if (!CollectionUtils.isEmpty(integralDetailModelList)) {
			String givingUserId=UserUtil.getCurrentUser().getUserId();
			// 完善实体的属性
			integralDetailModelList=integralDetailModelList.stream().map(integralDetailModel -> {
				integralDetailModel.setGivingUserId(givingUserId);
				integralDetailModel.setCreator(givingUserId);
				integralDetailModel.setOperator(givingUserId);
				return integralDetailModel;
			}).collect(Collectors.toList());
			// String givingUserId="91T5M5oxNCEef1kLLFtKVo";
			return addIntegralService.addPeoplesIntegral(integralDetailModelList,givingUserId);
		}
		return IntegralResult.build(ErrorCodeConstant.AddPoints_Params_Exception, ErrorMessageConstant.AddPoints_Params_Exception);
	}
	/**
	 * 各种形式减分
	 *
	 * @return
	 * @author 赵雷
	 * @since 2019年10月6日11:44:03
	 */
	@ApiOperation(value = "减分")
	@PostMapping(value = {"/minusIntegralDetail"})
	public IntegralResult minusIntegralDetail(@RequestBody List<IntegralDetailModel> integralDetailModelList) {
		if (!CollectionUtils.isEmpty(integralDetailModelList)) {
			String givingUserId=UserUtil.getCurrentUser().getUserId();
			integralDetailModelList=integralDetailModelList.stream().map(integralDetailModel -> {
				integralDetailModel.setGivingUserId(givingUserId);
				integralDetailModel.setCreator(givingUserId);
				integralDetailModel.setOperator(givingUserId);
				return integralDetailModel;
			}).collect(Collectors.toList());
			// String givingUserId="91T5M5oxNCEef1kLLFtKVo";
			return addIntegralService.minusPeoplesIntegral(integralDetailModelList,givingUserId);
		}
		return IntegralResult.build(ErrorCodeConstant.MinusPoints_Params_Exception, ErrorMessageConstant.MinusPoints_Params_Exception);
	}
    /**
     * 各种形式加减分
     *
     * @return
     * @author 王梦瑶
     * @since 2019年10月6日11:44:03
     */
    @ApiOperation(value = "加减分接口")
    @PostMapping(value = {"/addIntegral"})
    public IntegralResult addIntegral(@RequestBody List<IntegralDetailModel> integralDetailModelList) {
        if (!CollectionUtils.isEmpty(integralDetailModelList)) {
			String givingUserId = UserUtil.getCurrentUser().getUserId();

			List<IntegralDetailModel> minusIntegralDetail = integralDetailModelList.stream().filter(integralDetailModel -> integralDetailModel.getIntegral() < 0).collect(Collectors.toList());
			if (minusIntegralDetail != null && minusIntegralDetail.size() > 0) {
				minusIntegralDetail = minusIntegralDetail.stream().map(integralDetailModel -> {
					integralDetailModel.setGivingUserId(givingUserId);
					integralDetailModel.setCreator(givingUserId);
					integralDetailModel.setOperator(givingUserId);
					return integralDetailModel;
				}).collect(Collectors.toList());
				addIntegralService.minusPeoplesIntegral(minusIntegralDetail, givingUserId);
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
				return addIntegralService.addPeoplesIntegral(addIntegralDetail, givingUserId);

			}
			return  IntegralResult.build(IntegralResult.SUCCESS,"加分成功");
		}
        return IntegralResult.build(ErrorCodeConstant.AddPoints_Params_Exception, ErrorMessageConstant.AddPoints_Params_Exception);
    }

    /**
	 * @Description:
	 * @Param:
	 * @return:
	 * @Author: 王云召
	 * @Date: 2019/9/17
	 */
	@ApiOperation(value = "查询全部人员")
	@GetMapping(value = {"/queryAllUser"})
	public IntegralResult<FaceToFaceModel> queryAllUser() {
		List<AllUserModel> allUser = addIntegralService.queryAllUser();
		if (allUser.size() == 0) {
			return IntegralResult.build(IntegralResult.FAIL, "查询失败", allUser);
		}

		return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", allUser);
	}
	/**
	 * @Description:查询是否有减分权限
	 * @Param:
	 * @return:
	 * @Author: 王云召
	 * @Date: 2019年10月5日19:17:43
	 */
	@ApiOperation(value = "查询是否有减分权限")
	@GetMapping(value = {"/queryReduceIntegral"})
	public IntegralResult queryReduceIntegral() {
		String userId=UserUtil.getCurrentUser().getUserId();
		int reduceIntegralFlag = addIntegralService.queryReduceIntegral(userId);
		return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", reduceIntegralFlag);
	}

	//接口鉴权加分接口
	@ApiOperation(value = "加分接口（鉴权）")
	@PostMapping(value = {"/addIntegralByAuthentication"})
	@ResponseBody
	public IntegralResult addIntegralByAuthentication(HttpServletRequest request, String signature, String nonceStr , String timestamp,@RequestBody List<IntegralDetailModel> integralDetailModelList){
		return addIntegralService.addIntegralByAuthentication(request, signature, nonceStr, timestamp, integralDetailModelList);
	}
}
