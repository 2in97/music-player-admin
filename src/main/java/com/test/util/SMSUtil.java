package com.test.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

public class SMSUtil {

    // 替换成你的AK
    private static final String accessKeyId = "cdb9ccd42f5a421d8e8d8a7f00e431e2";// 你的accessKeyId
    private static final String accessKeySecret = "H44OpuJfkDaxQPuVyBZ4rIaPze3RLfFx";// 你的accessKeySecret
    private static final String signName = "60313b797aac4078b900b720386c03cc";// 签名
    private static final String templateCode = "M09DD535F4";// 短信模板

    private static final String host = "https://smssend.shumaidata.com";
    private static final String path = "/sms/send";
    private static final String method = "POST";
    private static final String appcode = "60313b797aac4078b900b720386c03cc";
    private static int code;

    /**
     * @Description:发送手机验证码
     * @Param:需要发送的手机号码
     * @return:OK表示成功，失败则返回失败信息
     */
    @SuppressWarnings("finally")
	public int sendMes(String phone){
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("receive", phone);
        querys.put("tag", String.valueOf((int)((Math.random() * 9 + 1) * 100000)));
        querys.put("templateId", "M09DD535F4");
        Map<String, String> bodys = new HashMap<String, String>();
        code=Integer.parseInt(querys.get("tag"));
        try {

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return code;
        }
    }

    public int getCode() {
        return code;
    }
}