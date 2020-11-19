package com.tfjybj.integral.constant;

/**
 * @program: integralV3.0-sprint1.0
 * @description: service错误消息
 * @author: 赵雷
 * @create: 2019-10-05 09:57
 **/
public class ErrorMessageConstant {
	public static final String AddPoints_Point_Less="该用户总积分不足";
	public static final String AddPoints_GivingRecord_Fail="存入Redis被加分人的接收记录失败";
	public static final String AddPoints_GiviedRecord_Fail="存入Redis加分人送分记录失败";
	public static final String AddPoints_Params_Exception="加分参数异常";
	public static final String MinusPoints_GiviedRecord_Fail="存入Redis加分人减分记录失败";
	public static final String MinusPoints_Params_Exception="减分参数异常";
}
