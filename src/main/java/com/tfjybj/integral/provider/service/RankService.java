package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.tfjybj.integral.model.IntegralRankModel;
import com.tfjybj.integral.model.MyRankModel;
import com.tfjybj.integral.model.RankListModel;
import com.tfjybj.integral.provider.dao.TikUserDao;
import com.tfjybj.integral.utils.cache.RedisContants;
import com.tfjybj.integral.utils.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("rankService")
public class RankService {
    @Autowired
    private TikUserDao rankDao;

    @Autowired
    RedisUtil redisUtil;


    public MyRankModel getPageRank(Integer pageSize, Integer pageNo) {

        String userId= UserUtil.getCurrentUser().getUserId();

        /**
         * 查询所有人和个人总积分排名
         *
         */
        if (redisUtil.hasKey(RedisContants.AllMyRank + userId)) {
            List<RankListModel> rankListModels = new ArrayList<>();

            if (!redisUtil.hasKey((RedisContants.AllRank))) {
                List<RankListModel> rankListModel = rankDao.getPageRank(pageSize, pageNo);
                redisUtil.llSetAll(RedisContants.AllRank, rankListModel);
                redisUtil.expire(RedisContants.AllRank, 1800);
                List ranlistmodel = redisUtil.lGet(RedisContants.AllRank, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            } else {
                List ranlistmodel = redisUtil.lGet(RedisContants.AllRank, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            }


//            Set<Object> ranklistmodel = redisUtil.sGet(RedisContants.AllRank);
//            List<RankListModel> listModels = JSONObject.parseArray(ranklistmodel.toString(), RankListModel.class);

            String Myrankmodel = redisUtil.get(RedisContants.AllMyRank + userId);
            IntegralRankModel integralRankModel = JSON.parseObject(Myrankmodel, IntegralRankModel.class);
            MyRankModel myRankModel = new MyRankModel();
            myRankModel.setRankListModels(rankListModels);
            myRankModel.setMyName(integralRankModel.getUserName());
            myRankModel.setMyIntegral(integralRankModel.getIntegral());
            myRankModel.setMyRank(integralRankModel.getSort());
            return myRankModel;

        } else {
            List<RankListModel> rankListModels = new ArrayList<>();
            if (!redisUtil.hasKey((RedisContants.AllRank))) {
                List<RankListModel> rankListModel = rankDao.getPageRank(pageSize, pageNo);
                redisUtil.llSetAll(RedisContants.AllRank, rankListModel);
                redisUtil.expire(RedisContants.AllRank, 1800);
                List ranlistmodel = redisUtil.lGet(RedisContants.AllRank, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            } else {
                List ranlistmodel = redisUtil.lGet(RedisContants.AllRank, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            }
            //所有人存入redis
//            List<RankListModel> rankListModel = rankDao.getPageRank(pageSize, pageNo);
//            redisUtil.sSet(RedisContants.AllRank, JSON.toJSONString(rankListModel));
//            redisUtil.expire(RedisContants.AllRank, 1800);
            //个人存入redis
            IntegralRankModel myrankModel = rankDao.getMyRank(userId);
            redisUtil.set(RedisContants.AllMyRank + userId, JSON.toJSONString(myrankModel));
            redisUtil.expire(RedisContants.AllMyRank + userId, 1800);

            MyRankModel myRankModel = new MyRankModel();
            myRankModel.setRankListModels(rankListModels);
            myRankModel.setMyName(myrankModel.getUserName());
            myRankModel.setMyIntegral(myrankModel.getIntegral());
            myRankModel.setMyRank(myrankModel.getSort());
            return myRankModel;
        }
    }

    /**
     * 查询所在部门所有人和个人总积分排名
     */
    public MyRankModel getDepRank(Integer pageSize, Integer pageNo) {
        String userId= UserUtil.getCurrentUser().getUserId();
//        pageNo = (pageNo - 1) * pageSize;

        if (redisUtil.hasKey((RedisContants.DepMyRank + userId))) {
            List<RankListModel> rankListModels = new ArrayList<>();
            if (!redisUtil.hasKey((RedisContants.DepRank+userId))) {
                rankListModels = rankDao.getDepRank(userId);
                redisUtil.llSetAll(RedisContants.DepRank+userId, rankListModels);
                redisUtil.expire(RedisContants.DepRank+userId, 1800);
                List ranlistmodel = redisUtil.lGet(RedisContants.DepRank+userId, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            } else {
                List ranlistmodel = redisUtil.lGet(RedisContants.DepRank+userId, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            }

//            List ranlistmodel = redisUtil.lGet(RedisContants.DepRank, (pageNo - 1) * pageSize, (pageNo + 1) * pageSize);
////            List<String> list = JSONObject.parseArray(ranlistmodel.toString(), String.class);
//            List<RankListModel> rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);

            String Myrankmodel = redisUtil.get(RedisContants.DepMyRank + userId);
            IntegralRankModel integralRankModel = JSON.parseObject(Myrankmodel, IntegralRankModel.class);
            MyRankModel myRankModel = new MyRankModel();

            myRankModel.setRankListModels(rankListModels);
            myRankModel.setMyName(integralRankModel.getUserName());
            myRankModel.setMyIntegral(integralRankModel.getIntegral());
            myRankModel.setMyRank(integralRankModel.getSort());
            return myRankModel;

        } else {
            List<RankListModel> rankListModels = new ArrayList<>();
            if (!redisUtil.hasKey((RedisContants.DepRank+userId))) {
                rankListModels = rankDao.getDepRank(userId);
                redisUtil.llSetAll(RedisContants.DepRank+userId, rankListModels);
                redisUtil.expire(RedisContants.DepRank+userId, 1800);
                List ranlistmodel = redisUtil.lGet(RedisContants.DepRank+userId, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            } else {
                List ranlistmodel = redisUtil.lGet(RedisContants.DepRank+userId, (pageNo - 1) * pageSize, (pageNo) * pageSize - 1);
                rankListModels = JSONObject.parseArray(ranlistmodel.toString(), RankListModel.class);
            }
            //所在部门下的个人存入redis
            IntegralRankModel IntegralRankModel = rankDao.getDepMyRank(userId,userId);
            redisUtil.set(RedisContants.DepMyRank + userId, JSON.toJSONString(IntegralRankModel));
            redisUtil.expire(RedisContants.DepMyRank + userId, 1800);
            MyRankModel myRankModel = new MyRankModel();
            myRankModel.setRankListModels(rankListModels);
            myRankModel.setMyName(IntegralRankModel.getUserName());
            myRankModel.setMyIntegral(IntegralRankModel.getIntegral());
            myRankModel.setMyRank(IntegralRankModel.getSort());
            return myRankModel;
        }


    }

}
