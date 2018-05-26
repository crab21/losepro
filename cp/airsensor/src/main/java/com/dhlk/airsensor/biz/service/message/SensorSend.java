package com.dhlk.airsensor.biz.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SensorSend implements AirConSenor {
    @Autowired
    private SensorMessage sensorMessage;

    @Override
    public Map getMessage(List list) {
        Map message = sensorMessage.getMessage(list);
        return message;
    }
}

