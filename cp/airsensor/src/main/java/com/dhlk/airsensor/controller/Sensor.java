package com.dhlk.airsensor.controller;

import com.dhlk.airsensor.biz.service.airDeal.airControl.AirControl;
import com.dhlk.airsensor.biz.service.airDeal.airControl.SummerControl;
import com.dhlk.airsensor.conf.sensor.SensorInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sensor {

    @Autowired
    private SensorInit sensorDeal;
    @Autowired
    SummerControl summerControl;

    @RequestMapping("/getsensor")
    public String getSensor() {
        /*System.out.println(message.size());
        for (String map :
                message.keySet()) {
            System.out.println(map + "---------" + message.get(map).size());
            List<String> list = (List) message.get(map);
            for (String ll :
                    list) {
                System.out.println(ll + "---");
            }
        }*/
        sensorDeal.init();
        return "ok";
    }

    @RequestMapping("/lop")
    public String lp() {
        summerControl.airControl();
        return "ok";
    }
}
