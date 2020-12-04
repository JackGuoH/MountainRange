package com.example.demo.pojo;


import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    private String id;

    private String nickName;

    private String sex;

    private String age;

    private String openId;

    private Date createTime;

}
