package com.example.demo.dao;

import com.example.demo.pojo.ConsumptionInfo;

import java.util.List;

public interface ConsumptionDAO {

    Integer add(List<ConsumptionInfo> consumptionInfos);

    List<ConsumptionInfo> getDayConsumptionInfo(String user_id , String dayTime);

    List<ConsumptionInfo> getAllConsumptionInfo(String user_id);

    Integer delConsumption(String user_id,String consumptionInfoId);

}
