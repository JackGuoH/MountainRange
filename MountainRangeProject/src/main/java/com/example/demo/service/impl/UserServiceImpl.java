package com.example.demo.service.impl;

import com.example.demo.dao.UserDAO;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Integer getUserId() {
        return userDAO.getUserId();
    }


    @Override
    public Integer addUser(String id, String nickName, String sex, String openId,String createTime) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("nickName",nickName);
        map.put("sex",sex);
        map.put("openId",openId);
        map.put("createTime",createTime);
        return userDAO.addUser(map);
    }
}
