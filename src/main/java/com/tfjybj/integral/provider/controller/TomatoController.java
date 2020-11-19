package com.tfjybj.integral.provider.controller;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.model.TomatoPercentModel;
import com.tfjybj.integral.provider.service.TomatoService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import com.tfjybj.integral.model.TomatoIntegralModel;
import com.tfjybj.integral.model.WeekTomatoModel;

import java.util.List;


@Api(tags={"番茄记录表接口"})
@RequestMapping(value = "/tomato")
@RestController
public class TomatoController {
    @Resource
    private TomatoService tomatoService;

    /** 
    * @Description: 查询登录用户今日积分、总积分和番茄积分详情 
    * @Param:  
    * @return:  sumIntegral，todayIntegral，todayTomatoList
    * @Author: 张文慧
    * @Date: 2019/9/15 
    */ 
    @ApiOperation(value="查询登录用户今日的番茄积分和总积分以及番茄数量和名称",notes="请填写用户id")
    @GetMapping(value = {"/queryHistory"})

    public IntegralResult queryHistory(){
        String userId= UserUtil.getCurrentUser().getUserId();
        return IntegralResult.build("0000","查询成功",tomatoService.queryHistory(userId));
    }


    /**
     * 查询登录用户本周和上周的积分
     * @author 张文慧
     * @return week,thisIntegral,beforeIntegral
     * @since 2019年9月12日11:29:51
     */
    @ApiOperation(value="查询登录用户本周和上周的积分",notes="请填写用户id")
    @GetMapping(value = {"/queryWeekIntegral"})
    public IntegralResult queryWeekIntegral(){
        String userId= UserUtil.getCurrentUser().getUserId();
        return IntegralResult.build("0000","查询成功",tomatoService.queryWeekIntegral(userId));
    }
}
