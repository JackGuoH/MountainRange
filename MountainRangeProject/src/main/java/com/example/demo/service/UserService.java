package com.example.demo.service;

import com.example.demo.pojo.User;

import java.io.InputStreamReader;

public interface UserService {

    Integer getUserId();

    Integer addUser(String id, String nickName, String sex, String openId,String createTime);

}
