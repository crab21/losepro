package com.dhlk.bas.conf;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.bas.model.AirConditioner;
import com.dhlk.bas.util.AirConditionerUtil;
import com.dhlk.bas.util.DateUtil;
import com.dhlk.bas.util.FileUtil;
import com.dhlk.bas.util.HttpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by fnan on 2017-12-11.
 * 定时器配置
 * http://tj.nineton.cn/Heart/index/future24h/?city=CHSN000000
 */

@Configuration
@EnableScheduling
public class ScheduleConfig {

    public static Map<Integer, Integer> hourlyTemperatureMap = new HashMap();
    public static int baseTemperature = 26; // 基准温度
    public static int componentTemperature = 2; // 补偿温度
    public static int mode = 1; // 制热
    public static int workDay = 1; // 是否是工作日

    // 设置温度基准
    @Scheduled(cron = "20 0 0 * * ?")
    @PostConstruct
    public void set01() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if (month > 10 || month <= 4) {
            baseTemperature = 26; // 基准温度
            componentTemperature = 2; // 补偿温度
        } else {
            baseTemperature = 18; // 基准温度
            componentTemperature = -2; // 补偿温度
        }
    }

    // 更新每小时的baseTemperature
    @Scheduled(cron = "10 0 * * * ?")
    public void set02() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Integer outdoorTemperature = hourlyTemperatureMap.get(hour);
        if (month > 10 || month <= 4) {
            baseTemperature = 26 - Math.round((outdoorTemperature - 3) / 10.0f); // 基准温度
        } else {
            baseTemperature = 18 - Math.round((outdoorTemperature - 20) / 10.0f); // 基准温度
        }
    }

    // 设置是否是工作日
    @PostConstruct
    @Scheduled(cron = "30 0 0 ? * SAT,SUN")
    public void set04() {
        workDay = 0;
    }

    // 设置是否是工作日
    @PostConstruct
    @Scheduled(cron = "30 0 0 ? * MON-FRI")
    public void set03() {
        workDay = 1;
    }

    // 获取一天内每小时的温度信息
    @PostConstruct
    @Scheduled(cron = "0,5 0 * ? * *")
    public void schedule2() {

        try {
            String hourlyTemperature = HttpUtil.doGet("http://tj.nineton.cn/Heart/index/future24h/?city=CHSN000000");
            Map<String, Object> hourlyTemperatureObject = (Map<String, Object>) JSONObject.parse(hourlyTemperature);
            List<Map<String, Object>> hourlyTemperatureList = (List<Map<String, Object>>) hourlyTemperatureObject.get("hourly");

            for (int i = 0; i < hourlyTemperatureList.size(); i++) {
                Map<String, Object> hourTemperatureMap = hourlyTemperatureList.get(i);
                Integer hour = Integer.valueOf(hourTemperatureMap.get("time").toString().substring(11, 13));
                Integer temperature = Integer.valueOf(hourTemperatureMap.get("temperature").toString());
                hourlyTemperatureMap.put(hour, temperature);
//                System.out.println(hour + ":" + temperature);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    /**
     * 每5分钟执行以下定时任务
     * 当到达指定温度后，停机，当温度低于基准温度2度时，再开启
     * 添加国家法定节假日过滤...
     */
    @Scheduled(cron = "0,5 0/5 * ? * *")
    public static void schedule() {

        // 如果非工作日
        if (workDay == 0) {
            return;
        }

        // 如果是节假日，退出
        boolean holiday = DateUtil.isHoliday();
        if (holiday) {
            return;
        }

        // 获取小时段
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // 24小时制
        int minute = Calendar.getInstance().get(Calendar.MINUTE);

        // 获取室外温度
        int hourTemperature = 0;

        try {
            hourTemperature = hourlyTemperatureMap.get(hour);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 正常上班时间开启自动模式
        if (hour >= 9 && hour <= 18) {

            // 如果18:30后退出
            if (hour == 18 && minute >= 20) {
                return;
            }

            try {
                Map<String, Object> airConditionerMap = FileUtil.airConditionerMap;
                Iterator<String> iterator = airConditionerMap.keySet().iterator();
                while (iterator.hasNext()) {

                    String next = iterator.next();
                    AirConditioner airConditioner = (AirConditioner) airConditionerMap.get(next);

                    int switch0 = airConditioner.getSwitch0();
                    int mode = airConditioner.getMode();
                    int windSpeed = airConditioner.getWindSpeed();
                    int indoorTemperature = airConditioner.getIndoorTemperature(); // 室内温度
                    int settingTemperature = airConditioner.getSettingTemperature(); // 获取设定温度
                    int compensateTemperature = airConditioner.getCompensateTemperature(); // 补偿温度，使用软件进行补偿量控制。
                    int mBaseTemperature = baseTemperature; // 获取当前基准温度

                    // 根据不同季节设定不同温度标准
                    if (hourTemperature < 24) { // 冬天自然界温度

                        // 特殊情况
                        if (airConditioner.getAddr() == 804 || airConditioner.getAddr() == 801 || airConditioner.getAddr() == 817) {
                            mBaseTemperature += 1;
                        }

                        mode = 1; // 制热
                        compensateTemperature = 0; // 补偿温度

                        // 温度控制，如果室内温度接近设定温度
                        if (indoorTemperature + 1 >= mBaseTemperature) { // 26
                            switch0 = 0; // <=0 关
                        } else if (indoorTemperature + 3 <= mBaseTemperature) { // 24
                            switch0 = 1; // <=2 开
                        }

                        if (indoorTemperature < mBaseTemperature - 3) {
                            windSpeed = 1; // <=3 高速
                        } else if (indoorTemperature >= mBaseTemperature - 3 && indoorTemperature < mBaseTemperature - 2) {
                            windSpeed = 2; // 2-1 中速
                        } else if (indoorTemperature >= mBaseTemperature - 2 && indoorTemperature < mBaseTemperature - 1) { // 接近室温
                            windSpeed = 3; // 1-0 低速
                        } else {
                            windSpeed = 3; // 0
                        }

                    } else if (hourTemperature > 30) { // 夏天自然界温度

                        // 特殊情况
                        if (airConditioner.getAddr() == 804 || airConditioner.getAddr() == 801 || airConditioner.getAddr() == 817) {
                            mBaseTemperature -= 1;
                        }

                        mode = 0; // 制冷
                        compensateTemperature = 0; // 补偿温度

                        // 温度控制，如果室内温度接近设定温度
                        if (indoorTemperature - 3 >= mBaseTemperature) {
                            switch0 = 1; // >=2 开
                        } else if (indoorTemperature - 1 <= mBaseTemperature) {
                            switch0 = 0; // >=0 关
                        }

                        if (indoorTemperature - 3 > mBaseTemperature) {
                            windSpeed = 1; // >=3 高速
                        } else if (indoorTemperature - 3 <= mBaseTemperature && indoorTemperature - 2 > mBaseTemperature) {
                            windSpeed = 2; // 2-1 中速
                        } else if (indoorTemperature - 2 <= mBaseTemperature && indoorTemperature - 1 > mBaseTemperature) {
                            windSpeed = 3; // 1-0 低速
                        } else {
                            windSpeed = 3; // 0 自动
                        }

                    }

                    // 特殊情况
                    if (airConditioner.getAddr() == 803) {
                        windSpeed = 1; // 803 线接反
                    }

                    airConditioner.setSettingTemperature(mBaseTemperature);
                    airConditioner.setMode(mode);
                    airConditioner.setSwitch0(switch0);
                    airConditioner.setWindSpeed(windSpeed);
                    airConditioner.setCompensateTemperature(compensateTemperature); // 自动调节时，设置补偿温度为0

                    // 重新设置空调参数
                    // 以后自动管理，加入的配置
                    // 801-802-803-804-805-806-807-808-809-810-811-812-813-814-815-816-817-818-819-820
                    String shortaddrs = "801-802-803-804-806-807-808-810-811-812-814-815-816-817";
                    int shortaddr = airConditioner.getAddr();
                    if (shortaddrs.contains(String.valueOf(shortaddr))) { // 如果包含
                        AirConditionerUtil.airConditionerSet(String.valueOf(shortaddr), airConditioner);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

//public class ScheduleConfig implements SchedulingConfigurer {
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(
//                () -> System.out.println("ScheduleTask:" + LocalDateTime.now().toLocalTime()),
//                triggerContext -> {
//
//                    String cron = ScheduleUtil.cron01;
//                    if (StringUtils.isNullOrEmpty(cron)) {
//
//                    }
//
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
//    }
//
//}
