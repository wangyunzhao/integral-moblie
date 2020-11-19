package com.tfjybj.integral.provider.controller;

import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.model.UserHomeModel;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.tfjybj.integral.model.UserHomeModel;
import com.tfjybj.integral.provider.service.HomeLoadingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Api(tags = {"首页加载接口"})
@RequestMapping(value = "/homeloading")
@RestController
@Slf4j
public class HomeLoadingController{
    @Resource
    private HomeLoadingService homeLoadingService;
    @ApiOperation(value = "根据用户id查询用户总积分、月积分、日积分以及绑定的插件")
    @GetMapping(value = {"/queryPageContentByUserId"})
    public IntegralResult<UserHomeModel> queryPageContentByUserId( ){
        //获取token信息中的userId;
        String userId = UserUtil.getCurrentUser().getUserId();

        UserHomeModel userHomeModel= homeLoadingService.queryPageContentByUserId(userId);
        return IntegralResult.build("0000","查询成功",userHomeModel);
    }
    @ApiOperation(value = "根据接受者id和消息状态查询条数")
    @GetMapping(value = {"/queryUnreadMessage"})
    public IntegralResult queryUnreadMessage( ){
        //获取token信息中的userId;
        String userId = UserUtil.getCurrentUser().getUserId();
        Integer unreadMessageCount= homeLoadingService.queryUnreadMessage(userId);
        return IntegralResult.build("0000","查询成功",unreadMessageCount);
    }
    @ApiOperation(value = "根据接受者id和消息状态查询红包条数")
    @GetMapping(value = {"/queryUnreadRedMessage"})
    public IntegralResult queryUnreadRedMessage( ){
        //获取token信息中的userId;
        String userId = UserUtil.getCurrentUser().getUserId();
        Integer unreadMessageCount= homeLoadingService.queryUnreadRedMessage(userId);
        return IntegralResult.build("0000","查询成功",unreadMessageCount);
    }
}
