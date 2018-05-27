package com.dhlk.airsensor.biz.service.wservice.impl;

import com.dhlk.airsensor.biz.dao.UpdateSensorMapper;
import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import com.dhlk.airsensor.biz.model.bean.SensorInputBean;
import com.dhlk.airsensor.biz.service.wservice.SensorService;
import com.dhlk.airsensor.conf.schedule.ScheduleAirSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    @Autowired
    UpdateSensorMapper insertSensorMapper;

    @Autowired
    ScheduleAirSensor scheduleAirSensor;

    /**
     * 传暗器温度的设定和空调温度的设定
     *
     * @param sensorInputBean
     * @return
     */
    @Override
    public String sensorAirService(SensorInputBean sensorInputBean) {

        SensorInfo sensorInfo = SensorServiceImplHelper.dealAirAddr(sensorInputBean);
        int result = insertSensorMapper.updateSensorInfo(sensorInfo);
        List<SenInfo> list = SensorServiceImplHelper.deal(sensorInputBean);
        if (list.size() == 0) {
            return null;
        }
        int resultSec = insertSensorMapper.insertSenInfo(list);
        //重新启动定时器
        scheduleAirSensor.restartCron();
        if (result == 1 && resultSec == 1) {
            return "ok";
        }
        return null;
    }
}
