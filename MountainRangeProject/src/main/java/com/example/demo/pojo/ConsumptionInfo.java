package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class ConsumptionInfo {

    private String id;

    private Double money;

    private Integer consumption_lib_id;

    private Integer consumption_lib_small_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date consumption_time;

    private String remarks;

    private String user_id;

}
