package com.tfjybj.integral.utils.cache;

public class RedisContants {
    // private String ENGLISH_USER_DATE = "EngLish:userId:Date";
    //面对面加分目录
    public static String faceToFace = "Integral:FaceToFace:keyText:";
    public static String faceToFaceStae = "Integral:FaceToFace:State:";
    //查询排行榜所有人目录和个人目录
    public static  String AllRank="Integral:All:AllRank";
    //查询排行榜所在部门所有人目录和个人目录
    public static  String DepRank="Integral:Dep:AllDep:";

    //查询排行榜所有人目录和个人目录
    public static  String AllMyRank="Integral:All:UserId:";
    //查询排行榜所有人目录和个人目录
    public static  String DepMyRank="Integral:Dep:UserId:";

    //主页加载
    public static String homeLoad="Integral:HomeLoad:userId:";
    //消息通知
    public static String message="Integral:Message:userId:";
    //审批
    public static String approval="Integral:Message:userId:";
    //钉钉运动数据目录
    public static String dingSport="Integral:dingSport:userId:";
    //月积分目录
    public static String monthIntegralOut = "Integral:MonthIntegral:Outcome:userId:";
    public static String monthIntegralInOut = "Integral:MonthIntegral:InOutcome:userId:";
    //日积分目录
    public static String dayIntegral = "Integral:DayIntegral:InOutcome:userId:";
    //总积分目录
    public static String allIntegral = "Integral:AllIntegral:InOutcome:userId:";

    //钉钉个人考勤目录
    public static String  dingAttendanceSelf="Integral:DingAttendance:self:userId:";

    //钉钉所有人考勤目录
    public static String  dingAttendanceAll="Integral:DingAttendance:all:userId:";
	// 查询最近三个联系人
	public static String frequentThreeUser="Integral:frequentThreeUser:userId:";
	// 查询最近二十个联系人
	public static String frequentUsers="Integral:frequentUsers:userId:";
	// 查询组织结构树
	public static String organizationsInCompany="Integral:organizationsInCompany";
	// 查询加分详情
	public static String addScores="Integral:addScores:givingUserId:";
	// 查询减分详情
	public static String minusScores="Integral:minusScores:givingUserId:";
	// 查询被加分详情
	public static String payIncome="Integral:payIncome:givedUserId:";
	// 用户的可赠积分
	public static String givingIntegral="Integral:userIntegral:givingIntegral:";

	//项目签名信息
    public static String signature="IntegralBackStage:authentication:signature:";

    //nonceStr信息
    public static String nonceStr="IntegralBackStage:authentication:nonceStr:";

    //ip白名单
    public static String ipList="IntegralBackStage:authentication:ipList:";

}
