package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }


    @GetMapping("/getAAA")
    public String testAAA(){
        return "aaa";
    }

    @GetMapping("/getBBB")
    public String testBBB(){
        return "aaa";
    }

}
