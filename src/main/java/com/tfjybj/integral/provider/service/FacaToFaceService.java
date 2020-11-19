package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.google.common.collect.Lists;
import com.tfjybj.integral.model.AddIntegralPeopleAndState;
import com.tfjybj.integral.model.AddIntegralPeopleAndStateTipModel;
import com.tfjybj.integral.model.FaceToFaceModel;
import com.tfjybj.integral.model.FaceToFaceTipsModel;
import com.tfjybj.integral.utils.cache.RedisUtil;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfjybj.integral.utils.cache.RedisContants;

import java.security.Key;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Set;

@Service("FacaToFaceService")
public class FacaToFaceService {
    @Autowired
    RedisUtil redisUtil;
    /**
     * @Description: 查询相同Key值的所有人
     * @Param:
     * @return:
     * @Author: 王云召
     * @Date: 2019/9/12
     */
    public IntegralResult findByKeyText(FaceToFaceTipsModel faceToFaceTipsModel) {
        //获取keytext值
        String keyText = faceToFaceTipsModel.getKeyText();
        //创建一个model，接收id和name
        FaceToFaceModel faceModel = new FaceToFaceModel();
        faceModel.setId(faceToFaceTipsModel.getId());
        faceModel.setName(faceToFaceTipsModel.getName());
        //将model序列化后，存入到redis中
        redisUtil.sSet(RedisContants.faceToFace + keyText, JSON.toJSONString(faceModel));
        //设置过期时间为600秒
        redisUtil.expire(RedisContants.faceToFace + keyText, 600);

        //从redis中根据key值取出value
        Set<Object> faceToFaceModelSet = redisUtil.sGet(RedisContants.faceToFace + keyText);
        //将set反序列化并且转化成list存储
        List<FaceToFaceModel> faceToFaceModels = JSONObject.parseArray(faceToFaceModelSet.toString(), FaceToFaceModel.class);
        return IntegralResult.build("0000", "查询成功", faceToFaceModels);
    }

    /**
     * @Description: 查询加分人和加分状态
     * @Param:
     * @return:
     * @Author: 王云召
     * @Date: 2019年9月26日11:36:48
     */
    public IntegralResult addIntegralPeopleAndState(AddIntegralPeopleAndStateTipModel addIntegralPeopleAndStateTipModel) {
        //0正在加分，1加分完成，2等待被加分
        if(addIntegralPeopleAndStateTipModel.getState() == 0){
            //创建model存储正在加分人和加分的状态，0代表正在加分
            AddIntegralPeopleAndState addIntegralPeopleAndState = new AddIntegralPeopleAndState();
            //接收姓名
            addIntegralPeopleAndState.setName(addIntegralPeopleAndStateTipModel.getName());
            //接收正在状态
            addIntegralPeopleAndState.setState(addIntegralPeopleAndStateTipModel.getState());
            //接收加分分值
            addIntegralPeopleAndState.setIntegral(addIntegralPeopleAndStateTipModel.getIntegral());
            //接收加分姓名
            addIntegralPeopleAndState.setUsers(addIntegralPeopleAndStateTipModel.getUsers());
            //判断redis中有无已经存在的key
            if(redisUtil.hasKey(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey())){
                Set<Object> AddIntegralPeopleAndState = redisUtil.sGet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey());
                List<AddIntegralPeopleAndState> addIntegralPeopleAndStateList = JSONObject.parseArray(AddIntegralPeopleAndState.toString(), AddIntegralPeopleAndState.class);
                Integer num = 0;
                for (int i = 0; i <addIntegralPeopleAndStateList.size() ; i++) {
                    if(addIntegralPeopleAndStateTipModel.getName().trim().equals(addIntegralPeopleAndStateList.get(i).getName().trim())){
                        addIntegralPeopleAndStateList.remove(i);
                        addIntegralPeopleAndStateList.add(addIntegralPeopleAndState);
                        num++;
                    }
                }
                if(num==0){
                    addIntegralPeopleAndStateList.add(addIntegralPeopleAndState);
                }
                //删除之前key值对应的value
                redisUtil.del(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey());
                //将新的值存储到redis中
                for (int i = 0; i < addIntegralPeopleAndStateList.size() ; i++) {
                    redisUtil.sSet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey(), JSON.toJSONString(addIntegralPeopleAndStateList.get(i)));
                    redisUtil.expire(RedisContants.faceToFaceStae +addIntegralPeopleAndStateTipModel.getTextKey(),600);
                }
                return IntegralResult.build("0000", "查询成功", addIntegralPeopleAndStateList);
            }else {
            //将姓名和状态存储到redis中
            redisUtil.sSet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey(), JSON.toJSONString(addIntegralPeopleAndState));
            //设置过期时间十分钟
            redisUtil.expire(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey(), 600);
            //从redis中根据redis取出来
            Set<Object> AddIntegralPeopleAndStateSet = redisUtil.sGet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey());
            List<AddIntegralPeopleAndState> addIntegralPeopleAndStateList = JSONObject.parseArray(AddIntegralPeopleAndStateSet.toString(), AddIntegralPeopleAndState.class);
            return IntegralResult.build("0000", "查询成功", addIntegralPeopleAndStateList);
            }
        }
        if(addIntegralPeopleAndStateTipModel.getState() == 1){
            //加分成功后，将正在加分的数据从redis中取出来
            Set<Object> AddIntegralPeopleAndState = redisUtil.sGet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey());
            List<AddIntegralPeopleAndState> addIntegralPeopleAndStateList = JSONObject.parseArray(AddIntegralPeopleAndState.toString(), AddIntegralPeopleAndState.class);
            //将0修改为1后存储到list中
            for (int i = 0; i < addIntegralPeopleAndStateList.size(); i++) {
                if (addIntegralPeopleAndStateTipModel.getName().trim().equals(addIntegralPeopleAndStateList.get(i).getName().trim())){
                    addIntegralPeopleAndStateList.get(i).setState(addIntegralPeopleAndStateTipModel.getState());
                    addIntegralPeopleAndStateList.get(i).setIntegral(addIntegralPeopleAndStateTipModel.getIntegral());
                }
            }
            //删除之前key值对应的value
            redisUtil.del(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey());
            //将新的值存储到redis中
            for (int i = 0; i < addIntegralPeopleAndStateList.size() ; i++) {
                redisUtil.sSet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey(), JSON.toJSONString(addIntegralPeopleAndStateList.get(i)));
                 redisUtil.expire(RedisContants.faceToFaceStae +addIntegralPeopleAndStateTipModel.getTextKey(),600);
            }

            return IntegralResult.build("0000", "查询成功", addIntegralPeopleAndStateList);
        }
        if(addIntegralPeopleAndStateTipModel.getState() == 2){
            //被加分的人查询谁正在加分，将加分人和加分状态存储到redis
            Set<Object> AddIntegralPeopleAndStateSet = redisUtil.sGet(RedisContants.faceToFaceStae + addIntegralPeopleAndStateTipModel.getTextKey());
            List<AddIntegralPeopleAndState> addIntegralPeopleAndStateList = JSONObject.parseArray(AddIntegralPeopleAndStateSet.toString(), AddIntegralPeopleAndState.class);
            return IntegralResult.build("0000", "查询成功", addIntegralPeopleAndStateList);
        }else {
            //其他情况，查询失败
            return IntegralResult.build("1111", "查询失败");
        }

    }
}