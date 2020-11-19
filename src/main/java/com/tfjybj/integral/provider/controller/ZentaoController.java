package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.model.ColleagueResult.BugAndTaskModel;
import com.tfjybj.integral.model.scoreResult.ObjectTypeResultModel;
import com.tfjybj.integral.provider.service.RankService;
import com.tfjybj.integral.provider.service.ZentaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Api(tags = {"禅道接口"})
@RequestMapping(value = "/Zentao")
@RestController
public class ZentaoController {

    @Resource
    private ZentaoService zentaoService;

    @ApiOperation(value = "根据用户id和加分日期查询禅道汇总及详细数据")
    @GetMapping(value = {"/queryObjectTypeResult"})
    public IntegralResult<ObjectTypeResultModel> queryObjectTypeResult(@ApiParam(value = "加分日期") @RequestParam java.sql.Date success_time) {
        return  IntegralResult.build("0000","查询成功",zentaoService.queryObjectTypeResult(UserUtil.getCurrentUser().getUserId(),success_time));
    }

    @ApiOperation(value = "根据用户id查询项目组完成bug和task的数据")
    @GetMapping(value = {"/selectBugAndTaskModel"})
    public IntegralResult<BugAndTaskModel> selectBugAndTaskModel(){
        return IntegralResult.build("0000","查询成功",zentaoService.selectBugAndTaskModel(UserUtil.getCurrentUser().getUserId()));
    }
}
