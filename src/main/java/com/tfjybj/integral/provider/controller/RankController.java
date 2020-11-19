package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;

import com.tfjybj.integral.model.MyRankModel;
import com.tfjybj.integral.provider.service.RankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "排行榜")
@RequestMapping("/rankingList")
@RestController
public class RankController {
    @Autowired
    private RankService rankService;
    /**
     * @Description: 查询所有人和个人总积分排名信息
     * @Param:
     * @return:
     * @Author: 王梦瑶
     * @Date: 2019/9/12
     */
    @ApiOperation(value = "查询所有人和个人总积分排名")
    @GetMapping(value = "/getPageRank")
    public IntegralResult getPageRank(@ApiParam(value = "pageSize") @RequestParam Integer pageSize ,@ApiParam(value = "pageNo") @RequestParam Integer pageNo){
        MyRankModel list = rankService.getPageRank(pageSize,pageNo);
        return IntegralResult.build(IntegralResult.SUCCESS,"成功",list);
    }

    /**
     * @Description: 查询所在部门所有人和个人总积分排名信息
     * @Param:
     * @return:
     * @Author: 王梦瑶
     * @Date: 2019/9/12
     */
    @ApiOperation(value = "查询所在部门所有人和个人总积分排名")
    @GetMapping(value = "/getDepRank")
    public IntegralResult getDepRank(Integer pageSize , Integer pageNo){
        MyRankModel list = rankService.getDepRank(pageSize,pageNo);
        return IntegralResult.build(IntegralResult.SUCCESS,"成功",list);
    }
}
