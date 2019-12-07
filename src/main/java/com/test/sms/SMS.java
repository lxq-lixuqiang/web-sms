package com.test.sms;

import com.test.utils.HttpClient;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.util.HashMap;
import java.util.Map;

public class SMS {

    public static void main(String[] args) {
        //sendSMS();//发送短信
        selectCount();//查询短信余额

    }

    public static void selectCount(){
        String smsUrl="https://api.mysubmail.com/balance/sms.json";//【主】查询短信数量地址
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("appid","44009");  //appid
        param.put("signature","18ab9e7f0da516721df4636d0f40f57d"); //应用密匙 或 数字签名
        JSONObject jsonParam = JSONObject.fromObject(param);
        String message = HttpClient.sendPost(smsUrl,jsonParam.toString());
        JSONObject jsonMessage = JSONObject.fromObject(message);
        System.out.println(jsonMessage.toString());
        if(jsonMessage.get("status").equals("success")){
            System.out.println("你的短信条数余额："+jsonMessage.get("balance"));
        }else{
            smsUrl="https://api.submail.cn/balance/sms.json";//【备】查询短信数量地址
            message = HttpClient.sendPost(smsUrl,jsonParam.toString());
            jsonMessage = JSONObject.fromObject(message);
            System.out.println(jsonMessage.toString());
            if(jsonMessage.get("status").equals("success")){
                System.out.println("你的短信条数余额："+jsonMessage.get("balance"));
            }else{
                System.out.println("短信条数查询失败，请稍后再试！");
            }
        }

    }

    //发送短信
    public static void sendSMS(){
        String smsUrl="https://api.submail.cn/message/send.json";//【主】发送短信地址
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("appid","44009");  //appid
        param.put("to","13881123204"); //发送的短信手机号
        param.put("content","【MyWeb】您的短信验证码：3721，请在1分钟内输入。");//发送的短信内容
        param.put("signature","18ab9e7f0da516721df4636d0f40f57d"); //应用密匙 或 数字签名
        JSONObject jsonParam = JSONObject.fromObject(param);
        String message = HttpClient.sendPost(smsUrl,jsonParam.toString());
        JSONObject jsonMessage = JSONObject.fromObject(message);
        System.out.println(jsonMessage.toString());
        if(jsonMessage.get("status").equals("success")){
            System.out.println("发送成功！");
        }else{
            smsUrl="https://api.submail.cn/message/send.json";//【备】发送短信地址
            message = HttpClient.sendPost(smsUrl,jsonParam.toString());
            jsonMessage = JSONObject.fromObject(message);
            System.out.println(jsonMessage.toString());
            if(jsonMessage.get("status").equals("success")){
                System.out.println("发送成功！");
            }else{
                System.out.println("发送失败，请稍后再试！");
            }
        }
    }
}
