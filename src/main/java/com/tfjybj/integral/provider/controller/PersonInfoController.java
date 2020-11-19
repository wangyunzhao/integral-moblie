package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.tfjybj.integral.model.UserTagModel;
import com.tfjybj.integral.provider.service.PersonInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"账户与安全接口"})
@RequestMapping(value  = "PersonInfo")
@RestController
public class PersonInfoController {
    @Resource
    private PersonInfoService personInfoService ;
    /**
     * 根据用户code查询角色信息
     * @param userCode
     * @return List<UserTagModel>
     * @author  王璐
     * @since 2018-10-30 15:51:14
     */
    @ApiOperation(value="根据用户code查询角色属性",notes="请输入用户code进行查询")
    @GetMapping(value={"/queryUserTag"})
    public IntegralResult<UserTagModel> queryUserTag(
            @ApiParam(value = "用户code", required = true) @RequestParam("userCode") String userCode,@RequestHeader(value="Authorization")String authorization){
        UserTagModel userTagModel= personInfoService.queryUserTag(userCode,authorization);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功", userTagModel);
    }
}
