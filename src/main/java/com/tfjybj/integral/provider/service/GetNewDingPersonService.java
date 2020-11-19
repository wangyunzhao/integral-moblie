package com.tfjybj.integral.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.tfjybj.integral.model.NoUserOrgModel;
import com.tfjybj.integral.provider.dao.GetNewDingPersonDao;
import com.tfjybj.integral.utils.http.HttpUtils;
import com.tfjybj.integral.utils.http.ResponseWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@Service("getNewDingPersonService")
public class GetNewDingPersonService {

    @Resource
    private GetNewDingPersonDao getNewDingPersonDao;

    @Autowired
    private NoUserOrgModel noUserOrgModel;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    {
        //获取token
        HttpUtils http =
                HttpUtils.get("https://oapi.dingtalk.com/gettoken?appkey=dingt452prfmxlwv9dsx&appsecret=HojvOyIB0eVdmLurfK4d8RNkxO_2JviKyT4MsKkpWVt81NkKRiP0Q0X_gXQlCKR-");
        http.addHeader("Content-Type", "application/json;charset=utf-8");
        ResponseWrap execute = http.execute();
        String resultStr = execute.getString();
        Object parse = JSONObject.parse(resultStr);
        JSONObject jsonObject = (JSONObject) parse;
        this.setToken(jsonObject.getString("access_token"));
    }

    /**
     * 通过子部门id更新isgiveself字段
     * @author 梁佳宝
     */
    public void allUserDingId(){
        //获取到所有子部门id
        List<Integer> dingDept = getDingDept();

        RestTemplate restTemplate = new RestTemplate();

        HashSet<String> allUserDingId = new HashSet<>();
        //遍历子部门id，获取dingid
        dingDept.stream().forEach(child->{
            String getDingIdUrl = "https://oapi.dingtalk.com/user/getDeptMember?access_token="+ token+"&deptId="+ child;
            JSONObject resultJson = restTemplate.getForEntity(getDingIdUrl, JSONObject.class).getBody();
            List<String> userIds = (List<String>) resultJson.get("userIds");
            //如果dingid不为空，则添加到集合中
            if(!CollectionUtils.isEmpty(userIds)){
                allUserDingId.addAll(userIds);
            }
        });
        Integer intresult = getNewDingPersonDao.updateIsGivingSelfZero();
        List<String> ids = getNewDingPersonDao.selectIdByDingId(allUserDingId);
        Integer integer = getNewDingPersonDao.updateIsGivingSelfById(ids);
    }
    

    /**
     * 获取所有子部门id
     * @return
     * @author 梁佳宝
     */
    public List<Integer> getDingDept() {

        //获取所有部门id进行筛选
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://oapi.dingtalk.com/department/list_ids?access_token=" + token + "&id=1";
        JSONObject resultJson = restTemplate.getForEntity(url, JSONObject.class).getBody();

        List<Integer> subDeptIdListist = (List<Integer>) resultJson.get("sub_dept_id_list");
        //获取没用的部门id并移除
        List<Integer> deptlist = noUserOrgModel.getDept();
        subDeptIdListist.removeAll(deptlist);

        List<Integer> allSonDept = new ArrayList<>();
        //将根目录部门id添加进去
        allSonDept.add(1);
        //遍历根目录下的几个部门
        subDeptIdListist.stream().forEach(item ->{

            String allSonDeptUrl = "https://oapi.dingtalk.com/department/list?access_token="+ token +"&id="+item;
            JSONObject sonDeptJson = restTemplate.getForEntity(allSonDeptUrl,JSONObject.class).getBody();
            List<Object> department = (List<Object>) sonDeptJson.get("department");

            //如果部门下有子部门,获取子部门id添加到子部门集合中
            if (!CollectionUtils.isEmpty(department)){
                for (Object object:department){
                    JSONObject jsObject = JSONObject.parseObject(JSONObject.toJSONString(object));
                    Integer id = jsObject.getInteger("id");
                    allSonDept.add(id);
                }
            }else{
                //如果部门下没有子部门,直接将该部门id添加到子部门集合中
                allSonDept.add(item);
            }
        });
        return allSonDept;
    }
}