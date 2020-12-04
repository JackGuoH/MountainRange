package com.example.demo.service.impl;


import com.example.demo.dao.ConsumptionDAO;
import com.example.demo.pojo.ConsumptionInfo;
import com.example.demo.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    @Autowired
    private ConsumptionDAO consumptionDAO;

    @Override
    public Integer add(List<ConsumptionInfo> consumptionInfos) {
        for (int i = 0; i < consumptionInfos.size(); i++) {
            consumptionInfos.get(i).setConsumption_time(new Date());
            consumptionInfos.get(i).setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        }
        return consumptionDAO.add(consumptionInfos);
    }


    @Override
    public List<ConsumptionInfo> getDayConsumptionInfo(String user_id ,String dayTime) {
        return consumptionDAO.getDayConsumptionInfo(user_id,dayTime);
    }

    @Override
    public List<ConsumptionInfo> getAllConsumptionInfo(String user_id) {
        return consumptionDAO.getAllConsumptionInfo(user_id);
    }

    @Override
    public Integer delConsumption(String user_id, String consumptionInfoId) {
        return consumptionDAO.delConsumption(user_id,consumptionInfoId);
    }
}
