package com.tfjybj.integral.provider.controller;


import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.tfjybj.integral.entity.SportRecordEntity ;
import com.tfjybj.integral.model.SportRecordModel;
import com.tfjybj.integral.provider.service.DingSportService ;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.utils.cache.RedisUtil;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * SportRecordController接口
 * sportRecord
 *
 * @author 董思莲
 * @version 0.0.1
 * @since 0.0.1 2019年9月12日16:14:05
 */
@Api(tags = {"钉钉运动"})
@RequestMapping(value  = "DingSport")
@RestController
public class DingSportController {
    @Resource
    private DingSportService sportRecordService;
    /**
     * 功能描述：查询用户个人的钉钉运动数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @author 董思莲
     * @since 2019/09/11 21:31:00
     */
    @ApiOperation(value = "根据userId查询个人的钉钉运动数据", notes = "输入起始页码和每页数量")
    @GetMapping(value = {"querySelfSport"})
    public IntegralResult<SportRecordModel> querySelfSport(
            @ApiParam(value = "pageNum", required = true) @RequestParam int pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam int pageSize) {
        //从token里获取userId
          String userId = UserUtil.getCurrentUser().getUserId();
        List<SportRecordModel> selfSportResult = sportRecordService.querySelfSport(userId,pageNum,pageSize);
        if(selfSportResult.size()==0 || selfSportResult == null){
            return IntegralResult.build(IntegralResult.FAIL, "查询失败");
        }
        return IntegralResult.build(IntegralResult.SUCCESS, "查询成功", selfSportResult);



    }
}

