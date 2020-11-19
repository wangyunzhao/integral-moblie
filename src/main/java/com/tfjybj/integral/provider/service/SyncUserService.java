package com.tfjybj.integral.provider.service;


import com.tfjybj.integral.provider.dao.SyncUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * 更新已有数据tik_user表和插入没有的数据到tik_user表
 * 梁佳宝 2020年6月13日10点33分
 */
@Service("syncUserService")
public class SyncUserService {
    @Resource
    private SyncUserDao syncUserDao;


    /**
     * 更新已有数据tik_user表和插入没有的数据到tik_user表
     * @throws ParseException
     */
    public void syncUser() {
        String schoolNo = "666888";
        updateTikBySchoolNo(schoolNo);
        insertTikUser(schoolNo);
        updateIsDelete();
    }

    /**
     * 更新已有数据tik_user表
     * @param schoolNo
     */
    public void updateTikBySchoolNo(String schoolNo) {
        syncUserDao.updateTikBySchoolNo(schoolNo);
    }

    /**
     * 插入没有的数据到tik_user表
     * @param schoolNo
     */
    public void insertTikUser(String schoolNo) {
        syncUserDao.insertTikUser(schoolNo);
    }

    /**
     * 更新Is_delete数据
     */
    public void updateIsDelete(){
        syncUserDao.updateIsDelete();
    }

    /**
     * 更新积分没用人员数据
     */
    public void updateUselessIsdelete(){
        List<String> list = syncUserDao.selectUselessIsdelete();
        syncUserDao.updateUselessIsdelete(list);
    }
}
