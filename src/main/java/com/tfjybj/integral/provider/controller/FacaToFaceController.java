package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.tfjybj.integral.model.AddIntegralPeopleAndStateTipModel;
import com.tfjybj.integral.model.FaceToFaceModel;
import com.tfjybj.integral.model.FaceToFaceTipsModel;
import com.tfjybj.integral.provider.service.FacaToFaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.faces.view.facelets.Facelet;

@Api(tags = {"面对面接口"})
@RequestMapping(value  = "FaceToFace")
@RestController
public class FacaToFaceController {

    @Resource
    private FacaToFaceService facaToFaceService;
    /**
    * @Description: 查询相同Key值的所有人
    * @Param:
    * @return:
    * @Author: 王云召
    * @Date: 2019/9/12
    */
    @ApiOperation(value = "查询相同Key值的所有人",notes = "请输入Key值")
    @PostMapping(value = "/findByKeyText")
    public IntegralResult<FaceToFaceModel> findByKeyText(@RequestBody FaceToFaceTipsModel faceToFaceTipsModel){
        return facaToFaceService.findByKeyText(faceToFaceTipsModel);
    }
    /**
     * @Description: 查询加分人和加分状态
     * @Param:
     * @return:
     * @Author: 王云召
     * @Date: 2019年9月26日11:36:48
     */
    @ApiOperation(value = "查询加分人和加分状态",notes = "请输入参数值")
    @PostMapping(value = "/addIntegralPeopleAndState")
    public IntegralResult addIntegralPeopleAndState(
            @RequestBody AddIntegralPeopleAndStateTipModel addIntegralPeopleAndStateTipModel){
        return facaToFaceService.addIntegralPeopleAndState(addIntegralPeopleAndStateTipModel);
    }
}
