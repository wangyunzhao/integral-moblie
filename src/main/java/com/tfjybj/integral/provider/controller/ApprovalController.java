package com.tfjybj.integral.provider.controller;

import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.tfjybj.integral.entity.ApprovalIntegralEntity;

import com.tfjybj.integral.model.*;
import com.tfjybj.integral.provider.service.ApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import java.util.List;

@Api(tags = {"审批接口"})
@RequestMapping(value = "/approval")
@RestController
@Slf4j
public class ApprovalController {
    @Resource
    private ApprovalService approvalService;
    @ApiOperation(value = "根据申请人id和审批人姓名进行模糊查询")
    @GetMapping (value = {"/findAllIntegralApplyByapprovalpersonAndcreatorid"})
    public IntegralResult<ApprovalIntegralModel> findAllIntegralApplyByapprovalpersonAndcreatorid(@RequestParam String approvalPerson,int pageNum){
        //调用Service进行查询
        // pageSize默认为10
        int pageSize=10;
        List<ApprovalIntegralModel>  approvalIntegralModelList=approvalService.findAllIntegralApplyByApprovalPersonAndCreatorId(approvalPerson,pageNum,pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",approvalIntegralModelList);
    }
    @ApiOperation(value = "根据审批人id和申请人姓名进行模糊查询")
    @GetMapping (value = {"/findAllIntegralApplyByApprovalPersonIdAndCreator"})
    public IntegralResult<ApprovalIntegralModel> findAllIntegralApplyByApprovalPersonIdAndCreator(@RequestParam String creator,int pageNum){
        //默认页数大小为10
        int pageSize=10;
        List<ApprovalIntegralModel>  approvalIntegralModelList=approvalService.findAllIntegralApplyByApprovalPersonIdAndCreator(creator,pageNum,pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",approvalIntegralModelList);
    }
    @ApiOperation(value = "根据指定条件和审批人id搜索审批")
    @GetMapping (value = {"/findTypeIntegralApplyByApprovalPerson"})
    public IntegralResult<ApprovalIntegralModel> findTypeIntegralApplyByApprovalPerson(@RequestParam int approvalStatus,int pageNum){
        // 默认分页大小为10
        int pageSize=10;
        List<ApprovalIntegralModel>  approvalIntegralModelList=approvalService.findTypeIntegralApplyByApprovalPerson(approvalStatus,pageNum,pageSize);
        return IntegralResult.build("0000","查询成功",approvalIntegralModelList);
    }
    @ApiOperation(value = "根据指定条件和申请人id搜索审批")
    @GetMapping (value = {"/findTypeIntegralApplyByCreator"})
    public IntegralResult<ApprovalIntegralModel> findTypeIntegralApplyByCreator(@RequestParam int approvalStatus,int pageNum){
        // 默认分页大小为10；
        int pageSize=10;
        List<ApprovalIntegralModel>  approvalIntegralModelList=approvalService.findTypeIntegralApplyByCreator(approvalStatus,pageNum,pageSize);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",approvalIntegralModelList);
    }
    @ApiOperation(value="审批界面加载")
    @PostMapping(value = {"/selectApprovalLoading"})
    public IntegralResult<ApprovalLoadingModel> selectApprovalLoading(@RequestBody  ApprovalParamModel approvalParamModel){
        ApprovalLoadingModel approvalLoadingModelList = approvalService.selectApprovalLoading(approvalParamModel);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",approvalLoadingModelList);
    }

    /**
     * 进行审批-曹祥铭-2019年9月13日16:50:01
     * @param  approvalModel 参数Model
     * @return
     */
    @ApiOperation(value = "审批")
    @PostMapping(value = {"/approval"})
    public IntegralResult approval(@RequestBody ApprovalModel approvalModel){
        boolean IsApproval=approvalService.approval(approvalModel);
        //判断是否审批成功
        if (IsApproval){
            return IntegralResult.build(IntegralResult.SUCCESS,"审批成功");
        }else{
            return IntegralResult.build(IntegralResult.FAIL,"审批失败");
        }

    }

    /**
     * 插入审批
     * @param approvalIntegralEntity 审批实体
     */

    @ApiOperation(value = "插入审批")
    @PostMapping(value = {"/insertApproval"})
    public IntegralResult insertApproval(@RequestBody ApprovalIntegralEntity approvalIntegralEntity){
       boolean flag= approvalService.insertApproval(approvalIntegralEntity);
       if (flag){
           return IntegralResult.build(IntegralResult.SUCCESS,"发起审批成功");
       }else{
           return  IntegralResult.build(IntegralResult.FAIL ,"发起审批失败");
       }

    }
    @ApiOperation(value = "查询加分类型")
    @GetMapping(value = {"/selectTypeKey"})
    public IntegralResult<List<TypeKeyModel>> selectTypeKey(){
       List<TypeKeyModel>  typeKeyModelList= approvalService.selectTypeKey();
       return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",typeKeyModelList);

    }


}
