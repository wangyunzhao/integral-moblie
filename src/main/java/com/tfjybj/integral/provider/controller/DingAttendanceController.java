package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.model.DingIntegralModel;
import com.tfjybj.integral.model.DingMonthDetailIntegralModel;
import com.tfjybj.integral.model.LoginResultModel;
import com.tfjybj.integral.provider.service.DingAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"钉钉考勤"})
@RequestMapping(value = "/dingAttendance")
@RestController
public class DingAttendanceController {
    @Resource
    private DingAttendanceService dingAttendanceService;
    /**
     * 手机端，根据用户id查询该人的每天的加分详情
     * 崔晓鸿，2019年9月10日16:19:26
     *
     * @return
     */
    @ApiOperation(value = "根据用户id查询该人的每天的加分详情")
    @GetMapping(value = {"/findMyIntegral"})
    //@GetMapping(value = {"/findMyIntegral?userId={userId}&pageNum={pageNum}&pageSize={pageSize}"})
//    @GetMapping(value = {"/findMyIntegral/userId={userId}/pageNum={pageNum}/pageSize={pageSize}"})
//    public IntegralResult<List<DingIntegralModel>> findMyIntegral(
//            @ApiParam(value = "用户id", required = true) @PathVariable String userId,
//            @ApiParam(value = "pageNum", required = true) @PathVariable int pageNum,
//            @ApiParam(value = "pageSize", required = true) @PathVariable int pageSize) {
//        List<DingIntegralModel> recordDetailModels = detailRecordService.findMyIntegral(userId, pageNum, pageSize);
//        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
//    }
    public IntegralResult<List<DingIntegralModel>> findMyIntegral(
            //@ApiParam(value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(value = "pageNum", required = true) @RequestParam int pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam int pageSize) {
        //String userId="CErYcAr19C7hPyC4LMX9BS";
        String userId= UserUtil.getCurrentUser().getUserId();
        int frontNum=(pageNum-1)*pageSize;
        List<DingIntegralModel> recordDetailModels = dingAttendanceService.findMyIntegral(userId, frontNum, pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
    }
    /**
     * 手机端，根据用户id查询该人所在期的所有人的这个月的积分和上个月的积分
     * 崔晓鸿，2019年9月11日22:55:24
     *
     * @return
     */
    @ApiOperation(value = "根据用户id查询该人所在期的所有人的这个月的积分和上个月的积分及该人的加分详情")
    @GetMapping(value = {"/findAllIntegral"})
    public IntegralResult<List<DingMonthDetailIntegralModel>> findAllIntegral(
//            @ApiParam(value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(value = "pageNum", required = true) @RequestParam int pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam int pageSize) {
        //String userId="CErYcAr19C7hPyC4LMX9BS";
        String userId= UserUtil.getCurrentUser().getUserId();
        List<DingMonthDetailIntegralModel> recordDetailModels = dingAttendanceService.findAllIntegral(userId, pageNum, pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
    }

    /**
     * 手机端，根据userdingid查询usercode
     * 崔晓鸿，2019年9月10日16:19:26
     *
     * @return
     */
    @ApiOperation(value = "根根据userdingid查询usercode")
    @GetMapping(value = {"/findUsercodeByUserdingid"})
    public IntegralResult findUsercodeByUserdingid(
            @ApiParam(value = "用户Userdingid", required = true) @RequestParam String userDingId ) {

        LoginResultModel loginResultModel = dingAttendanceService.findUsercodeByUserdingid(userDingId);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束",loginResultModel.getData());
    }
}
