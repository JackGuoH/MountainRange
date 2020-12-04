package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

//@Mapper
public interface UserDAO {

    Integer getUserId();


    Integer addUser(Map<String,Object> record);

}
