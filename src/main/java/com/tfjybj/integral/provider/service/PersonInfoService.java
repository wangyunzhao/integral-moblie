package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.tfjybj.integral.entity.AllusersEntity;
import com.tfjybj.integral.model.PersonInfoModel;
import com.tfjybj.integral.model.UserTagModel;
import com.tfjybj.integral.provider.dao.TagsDao;
import com.tfjybj.integral.utils.http.HttpUtils;
import com.tfjybj.integral.utils.http.ResponseWrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import springfox.documentation.service.Tags;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service("PersonInfoService")
public class PersonInfoService {

    @Resource
    private TagsDao tagsDao;

    @Value("${permission.personinformation}")
    private String PersonInformation_Address;
    /**
    * @Description: 根据用户code角色信息
    * @Param:  userCode
    * @return:  List<UserTagModel>
    * @Author: 王云召
    * @Date: 2019/9/14
    */
    public UserTagModel queryUserTag(String userCode,String authorization) {
        //查询个人信息-调用权限接口
        String str = PersonInformation_Address+userCode;
        HttpUtils http = HttpUtils.get(str);
        //定义调用方式
        http.addHeader("Content-Type","application/json;charset =utf-8");
        http.addHeader("Authorization",authorization);
        ResponseWrap responseWrap = http.execute();
        System.out.println(responseWrap.getString());
        //将得到的json反序列化
        PersonInfoModel personInfoModel = JSON.parseObject(responseWrap.getString(),PersonInfoModel.class);

        //查询职位
        String Position = tagsDao.queryUserTag(userCode);
        UserTagModel userTagModels = new UserTagModel();
        if (Position==""|| Position==null){
            userTagModels.setPosition("职员");
        }else {
            userTagModels.setPosition(Position);
        }
        userTagModels.setEmail(personInfoModel.getData().getEmail());
        userTagModels.setName(personInfoModel.getData().getUserName());
        userTagModels.setUserCode(personInfoModel.getData().getUserCode());
        return userTagModels;
    }
}
