package com.dhlk.airsensor.biz.service.airDeal;

import com.dhlk.airsensor.biz.service.message.AirConSenor;
import com.dhlk.airsensor.biz.service.message.AirSend;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class AirDeal {
    private AirConSenor airConSenor;

    @Autowired
    public void setAirConSenor(AirSend airSend) {
        this.airConSenor = airSend;
    }

    /**
     * 测试
     *
     * @return
     */
    public String name() {
        Map message = airConSenor.getMessage(new ArrayList());
        return new Gson().toJson(message);
    }
}
