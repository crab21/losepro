package com.dhlk.airsensor.biz.service.wservice;

import com.dhlk.airsensor.biz.model.bean.SensorInputBean;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorService {

    /**
     * 设定温度等参数的处理
     *
     * @param sensorInputBean
     * @return
     */
    public String sensorAirService(SensorInputBean sensorInputBean);

}
