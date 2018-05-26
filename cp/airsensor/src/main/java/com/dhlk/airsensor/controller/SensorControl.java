package com.dhlk.airsensor.controller;

import com.dhlk.airsensor.biz.model.bean.SensorInputBean;
import com.dhlk.airsensor.biz.service.airDeal.airControl.SummerControl;
import com.dhlk.airsensor.biz.service.wservice.SensorService;
import com.dhlk.airsensor.conf.sensor.SensorInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorControl {

    @Autowired
    private SensorInit sensorDeal;
    @Autowired
    SummerControl summerControl;

    @Autowired
    SensorService sensorService;

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

    /**
     * @param sensorAddr 01
     * @param tem        170280
     * @param date       20180910-20181090-20181003-20190203
     * @param airAddr    091-091-989-92198219-23
     * @return
     */
    @PostMapping(value = "/sensor")
    @ResponseBody
    public String sensorWithAir(@RequestParam("addr") String sensorAddr,
                                @RequestParam("tem") String tem,
                                @RequestParam("date") String date,
                                @RequestParam("air_addr") String airAddr) {
        System.out.println(sensorAddr + "--" + tem + "--" + date + "---" + airAddr + "--");
        sensorAddr = "01";
        tem = "123456";
        date = "20180910-20181090-20181003-20190203#-";
        airAddr = "002-090-230-909-998#-";
        SensorInputBean sensorInputBean = new SensorInputBean(sensorAddr, tem, date, airAddr);
        sensorService.sensorAirService(sensorInputBean);
        return "ok";

    }

}
