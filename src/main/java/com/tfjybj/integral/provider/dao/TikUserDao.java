package com.tfjybj.integral.provider.dao;

import com.tfjybj.integral.entity.IntegralDetailEntity;
import com.tfjybj.integral.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TikUserDao接口
 * tikUser表
 *
 * @author 王云召
 * @version ${version}
 * @since ${version} 2019-09-11 22:13:52
 */
@Repository("tikUserDao")
public interface TikUserDao{
	/**
	 * 根据userId查询用户总积分-曹祥铭-2019年9月12日15:11:35
	 * @param userId  用户id
	 * @return  总积分
	 */
	Integer queryUserIntegralByUserId(String userId);
	/**
	 * 根据Id查询用户可赠积分-赵雷-2019年9月12日15:11:35
	 * @param id  用户id
	 * @return  总积分
	 */
	Integer selectGivingById(String id);
	/**
	 * 根据userId查询用户总积分-赵雷-2019年9月12日15:11:35
	 * @param id  用户id
	 * @return  总积分
	 */
	Integer selectIntegerById(String id);
	/**
	 * 根据Id查询用户姓名赵雷-2019年10月8日14:27:25
	 * @param userId  用户userId
	 * @return  总积分
	 */
	String selectUserName(String userId);

	List<FrequentUserModel> selectUserInfo(List<IntegralDetailEntity> integralDetailEntityList);

	/**
	 * @description: 查询用户是否有赠送自己积分的权限
	 * @param userId
	 * @return: java.lang.Integer
	 * @author: 马珂
	 * @time: 2020/8/26 16:23
	 */
	Integer selectUserGivingSelf(String userId);

	/**
	 * 查询所有人总积分排名
	 * @return
	 */

	List<RankListModel> getPageRank(@Param("pageSize") Integer pageSize, @Param("pageNo") Integer pageNo);


	/**
	 * 查询个人总积分排名
	 * @return
	 */
	IntegralRankModel getMyRank(String userId);


	/**
	 * 查询所在部门所有人总积分排名
	 */
	List<RankListModel> getDepRank(@Param("userId")String userId);

	/**
	 * 查询所在部门个人总积分排名
	 */
	IntegralRankModel getDepMyRank(@Param("userId")String userId,@Param("id")String id);

	/**
	 * 更新用户可赠积分
	 * @param userId
	 * @param givingIntegral
	 * @return
	 */
	Boolean updateGivingIntegral(@Param("userId") String userId,@Param("givingIntegral") Integer givingIntegral);

	/**
	 * 更新用户可赠积分和用户本身积分
	 * @param userId
	 * @param integral
	 * @return
	 */
	Boolean updateAllIntegral(@Param("userId") String userId,@Param("integral") Integer integral);

	Boolean updateIntegral(@Param("userId") String userId,@Param("integral") Integer integral);

	List<AllUserModel> queryAllUser();

	int queryReduceIntegral(@Param("userId") String userId);

	/**
	 * 批量更新list里面的被减分用户的积分-赵雷-2019年10月6日16:45:27
	 * @param integralDetailEntityList
	 * @return
	 */
	int updateIntegralList(List<IntegralDetailEntity> integralDetailEntityList);

	boolean updateGivingIntegralByMonth(int givingIntegral);

}
