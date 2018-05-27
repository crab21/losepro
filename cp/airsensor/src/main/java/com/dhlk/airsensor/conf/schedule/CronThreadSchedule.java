package com.dhlk.airsensor.conf.schedule;

import com.dhlk.airsensor.biz.service.airDeal.airControl.SummerControl;
import com.dhlk.airsensor.biz.service.airDeal.airControl.WinterControl;
import com.dhlk.airsensor.conf.sensor.SensorInit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CronThreadSchedule extends Thread {
    @Autowired
    WinterControl winterControl;

    @Autowired
    SummerControl summerControl;
    private static CronThreadSchedule cronThreadSchedule;

    @PostConstruct
    public void init() {
        cronThreadSchedule = this;
    }

    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String date = simpleDateFormat.format(new Date());

        String winterDate = SensorInit.winterDate;
        boolean result = compDate(winterDate, date);
        if (result) {
            cronThreadSchedule.winterControl.airControl();
        }

        String summerDate = SensorInit.summerDate;
        result = compDate(summerDate, date);
        if (result) {
            try {
                if (cronThreadSchedule.summerControl == null) {
                    System.out.println("fuck");
                } else {

                    cronThreadSchedule.summerControl.airControl();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 判断冬夏
     *
     * @param makeDate
     * @param date
     * @return
     */
    private static boolean compDate(String makeDate, String date) {

        if (StringUtils.isEmpty(date))
            return false;

        String startDate = makeDate.substring(0, 12);
        String endDate = makeDate.substring(12, makeDate.length());
        if (date.compareTo(startDate) > 0 && endDate.compareTo(date) > 0)
            return true;
        else
            return false;
    }

}
