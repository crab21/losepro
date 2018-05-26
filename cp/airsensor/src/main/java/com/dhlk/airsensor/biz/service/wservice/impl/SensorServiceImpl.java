package com.dhlk.airsensor.biz.service.wservice.impl;

import com.dhlk.airsensor.biz.dao.UpdateSensorMapper;
import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import com.dhlk.airsensor.biz.model.bean.SensorInputBean;
import com.dhlk.airsensor.biz.service.wservice.SensorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    @Autowired
    UpdateSensorMapper insertSensorMapper;

    @Override

    public String sensorAirService(SensorInputBean sensorInputBean) {
        String air_addrs = "";
        if (sensorInputBean.getAir_addr().contains("-")) {

            air_addrs = new Gson().toJson(sensorInputBean.getAir_addr().split("-"));
        }
        SensorInfo sensorInfo = new SensorInfo(sensorInputBean.getAddr(), air_addrs);
        int result = insertSensorMapper.updateSensorInfo(sensorInfo);
        List<SenInfo> list = SensorServiceImplHelper.deal(sensorInputBean);
        int resultSec = insertSensorMapper.insertSenInfo(list);

        if (result == 1 && resultSec == 1) {
            return "ok";
        }
        return null;
    }
}
