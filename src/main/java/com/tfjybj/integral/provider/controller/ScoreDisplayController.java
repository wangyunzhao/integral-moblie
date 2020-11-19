package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.model.*;
import com.tfjybj.integral.provider.service.ScoreDisplayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@Api(tags={"积分记录查询接口"})
@RequestMapping(value = "/scoreDisplay")
@RestController
public class ScoreDisplayController {
    @Resource
    private ScoreDisplayService scoreDisplayService;
    /**
     * 通过用户id、日期查询所得总分、收入详情和支出详情
     * @return List<IntegralColDetDayModel>
     * @author 崔晓鸿
     * @since 2019年9月12日10:50:54
     */
    @ApiOperation(value="通过用户id、日期查询所得总分、收入详情和支出详情",notes = "通过用户id、日期查询所得总分、收入详情和支出详情")
    @GetMapping(value = {"/findByUserIdTodayIntegral"})
    public IntegralResult<List<IntegralColDetDayModel>> findByUserIdTodayIntegral(
            //@ApiParam(value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(value = "创建时间", required = true) @RequestParam String createTime,
            @ApiParam(value = "pageNum", required = true) @RequestParam int pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam int pageSize) {
        //String userId="A4TkLeJXZ8zK8ELeJAFn7j";
        //通过token取得userid
        String userId= UserUtil.getCurrentUser().getUserId();
        //使用limit分页查询，利用页数和页的大小计算数据开始节点
        int frontNum=(pageNum-1)*pageSize;
        List<IntegralColDetDayModel> recordDetailModels = scoreDisplayService.findByUserIdTodayIntegral(userId,createTime,frontNum, pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
    }

    /**
     * 日积分-通过用户id、日期查询支出详情
     * @return List<IntegralColDetDayPayModel>
     * @author 崔晓鸿
     * @since 2019年9月12日17:19:14
     */
    @ApiOperation(value="通过用户id、日期查询支出详情",notes = "通过用户id、日期查询支出详情")
    @GetMapping(value = {"/findByUserIdTodayExpendIntegral"})
    public IntegralResult<List<IntegralColDetDayPayModel>> findByUserIdTodayExpendIntegral(
            //@ApiParam(value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(value = "创建时间", required = true) @RequestParam String createTime,
            @ApiParam(value = "pageNum", required = true) @RequestParam int pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam int pageSize) {
        //String userId="A4TkLeJXZ8zK8ELeJAFn7j";
        //通过token取得userid
        String userId= UserUtil.getCurrentUser().getUserId();
        //使用limit分页查询，利用页数和页的大小计算数据开始节点
        int frontNum=(pageNum-1)*pageSize;
        //调用service层，得到日积分的支出详情
        List<IntegralColDetDayPayModel> recordDetailModels = scoreDisplayService.findByUserIdTodayExpendIntegral(userId,createTime,frontNum, pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
    }

    /**
     * 通过用户id、日期查询所得总分、收入详情、支出详情、上个月各插件分、本月各插件分
     * @return List<IntegralCollectMonthModel>
     * @author 崔晓鸿
     * @since 2019年9月13日20:14:07
     */
    @ApiOperation(value="通过用户id、日期查询所得总分、收入详情、支出详情、上个月各插件分、本月各插件分",notes = "通过用户id、日期查询所得总分、收入详情、支出详情、上个月各插件分、本月各插件分")
    @GetMapping(value = {"/findByUserIdMonth"})
    public IntegralResult<List<IntegralCollectMonthModel>> findByUserIdMonth(
            //@ApiParam(value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(value = "创建时间", required = true) @RequestParam String createTime,
            @ApiParam(value = "pageNum", required = true) @RequestParam int pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam int pageSize) {
        //String userId="A4TkLeJXZ8zK8ELeJAFn7j";
        //通过token取得userid
        String userId= UserUtil.getCurrentUser().getUserId();
        //使用limit分页查询，利用页数和页的大小计算数据开始节点
        int frontNum=(pageNum-1)*pageSize;
        List<IntegralCollectMonthModel> recordDetailModels = scoreDisplayService.findByUserIdMonth(userId,createTime,frontNum, pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
    }

    /**
     *  根据userId、日期查询各插件分别所获得积分和所在部门平均值
     * @return List<IntegralCollectAllModel>
     * @author 崔晓鸿
     * @since 2019年9月14日11:54:25
     */
    @ApiOperation(value="根据userId、日期查询各插件分别所获得积分和所在部门平均值",notes = "根据userId、日期查询各插件分别所获得积分和所在部门平均值")
    @GetMapping(value = {"/findIntegralDetailByUserId"})
    public IntegralResult<List<IntegralCollectAllModel>> findIntegralDetailByUserId(
            //@ApiParam(value = "用户id", required = true) @RequestParam String userId,
            @ApiParam(value = "开始时间", required = true) @RequestParam String startTime,
            @ApiParam(value = "结束时间", required = true) @RequestParam String endTime
            ) {
        //String userId="A4TkLeJXZ8zK8ELeJAFn7j";
        //通过token取得userid
        String userId= UserUtil.getCurrentUser().getUserId();
        List<IntegralCollectAllModel> recordDetailModels = scoreDisplayService.findIntegralDetailByUserId(userId,startTime,endTime);
        return IntegralResult.build(IntegralResult.SUCCESS, "查询结束", recordDetailModels);
    }
}
