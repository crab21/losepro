package com.dhlk.airsensor.controller;

import com.dhlk.airsensor.biz.model.bean.SensorInputBean;
import com.dhlk.airsensor.biz.service.airDeal.airControl.SummerControl;
import com.dhlk.airsensor.biz.service.wservice.SensorService;
import com.dhlk.airsensor.conf.schedule.ScheduleAirSensor;
import com.dhlk.airsensor.conf.sensor.SensorInit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorControl {

    @Autowired
    SensorInit sensorDeal;

    @Autowired
    SummerControl summerControl;

    @Autowired
    SensorService sensorService;

    @Autowired
    ScheduleAirSensor scheduleAirSensor;

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

    @GetMapping(value = "/sensor")
    @ResponseBody
    public int sensorWithAir() {
    /*
    @RequestParam("addr") String sensorAddr,
                                @RequestParam("tem") String tem,
                                @RequestParam("date") String date,
                                @RequestParam("air_addr") String airAddr) {
        System.out.println(sensorAddr + "--" + tem + "--" + date + "---" + airAddr + "--"
     */

        String sensorAddr = "01";
        String tem = "123-456#-";
        String date = "201809101928201810011928-201810031928201902031928#-";
        String airAddr = "002-090-230-909-998";
        SensorInputBean sensorInputBean = new SensorInputBean(sensorAddr, tem, date, airAddr);
        String s = sensorService.sensorAirService(sensorInputBean);
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        return 1;

    }

    /**
     * @param control 1设定成功  0设定失败
     * @return
     */
    @RequestMapping(value = "/senCrtl/{control}", method = {RequestMethod.GET, RequestMethod.POST})
    public int scheControl(@PathVariable(value = "control") String control) {
        try {
            if ("start".equals(control)) {
                scheduleAirSensor.startCron();
            }
            if ("end".equals(control)) {
                scheduleAirSensor.stopCron();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
