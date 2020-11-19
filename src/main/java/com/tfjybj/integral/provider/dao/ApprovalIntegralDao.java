package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.entity.ApprovalIntegralEntity;
import com.tfjybj.integral.model.ApprovalIntegralModel;
import com.tfjybj.integral.model.ApprovalLoadingListModel;
import com.tfjybj.integral.model.ApprovalParamModel;
import com.tfjybj.integral.model.ApprovalTypeKeyModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApprovalIntegralDao接口
 * approvalIntegral表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("approvalIntegralDao")
public interface ApprovalIntegralDao {
    /**
     * 根据申请人id和审批人进行模糊搜索-曹祥铭-2019年9月12日16:05:06
     * @param
     * @return 审批集合
     */
	List<ApprovalIntegralModel> findAllIntegralApplyByapprovalpersonAndcreatorid(String creatorId,String approvalPerson,int pageNum,int pageSize);

    /**
     * 根据审批人id和申请人姓名进行模糊搜索-曹祥铭-2019年9月12日16:07:46
     * @param
     * @return 审批集合
     */
	List<ApprovalIntegralModel> findAllIntegralApplyByapprovalpersonidAndcreator(String approvalPersonId,String  creator,int pageNum,int pageSize);

    /**
     * 根据指定条件和审批id搜索审批的方法-曹祥铭-2019年9月12日16:16:01
     * @param
     * @return 审批集合
     */
	List<ApprovalIntegralModel> findTypeIntegralApplyByApprovalPerson(String approvalPersonId,int approvalStatus,int pageNum,int pageSize);

    /**
     * 根据指定条件和申请人id搜索审批的方法-曹祥铭-2019年9月12日16:16:10
     * @param
     * @return 审批集合
     */
	List<ApprovalIntegralModel> findTypeIntegralApplyByCreator(String creatorId,int approvalStatus,int pageNum,int pageSize);

    /**
     * 根据creator_id和审批状态分页查询审批
     * @param approvalParamModel 查询参数
     * @return 审批集合
     */
    List<ApprovalIntegralModel> selectApprovalByStatusAndCreatorId(ApprovalParamModel approvalParamModel);

    /**
     * 根据approval_person_id和审批状态查询审批
     * @param approvalParamModel 查询参数
     * @return 审批集合
     */
    List<ApprovalIntegralModel> selectApprovalByStatusAndApprovalPersonId(ApprovalParamModel approvalParamModel);

    /**
     * 更新tik_approval_integtral表中的 approvalStatus和approvalOpinion字段
     * @param id  审批id
     * @param approvalStatus 审批状态
     * @param approvalOpinion  审批意见
     * @return 受影响的行数
     */
    int updateApprovalStatusAndOpinion(String id,Integer approvalStatus,String approvalOpinion,String updateTime);

    /**
     * 根据审批id查询审批详情
     * @param Id 审批id
     * @return 审批实体
     */
    ApprovalIntegralModel selectApprovalById(String Id);

    /**
     * 插入审批
     * @param approvalIntegralEntity 审批实体
     * @return 受影响的行数
     */
    int insertApproval( ApprovalIntegralEntity approvalIntegralEntity);

    /**
     * 查询审批加分所有加分类型
     * @return
     */
    List<ApprovalTypeKeyModel> selectTypeKey();
}
