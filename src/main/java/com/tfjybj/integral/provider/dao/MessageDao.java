package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.entity.MessageEntity;
import com.tfjybj.integral.model.MessageModel;
import com.tfjybj.integral.model.MessageParamModel;
import com.tfjybj.integral.model.RedMessageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MessageDao接口
 * message表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("messageDao")
public interface MessageDao{
    /**
     * 向tik_message表中插入消息
     * @param messages
     * @return
     */
    int insertMessages(@Param("messages") List<MessageEntity> messages);
    /**
     * 向tik_message表中插入消息
     * @param messages
     * @return
     */
    int insertRedEnvelopesMessages(@Param("messages") List<MessageEntity> messages);
    /**
     * 根据userId查询用户所有消息
     * @param userId 用户id
     * @return
     */
    List<MessageModel> selectMessageByAcceoptId (String userId);

    /**
     * 根据userId和消息Id更新消息为已读
     * @param userId 用户id
     * @param Id 消息id
     */
    int updateIsRead(String userId,String Id);

    /**
     * 批量更新消息为已读
     * @param userId
     * @return
     */
    int updateAllIsRead(String userId,List<MessageEntity> messageEntityList);

	Integer queryUnreadMessage(String userId);

    Integer queryUnreadRedMessage(String userId);

    /**
     * 根据userID和消息ID修改删除状态
     */
	void updateIsDelete (String userId,String Id);

    List<RedMessageModel> selectRedMessageByAcceptId(String userId);
}
