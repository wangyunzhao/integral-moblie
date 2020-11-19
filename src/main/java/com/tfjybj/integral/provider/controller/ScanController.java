package com.tfjybj.integral.provider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = {"微信接口"})
@RequestMapping(value = "/scan")
@RestController
@Slf4j
public class ScanController {
    /**
     *调用微信扫一扫 崔晓鸿 2019年9月15日17:36:36
     * @param currentPageUrl
     * @return Map
     */
    @GetMapping("/scan")
    @ApiOperation(value = "手机端扫一扫")
    public Map scan(@ApiParam(value = "调用微信接口的URL", required = true) @RequestParam("currentPageUrl") String currentPageUrl) {
        String appId = "wx2b9d806c89ac57dc";
        String appSecret = "00a66dec581524bc18cc9cc2b8af8767";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        //根据aapId和appSecret获得access_token(使用凭证)
        String res = sendGetRequest(url);
        com.alibaba.fastjson.JSONObject userInfo = com.alibaba.fastjson.JSON.parseObject(res);
        String accessToken = userInfo.getString("access_token");

        //根据access_token获得jspApi_ticket
        String jsApiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        String res1 = sendGetRequest(jsApiUrl);
        com.alibaba.fastjson.JSONObject userInfo1 = com.alibaba.fastjson.JSON.parseObject(res1);
        System.out.println(userInfo1.toString());
        String jsapiTicket = userInfo1.getString("ticket");

        Map<String, String> map = sign(jsapiTicket, currentPageUrl);
        map.put("appId", appId);
        return map;
    }

    private Map<String, String> sign(String jsApiTicket, String urlCurrentPage) {
        String url = null;
        try {
            url = URLDecoder.decode(urlCurrentPage, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> ret = new HashMap<>(10);
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("ticket", jsApiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private String sendGetRequest(String getUrl) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            InputStreamReader isr = new InputStreamReader(url.openStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
