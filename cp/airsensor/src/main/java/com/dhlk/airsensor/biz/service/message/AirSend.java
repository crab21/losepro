package com.dhlk.airsensor.biz.service.message;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AirSend implements AirConSenor {
    private AirMessage airMessage;

    @Override
    public Map getMessage(List list) {
//        airMessage.getMessage(list);
        Map na = new HashMap();
        na.put("wang", "wang");
        return na;
    }
}
