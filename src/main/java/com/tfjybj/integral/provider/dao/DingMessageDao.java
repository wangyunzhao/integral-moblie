package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.entity.DingMessageEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 钉消息群发送消息
 */
@Repository("dingMessageDao")
public interface DingMessageDao {
    List<String> getUserId (List<String> ids);
    List<DingMessageEntity> getGroupId(@Param("dingIds")List<String> dingIds);
    boolean insertGroup(List<DingMessageEntity> DingMessageList);
}
