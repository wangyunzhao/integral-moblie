package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tfjybj.integral.entity.MessageEntity;
import com.tfjybj.integral.model.MessageModel;
import com.tfjybj.integral.model.MessageParamModel;
import com.tfjybj.integral.model.RedMessageModel;
import com.tfjybj.integral.provider.dao.MessageDao;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dmsdbj.cloud.tool.business.BaseService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageService {
    @Autowired
    RedisUtil redisUtil;
    @Resource
    private MessageDao messageDao;

    /**
     * 插入消息-曹祥铭-2019年9月14日10:36:20
     *
     * @param messages 消息实体
     */
    public int insertMessages(@Param("messages") List<MessageEntity> messages) {
        //向message表中插入数据，可能是多条
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).setId(IdWorker.getIdStr());
        }
        int flag = messageDao.insertMessages(messages);
        return flag;
    }
    public int insertRedMessages(@Param("messages") List<MessageEntity> messages) {
        //向message表中插入数据，可能是多条
       messages= messages.stream().peek(e->e.setId(IdWorker.getIdStr())).collect(Collectors.toList());
        int flag = 0;
        try {
            flag = messageDao.insertMessages(messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新消息为已读
     *
     * @param userId 用户id
     * @param Id
     */
    public int updateIsRead(String userId, String Id) {
        return messageDao.updateIsRead(userId, Id);

    }

    /**
     * 批量更新消息为已读-曹祥铭-2019年10月8日08:30:34
     */
    public int  updateAllIRead(String userId, List<MessageEntity> messageEntityList) {
        return  messageDao.updateAllIsRead(userId,messageEntityList);
    }

    /**
     * 根据userid查询用户的消息
     *
     * @param userId 用户id
     * @return
     */
    public List<MessageModel> selectMessageByAcceoptId(String userId) {

        List<MessageModel> messageModelList = messageDao.selectMessageByAcceoptId(userId);
        return messageModelList;
    }

    /**
     * 修改消息删除状态
     */
    public void updateIsDelete(String userId, String Id) {
        messageDao.updateIsDelete(userId, Id);
    }


    public List<RedMessageModel> selectRedMessageByAcceptId(String userId) {

        List<RedMessageModel> messageModelList = messageDao.selectRedMessageByAcceptId(userId);
        return messageModelList;
    }

    public Integer queryUnreadRedMessageCount(String userId){
        return messageDao.queryUnreadRedMessage(userId);
    }
}
