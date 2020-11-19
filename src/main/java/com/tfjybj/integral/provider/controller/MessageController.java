package com.tfjybj.integral.provider.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.entity.EvaluateEntity;
import com.tfjybj.integral.entity.MessageEntity;
import com.tfjybj.integral.model.MessageModel;
import com.tfjybj.integral.model.MessageParamModel;
import com.tfjybj.integral.model.RedMessageModel;
import com.tfjybj.integral.provider.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"消息接口"})
@RequestMapping(value = "/message")
@RestController
@Slf4j
public class MessageController {
    @Resource
    private MessageService messageService;
    @ApiOperation(value = "发送消息")
    @PostMapping(value = {"/insertMessage"})
    public IntegralResult  insertMessage(@RequestBody List<MessageEntity> messages){
        messageService.insertMessages(messages);
        return IntegralResult.build(IntegralResult.SUCCESS,"发送成功");
    }

    @ApiOperation(value = "发送红包消息")
    @PostMapping(value = {"/insertRedMessage"})
    public IntegralResult  insertRedMessage(@RequestBody List<MessageEntity> messages){
        messageService.insertRedMessages(messages);
        return IntegralResult.build(IntegralResult.SUCCESS,"发送成功");
    }

    /**
     * 根据userid查询用户消息
     * @param
     * @return
     */
    @ApiOperation(value="根据userId查询用户的消息")
    @GetMapping(value={"/selectMessageByAcceptId"})
    public List<MessageModel> selectMessageByAcceptId(){
        //获取token信息中的userId
        String userId= UserUtil.getCurrentUser().getUserId();
        List<MessageModel> messageModelList=messageService.selectMessageByAcceoptId(userId);
        return messageModelList;
    }
    /**
     * 根据userid查询用户红包消息
     * @param
     * @return
     */
    @ApiOperation(value="根据userId查询用户红包的消息")
    @GetMapping(value={"/selectRedMessageByAcceptId"})
    public List<RedMessageModel> selectRedMessageByAcceptId(){
        //获取token信息中的userId
        String userId= UserUtil.getCurrentUser().getUserId();
        List<RedMessageModel> messageModelList=messageService.selectRedMessageByAcceptId(userId);
        return messageModelList;
    }

    @ApiOperation(value="根据Userid和消息Id更新消息状态为已读")
    @GetMapping(value = {"/updateIsRead"})
    public IntegralResult updateIsRead(@ApiParam(value = "Id") @RequestParam String Id){
        String userId=UserUtil.getCurrentUser().getUserId();
        messageService.updateIsRead(userId,Id);
        return IntegralResult.build(IntegralResult.SUCCESS,"修改成功");
    }
    /**
     * 批量更新消息为已读-曹祥铭-2019年10月8日08:58:45
     */
    @ApiOperation(value = "批量更新消息为已读")
    @PostMapping(value = {"/updateAllIsRead"})
    public IntegralResult updateAllIsRead(@RequestBody List<MessageEntity> messageEntityList){
        String userId=UserUtil.getCurrentUser().getUserId();
        int flag= messageService.updateAllIRead(userId,messageEntityList);
        if(flag>0){
            return IntegralResult.build(IntegralResult.SUCCESS,"更新成功");
        }else{
            return IntegralResult.build(IntegralResult.FAIL,"更新失败");
        }
    }
    /**
     * 根据userID和消息ID修改删除状态
     */
    @ApiOperation(value = "根据userId个消息Id修改消息删除状态")
    @GetMapping(value = {"/updateIsDelete"})
    public IntegralResult updateIsDelete(@ApiParam(value = "Id") @RequestParam String Id){
        //获取token信息中的userId
        String userId= UserUtil.getCurrentUser().getUserId();
        messageService.updateIsDelete(userId,Id);
        return IntegralResult.build(IntegralResult.SUCCESS,"删除成功");
    }

    /**
     * 根据userid查询用户红包消息
     * @param
     * @return
     */
    @ApiOperation(value="根据userId查询用户红包的消息的个数")
    @GetMapping(value={"/queryUnreadRedMessageCount"})
    public IntegralResult queryUnreadRedMessageCount() {
        //获取token信息中的userId
        String userId = UserUtil.getCurrentUser().getUserId();
        Integer count = messageService.queryUnreadRedMessageCount(userId);
        return IntegralResult.build(IntegralResult.SUCCESS,"查询成功",count);
    }
}
