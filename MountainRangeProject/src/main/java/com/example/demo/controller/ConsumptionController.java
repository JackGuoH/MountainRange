package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.ConsumptionInfo;
import com.example.demo.service.ConsumptionService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {


    @Autowired
    private ConsumptionService consumptionService;

    /**
     * 新增一条或多条账单记录
     *
     * @param data
     * @return
     */
    @PostMapping
    public Result add(@RequestBody String data) {
        List<ConsumptionInfo> consumptionInfos = JSON.parseArray(data, ConsumptionInfo.class);
        Integer status = consumptionService.add(consumptionInfos);
        if (status > 0) {
            return new Result(ResultInfo.SUCCESS_STATUS, ResultInfo.SUCCESS_MSG);
        }
        return new Result(ResultInfo.FAIL_STATUS, ResultInfo.FAIL_MSG);
    }


    /**
     * 查询当日账单  ||  查询所有账单
     *
     * @param user_id
     * @param btnStatus
     * @return
     */
    @GetMapping
    public Result getDayConsumptionInfo(String user_id, Integer btnStatus) {
        List<ConsumptionInfo> dayConsumptionInfo = new ArrayList<>();
        String dayTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        switch (btnStatus) {
            case 1:
                //查询当日账单
                dayConsumptionInfo = consumptionService.getDayConsumptionInfo(user_id, dayTime);
                break;
            case 2:
                //查询全部账单
                dayConsumptionInfo = consumptionService.getAllConsumptionInfo(user_id);
                break;
        }
        if (dayConsumptionInfo.size() > 0) {
            return new Result(ResultInfo.SUCCESS_STATUS, ResultInfo.SUCCESS_MSG, dayConsumptionInfo);
        }
        return new Result(ResultInfo.FAIL_STATUS, ResultInfo.FAIL_MSG);
    }


    /**
     * 删除用户选中的某一条账单记录
     *
     * @param user_id
     * @param consumptionInfoId
     * @return
     */
    @DeleteMapping
    public Result delConsumption(String user_id, String consumptionInfoId) {
        Integer status = consumptionService.delConsumption(user_id, consumptionInfoId);
        if (status > 0) {
            return new Result(ResultInfo.SUCCESS_STATUS, ResultInfo.SUCCESS_MSG);
        }
        return new Result(ResultInfo.FAIL_STATUS, ResultInfo.FAIL_MSG);
    }

}
