package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    //    @Autowired
//    private UserService userService;
    private final UserService userService;

    @Value("${xcx.appId}")
    private String appId;

    @Value("${xcx.appSecret}")
    private String appSecret;

    @Value("${xcx.grantType}")
    private String grantType;


    @PostMapping("/getOpenId")
    public String getOpenId(@RequestBody String data) {
        log.info(">>>开始调用[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]方法<<<");
        JSONObject param = JSON.parseObject(data);
        log.info(">>>获取到的参数为[" + param + "]<<<");
        String code = param.get("code").toString();
        String str = "";
        try {
            //建立连接
            URL connect = new URL("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=" + grantType);
            BufferedReader buff = new BufferedReader(new InputStreamReader(connect.openStream(), Charset.forName("UTF-8")));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = buff.readLine()) != null) {
                sb.append(res.trim());
            }
            buff.close();
            str = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        log.info(">>>调用结束,返回结果为:" + str + "<<<");
        return str;
    }

    @GetMapping("/getUserId")
    public JSONObject getUserId() {
        JSONObject jsonObject = new JSONObject();
        System.out.println(appId);
        Integer userId = userService.getUserId();
        jsonObject.put("userId", userId);
        return jsonObject;
    }


    @PostMapping("/addUser")
    public Result addUser(@RequestBody String data) {
        JSONObject datas = JSON.parseObject(data);
        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String nickName = datas.getString("nickName");
        String sex = datas.getString("sex");
        String openId = datas.getString("openId");
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Integer status = userService.addUser(id, nickName, sex, openId, createTime);
        if (status > 0) {
            return new Result(ResultInfo.SUCCESS_STATUS, ResultInfo.SUCCESS_MSG);
        }
        return new Result(ResultInfo.FAIL_STATUS, ResultInfo.FAIL_MSG);
    }
}
