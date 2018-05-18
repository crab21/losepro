package com.dhlk.bas.controller;

import com.dhlk.bas.conf.ScheduleConfig;
import com.dhlk.bas.model.AirConditioner;
import com.dhlk.bas.util.AirConditionerUtil;
import com.dhlk.bas.util.FileUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by fnan on 2017-11-30.
 */
@RestController
@EnableScheduling
@RequestMapping(value = "/airconditioner")
public class AirConditionerController {

    /**
     * 判断是否时工作日
     *
     * @return
     */
    @GetMapping(value = "/workDay/{isWorkDay}")
    public int isWorkDay(@PathVariable int isWorkDay) {
        try {
            ScheduleConfig.workDay = isWorkDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isWorkDay;
    }

    @GetMapping(value = "/workDay")
    public int getWorkDay() {
        return ScheduleConfig.workDay;
    }

    /**
     * 空调控制总开关
     *
     * @return
     */
    @GetMapping(value = "/switchMode/{mode}")
    public int switchMode(@PathVariable int mode) {
        int result = 1;
        try {
            AirConditionerUtil.airconditionerSwitchMode = mode;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    @GetMapping(value = "/switchMode")
    public int getSwitchMode() {
        return AirConditionerUtil.airconditionerSwitchMode;
    }

    /**
     * 设定基准温度
     *
     * @return
     */
    @GetMapping(value = "/baseTemperature/{temperature}")
    public int baseTemperature(@PathVariable int temperature) {
        try {
            ScheduleConfig.baseTemperature = temperature;
            ScheduleConfig.schedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temperature;
    }

    @GetMapping(value = "/baseTemperature")
    public int getBaseTemperature() {
        return ScheduleConfig.baseTemperature;
    }

    /**
     * 设定基准温度
     *
     * @return
     */
    @GetMapping(value = "/hourlyTemperature")
    public Map<Integer, Integer> hourlyTemperature() {
        return ScheduleConfig.hourlyTemperatureMap;
    }

    /**
     * 获取空调状态
     *
     * @return
     */
    @GetMapping(value = "/state")
    public Map<String, Object> state() {

        Map<String, Object> map = new HashMap<>();

        try {
            Map<String, Object> airConditionerMap = FileUtil.airConditionerMap;
            Iterator<String> iterator = airConditionerMap.keySet().iterator();
            while (iterator.hasNext()) {

                String next = iterator.next();

                AirConditioner airConditioner = (AirConditioner) airConditionerMap.get(next);
//                System.out.println(airConditioner);
                int state = airConditioner.getState();
                long time = airConditioner.getTime();

                if (new Date().getTime() - time > 5 * 60 * 1000) {
                    state = 2;
                }

                airConditioner.setState(state);
                map.put(next, airConditioner);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 获取单个，多个空调状态
     * 中间使用断线间隔：814-815
     *
     * @return
     */
    @GetMapping(value = "/state/{shortaddr}")
    public Map<String, Object> oneOrMoreState(@PathVariable String shortaddr) {

        Map<String, Object> map = new HashMap<>();

        try {
            Map<String, Object> airConditionerMap = FileUtil.airConditionerMap;
            Iterator<String> iterator = airConditionerMap.keySet().iterator();
            while (iterator.hasNext()) {

                String next = iterator.next();
                AirConditioner airConditioner = (AirConditioner) airConditionerMap.get(next);

                String addr = String.valueOf(airConditioner.getAddr());
                if (!shortaddr.contains(addr)) {
                    continue;
                }
                int state = airConditioner.getState();
                long time = airConditioner.getTime();

                if (new Date().getTime() - time > 5 * 60 * 1000) {
                    state = 2;
                }

                airConditioner.setState(state);
                map.put(next, airConditioner);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 空调设置接口
     *
     * @param shortaddrs
     * @param airConditioner
     * @return
     */
    @PostMapping("/set")
    public int airConditionerSet(@RequestParam String shortaddrs, AirConditioner airConditioner) {

        int flag = 0;

        try {

            AirConditionerUtil.airConditionerSet(shortaddrs, airConditioner);

            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 开关空调
     * 1：开灯；0：关灯
     *
     * @param shortaddrs
     * @param switch0
     * @return
     */
    @GetMapping(value = "/switch0/{shortaddrs}/{switch0}")
    public int switch0(@PathVariable String shortaddrs, @PathVariable int switch0) {

        int flag = 0;

        try {

            AirConditionerUtil.switch0(shortaddrs, switch0);
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 空调模式
     * 1：制热；0：制冷
     *
     * @param shortaddrs
     * @param mode
     * @return
     */
    @GetMapping(value = "/mode/{shortaddrs}/{mode}")
    public int mode(@PathVariable String shortaddrs, @PathVariable int mode) {

        int flag = 0;

        try {

            AirConditionerUtil.mode(shortaddrs, mode);
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 设置空调风速
     * 0：自动；1：高速；2：中速；3：低速
     *
     * @param windSpeed
     * @return
     */
    @GetMapping(value = "/wind/{shortaddrs}/{windSpeed}")
    public int wind(@PathVariable String shortaddrs, @PathVariable int windSpeed) {

        int flag = 0;

        try {

            AirConditionerUtil.wind(shortaddrs, windSpeed);
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 设定空调温度
     * 设定范围：10~30（制冷和制热调节范围相同）
     *
     * @param shortaddrs
     * @param temperature
     * @return
     */
    @GetMapping(value = "/temperature/{shortaddrs}/{temperature}")
    public int temperature(@PathVariable String shortaddrs, @PathVariable int temperature) {

        int flag = 0;

        try {

            AirConditionerUtil.temperature(shortaddrs, temperature);
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 设定空调补偿温度
     * 设定范围：-9*C~9*C，默认（-2*C）
     *
     * @param shortaddrs
     * @param compensateTemperature
     * @return
     */
    @GetMapping(value = "/compensateTemperature/{shortaddrs}/{compensateTemperature}")
    public int compensateTemperature(@PathVariable String shortaddrs, @PathVariable int compensateTemperature) {

        int flag = 0;

        try {

            AirConditionerUtil.compensateTemperature(shortaddrs, compensateTemperature);
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 设置开关定时
     *
     * @param shortaddrs
     * @param scheduleOn
     * @param scheduleHour
     * @return
     */
    @PostMapping(value = "/schedule")
    public int schedule(@RequestParam String shortaddrs, @RequestParam boolean scheduleOn, @RequestParam float scheduleHour) {

        int flag = 0;

        try {

//            AirConditionerUtil.schedule(shortaddrs, scheduleOn, scheduleHour);

            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 定时开关空调
     *
     * @throws UnsupportedEncodingException
     * @throws InterruptedException
     */
    @Scheduled(cron = "0,5 40 8 ? * *")
    @Scheduled(cron = "0,5 30 18 ? * *")
    public void airConditionerOpenSchedule() throws UnsupportedEncodingException, InterruptedException {

        System.out.println("-------------------------Schedule-------------------------");
        // 801-802-803-804-805-806-807-808-809-810-811-812-813-814-815-816-817-818-819-820
        String shotaddrs = "801-802-803-804-806-807-808-810-811-812-814-815-816-817-818-819-820";

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        // 非工作天，返回
        if (ScheduleConfig.workDay == 0) {
            return;
        }

        if (hour == 8) { // 定时开
            switch0(shotaddrs, 1);
        } else if (hour == 18) { // 定时关
            switch0(shotaddrs, 0);
        }


    }

}
