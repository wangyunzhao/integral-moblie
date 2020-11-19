package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSON;
import com.dmsdbj.cloud.tool.business.IntegralResult;
import com.dmsdbj.itoo.sso.utils.UserUtil;
import com.google.common.collect.Lists;
import com.tfjybj.integral.entity.ApprovalIntegralEntity;
import com.tfjybj.integral.entity.MessageEntity;
import com.tfjybj.integral.model.*;
import com.tfjybj.integral.model.ApprvalLoadingAllModel;
import com.tfjybj.integral.provider.dao.ApprovalIntegralDao;
import com.tfjybj.integral.provider.dao.MessageDao;
import com.tfjybj.integral.utils.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.ResultFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service

public class ApprovalService {
    @Autowired
    RedisUtil redisUtil;
    @Resource
    private ApprovalIntegralDao approvalIntegralDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private AddIntegralService addIntegralService;
    /**
     * 根据申请人id和审批人姓名搜索审批-曹祥铭-2019年9月12日16:57:33
     * @param
     * @return
     */
    public List<ApprovalIntegralModel> findAllIntegralApplyByApprovalPersonAndCreatorId(String approvalPerson,int pageNum,int pageSize) {
        String creatorId= UserUtil.getCurrentUser().getUserId();
        List<ApprovalIntegralModel> approvalIntegralModelList = approvalIntegralDao.findAllIntegralApplyByapprovalpersonAndcreatorid(creatorId,approvalPerson,pageNum,pageSize);
        // 将查到的加分详情转换成为list实体
        // 将查询到的加分详情，由json转换成实体
        for (int i=0; i <approvalIntegralModelList.size();i++){
            ApprvalLoadingAllModel apprvalLoadingAllModel = JSON.parseObject(approvalIntegralModelList.get(i).getIntegralDetail(),ApprvalLoadingAllModel.class);
            approvalIntegralModelList.get(i).setApprovalIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
            approvalIntegralModelList.get(i).setIntegralDetail("");
        }

        return approvalIntegralModelList;
    }

    /**
     * 根据审批人id和申请人搜索审批-曹祥铭-2019年9月12日17:39:46
     * @param
     */
    public List<ApprovalIntegralModel> findAllIntegralApplyByApprovalPersonIdAndCreator(String creator,int pageNum ,int pageSize) {
        //从token中获得userid
        String approvalPersonId=UserUtil.getCurrentUser().getUserId();
        List<ApprovalIntegralModel> approvalIntegralModelList = approvalIntegralDao.findAllIntegralApplyByapprovalpersonidAndcreator(approvalPersonId,creator,pageNum,pageSize);
        // 将查到的加分详情转换成为list实体
        // 将查询到的加分详情，由json转换成实体
        for (int i=0; i <approvalIntegralModelList.size();i++){
            ApprvalLoadingAllModel apprvalLoadingAllModel = JSON.parseObject(approvalIntegralModelList.get(i).getIntegralDetail(),ApprvalLoadingAllModel.class);
            approvalIntegralModelList.get(i).setApprovalIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
            approvalIntegralModelList.get(i).setIntegralDetail("");
        }
        return approvalIntegralModelList;
    }

    /**
     * 根据指定条件和审批人id搜索审批
     * @param
     * @return
     */
    public List<ApprovalIntegralModel> findTypeIntegralApplyByApprovalPerson(int approvalStatus,int pageNum,int pageSize) {
        // 从token中拿到userid
        String approvalPersonId=UserUtil.getCurrentUser().getUserId();
        List<ApprovalIntegralModel> approvalIntegralModelList = approvalIntegralDao.findTypeIntegralApplyByApprovalPerson(approvalPersonId,approvalStatus,pageNum,pageSize);
        // 将查到的加分详情转换成为list实体
        // 将查询到的加分详情，由json转换成实体
        for (int i=0; i <approvalIntegralModelList.size();i++){
            ApprvalLoadingAllModel apprvalLoadingAllModel = JSON.parseObject(approvalIntegralModelList.get(i).getIntegralDetail(),ApprvalLoadingAllModel.class);
            approvalIntegralModelList.get(i).setApprovalIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
            approvalIntegralModelList.get(i).setIntegralDetail("");
        }
        return approvalIntegralModelList;
    }

    /**
     * 根据指定条件和申请人id搜索审批
     * @param
     */
    public List<ApprovalIntegralModel> findTypeIntegralApplyByCreator(int approvalStatus,int pageNum,int pageSize) {
        // 从token信息中拿到userid
        String creatorId=UserUtil.getCurrentUser().getUserId();
        List<ApprovalIntegralModel> approvalIntegralModelList = approvalIntegralDao.findTypeIntegralApplyByCreator(creatorId,approvalStatus,pageNum,pageSize);
        // 将查到的加分详情转换成为list实体
        // 将查询到的加分详情，由json转换成实体
        for (int i=0; i <approvalIntegralModelList.size();i++){
            ApprvalLoadingAllModel apprvalLoadingAllModel = JSON.parseObject(approvalIntegralModelList.get(i).getIntegralDetail(),ApprvalLoadingAllModel.class);
            approvalIntegralModelList.get(i).setApprovalIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
            approvalIntegralModelList.get(i).setIntegralDetail("");
        }
        return approvalIntegralModelList;
    }

    /**
     * 审批页面加载
     * @param approvalParamModel  查询参数
     * @return
     */
    public ApprovalLoadingModel selectApprovalLoading(ApprovalParamModel approvalParamModel) {
        ApprovalLoadingModel approvalLoadingModel=new ApprovalLoadingModel();
        //查询用户有没有要处理的审批
        List<ApprovalIntegralModel> approvalLoadingListModel1=approvalIntegralDao.selectApprovalByStatusAndApprovalPersonId(approvalParamModel);
        // 查询用户有发起的审批
        List<ApprovalIntegralModel> approvalIntegralModel2=approvalIntegralDao.selectApprovalByStatusAndCreatorId(approvalParamModel);
        //根据用户审批的情况进行判断，并给出相应的返回值
        List<ApprovalIntegralModel> approvalIntegralModelList=Lists.newArrayList();
        if (approvalLoadingListModel1.size()>0) {
            //从结果表中拿到要返回的审批的详细数据
            ApprovalLoadingListModel approvalLoadingListModel=new ApprovalLoadingListModel();
            for (int i=0;i<approvalLoadingListModel1.size();i++){
                ApprovalIntegralModel approvalIntegralModel=new ApprovalIntegralModel();
                //拿到Id
                approvalIntegralModel.setId(approvalLoadingListModel1.get(i).getId());
                // 拿到审批人
                approvalIntegralModel.setApprovalPerson((approvalLoadingListModel1.get(i).getApprovalPerson()));
                //拿到状态
                approvalIntegralModel.setApprovalStatus(approvalLoadingListModel1.get(i).getApprovalStatus());
                //创建时间
                approvalIntegralModel.setCreateTime(approvalLoadingListModel1.get(i).getCreateTime());
                //发起人
                // 备注
                approvalIntegralModel.setRemark(approvalLoadingListModel1.get(i).getRemark());
                approvalIntegralModel.setCreator(approvalLoadingListModel1.get(i).getCreator());
                //加分类别
                approvalIntegralModel.setTypeKeyName(approvalLoadingListModel1.get(i).getTypeKeyName());
                // 加分详情
                ApprvalLoadingAllModel apprvalLoadingAllModel=JSON.parseObject(approvalLoadingListModel1.get(i).getIntegralDetail(),ApprvalLoadingAllModel.class);
                approvalIntegralModel.setApprovalIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
                approvalIntegralModelList.add(approvalIntegralModel);
            }
            approvalLoadingModel.setPriority(2);
            approvalLoadingModel.setList(approvalIntegralModelList);
        }else if(approvalIntegralModel2.size()>0){
            for (int i =0; i<approvalIntegralModel2.size();i++){
                ApprovalIntegralModel approvalIntegralModel=new ApprovalIntegralModel();
                //拿到Id
                approvalIntegralModel.setId(approvalIntegralModel2.get(i).getId());
                // 拿到审批人
                approvalIntegralModel.setApprovalPerson((approvalIntegralModel2.get(i).getApprovalPerson()));
                //拿到状态
                approvalIntegralModel.setApprovalStatus(approvalIntegralModel2.get(i).getApprovalStatus());
                //创建时间
                approvalIntegralModel.setCreateTime(approvalIntegralModel2.get(i).getCreateTime());
                //发起人
                approvalIntegralModel.setCreator(approvalIntegralModel2.get(i).getCreator());
                //备注
                approvalIntegralModel.setRemark(approvalIntegralModel2.get(i).getRemark());
                //加分类别
                approvalIntegralModel.setTypeKeyName(approvalIntegralModel2.get(i).getTypeKeyName());
                // 加分详情
                ApprvalLoadingAllModel apprvalLoadingAllModel=JSON.parseObject(approvalIntegralModel2.get(i).getIntegralDetail(),ApprvalLoadingAllModel.class);
                approvalIntegralModel.setApprovalIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
                approvalIntegralModelList.add(approvalIntegralModel);
            }
            approvalLoadingModel.setPriority(1);
            approvalLoadingModel.setList(approvalIntegralModelList);
        }else{
            approvalLoadingModel.setPriority(0);
        }
        return approvalLoadingModel;
    }
    /**
     * 进行审批（更改审批状态——调用加分接口——调用发送消息接口）-曹祥铭-2019年9月13日16:45:47
     * @param  approvalModel 参数model
     * @return
     */
    public boolean approval(ApprovalModel approvalModel) {
        // 最终的审批是否成功标识
        boolean ResultFlag = false;
        // 更新审批是否成功的标识
        boolean flagApproval = false;
        // 加分是否成功的标识
        boolean IsAddSucess = false;
        // 发送消息是否成功的标识
        int messageflag = 0;
        ApprvalLoadingAllModel apprvalLoadingAllModel = new ApprvalLoadingAllModel();
        //时间格式化工具
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //更改审批状态
        String id, approvalOpinion;
        Integer approvalStatus;
        String updateTime;
        //设置审批时间
        updateTime = sdf.format(date);
        //获得审批意见
        approvalOpinion = approvalModel.getApprovalOpinion();
        //获得审批状态（审批人是同意了还是拒绝了）
        approvalStatus = approvalModel.getApprovalStatus();
        //获得该条审批的Id
        id = approvalModel.getId();
        //根据审批Id查询审批的详情
        ApprovalIntegralModel approvalIntegralModel = approvalIntegralDao.selectApprovalById(id);

        int flag = approvalIntegralDao.updateApprovalStatusAndOpinion(id, approvalStatus, approvalOpinion, updateTime);
        if (flag > 0) {
            //判断审批是同意了还是被拒绝了
            if (approvalModel.getApprovalStatus() == 1 && approvalModel.getApprovalStatus() != 0) {
                //通过了加分申请
                //获得加分详情
                String integralDetail = approvalIntegralModel.getIntegralDetail();
                //将Json型的加分详情转化为实体
                apprvalLoadingAllModel = JSON.parseObject(integralDetail, ApprvalLoadingAllModel.class);

                // 调用加分接口
                ApprovalParamModel approvalParamModel = new ApprovalParamModel();
                //同意审批，调用加分
                approvalParamModel.setIntegralDetailModelList(apprvalLoadingAllModel.getIntegraldetailModeList());
                List<IntegralDetailModel> integralDetailModelList = Lists.newArrayList();
                for (int i = 0; i < approvalParamModel.getIntegralDetailModelList().size(); i++) {
                    IntegralDetailModel integralDetailModel = new IntegralDetailModel();
                    integralDetailModel.setGivingUserId(approvalIntegralModel.getApprovalPersonId());
                    integralDetailModel.setUserId(approvalParamModel.getIntegralDetailModelList().get(i).getUserId());
                    integralDetailModel.setIntegral(approvalParamModel.getIntegralDetailModelList().get(i).getIntegral());
                    integralDetailModel.setReason(approvalIntegralModel.getTypeKeyName());
                    integralDetailModel.setPluginId("pluginId_approval");
                    integralDetailModel.setCreateTime(approvalIntegralModel.getCreateTime());
                    integralDetailModelList.add(integralDetailModel);
                }
                IntegralResult  integralResult= addIntegralService.addPeoplesIntegral(integralDetailModelList,approvalIntegralModel.getApprovalPersonId());
                // 加分成功发送消息
                // --给申请人发送消息
                if (integralResult.getCode()==IntegralResult.SUCCESS) {
                    // --给被加分人发送消息
                    //拿到审批的发起人
                    //将Json型的加分详情转化为实体
                    apprvalLoadingAllModel = JSON.parseObject(integralDetail, ApprvalLoadingAllModel.class);

                    String acceptId;
                    String messageContent;
                    //获得发起人姓名
                    String userName = approvalIntegralModel.getCreator();

                    List<MessageEntity> messageEntityList = Lists.newArrayList();
                    //接受加分分值的变量
                    int integral;
                    for(int i=0;i<apprvalLoadingAllModel.getIntegraldetailModeList().size();i++) {
                        MessageEntity messageEntity = new MessageEntity();
                        acceptId = apprvalLoadingAllModel.getIntegraldetailModeList().get(i).getUserId();
                        integral = apprvalLoadingAllModel.getIntegraldetailModeList().get(i).getIntegral();
                        messageEntity.setAcceptorId(acceptId);
                        messageEntity.setMessageContent(userName + "发起了审批，为你加了" + integral + "分");
                        messageEntity.setMessageType(1);
                        messageEntity.setStartTime(sdf.format(date));
                        messageEntity.setSenderId("approval");
                        messageEntity.setEndTime(sdf.format(date));
                        //获得雪花Id
                        messageEntity.setId(IdWorker.getIdStr());
                        //将填写好的消息实体添加到List中
                        messageEntityList.add(messageEntity);
                        //消息结束时间暂未设置
                    }
                    //调用发送消息service
                    messageflag = messageDao.insertMessages(messageEntityList);
                    // 给审批发起人发送消息
                    //拿到发起人id
                    String creatorId = approvalIntegralModel.getCreatorId();
                    String approvaPersonName = approvalIntegralModel.getApprovalPerson();
                    //实例化消息实体
                    List<MessageEntity> creatorMessageEntityList = Lists.newArrayList();
                        MessageEntity creatorMessageEntity=new MessageEntity();
                        creatorMessageEntity.setSenderId("approval");
                        creatorMessageEntity.setAcceptorId(creatorId);
                        creatorMessageEntity.setMessageContent("恭喜，您发起的加分审批" + approvaPersonName + "已经通过！");
                        creatorMessageEntity.setStartTime(sdf.format(date));
                        creatorMessageEntity.setEndTime(sdf.format(date));
                        creatorMessageEntity.setMessageType(2);
                        //获得雪花Id
                        creatorMessageEntity.setId(IdWorker.getIdStr());
                        creatorMessageEntityList.add(creatorMessageEntity);
                    messageflag = messageDao.insertMessages(creatorMessageEntityList);
                    if (messageflag > 0) {
                        ResultFlag = true;
                    }
                } else {
                    approvalIntegralDao.updateApprovalStatusAndOpinion(id, 0, "", "");
                    ResultFlag = false;
                }
            } else {
                //如果拒绝的话，不调用加分接口，直接发送消息
                List<MessageEntity> refuseMessageEntityList = Lists.newArrayList();
                //获得消息接受者
                String messageAccecptId = approvalIntegralModel.getCreatorId();
                String approvalPersonId1 = approvalIntegralModel.getApprovalPerson();
                MessageEntity refuseMessageEntity=new MessageEntity();
                    refuseMessageEntity.setSenderId("approval");
                    refuseMessageEntity.setAcceptorId(messageAccecptId);
                    refuseMessageEntity.setMessageContent("很遗憾，" + approvalPersonId1 + "拒绝了你发起的审批！");
                    refuseMessageEntity.setStartTime(sdf.format(date));
                    refuseMessageEntity.setEndTime(sdf.format(date));
                    refuseMessageEntity.setMessageType(2);
                    //获得雪花Id
                    refuseMessageEntity.setId(IdWorker.getIdStr());
                refuseMessageEntityList.add((refuseMessageEntity));
                messageflag = messageDao.insertMessages(refuseMessageEntityList);
                if (messageflag > 0) {
                    ResultFlag = true;
                } else {
                    ResultFlag = false;
                }
            }
        } else {
            //没有更新成功，就直接返回审批失败
            ResultFlag = false;

        }
        return ResultFlag;
    }

    /**
     * 插入审批
     * @param approvalIntegralEntity 审批实体
     */
    public boolean insertApproval(ApprovalIntegralEntity approvalIntegralEntity){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //获得雪花id
        approvalIntegralEntity.setId(IdWorker.getIdStr());
        int flag= approvalIntegralDao.insertApproval(approvalIntegralEntity);
        boolean result=true;
        if (flag>0){
            result=true;
            // 发起审批给审批人发送消息
            MessageEntity messageEntity=new MessageEntity();
            List<MessageEntity> messageEntityList=Lists.newArrayList();
            messageEntity.setAcceptorId(approvalIntegralEntity.getApprovalPersonId());
            messageEntity.setSenderId("approval");
            messageEntity.setMessageContent(approvalIntegralEntity.getCreator()+",发起了审批，请您及时处理！");
            messageEntity.setStartTime(sdf.format(date));
            messageEntity.setEndTime(sdf.format(date));
            messageEntity.setMessageType(2);
            messageEntity.setId(IdWorker.getIdStr());
            messageEntityList.add(messageEntity);
            messageDao.insertMessages(messageEntityList);
        }else {
            result=false;
        }
        return result;
    }
    public List<TypeKeyModel> selectTypeKey(){

        List<TypeKeyModel> typeKeyModelList= Lists.newArrayList();
        List<ApprovalTypeKeyModel> approvalTypeKeyModelList= approvalIntegralDao.selectTypeKey();
        for (int i=0;i<approvalTypeKeyModelList.size();i++){
            TypeKeyModel typeKeyModel=new TypeKeyModel();
            typeKeyModel.setText(approvalTypeKeyModelList.get(i).getName());
            typeKeyModel.setValue(approvalTypeKeyModelList.get(i).getTypeKey());
            typeKeyModelList.add(typeKeyModel);
        }
        return typeKeyModelList;
    }
}
