package com.dhlk.airsensor.biz.service.airDeal;

import com.dhlk.airsensor.biz.model.HumidityTem;
import com.dhlk.airsensor.biz.service.message.AirConSenor;
import com.dhlk.airsensor.biz.service.message.SensorSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SensorDeal {
    private AirConSenor airConSenor;

    @Autowired
    public void setAirConSenor(SensorSend sensorSend) {
        this.airConSenor = sensorSend;
    }


    /**
     * 拿取数据转为list
     *
     * @param list
     * @return
     */
    public List<HumidityTem> sensorChangeTo(List list) {

        Map message = airConSenor.getMessage(list);
        if (message.size() == 0) {
            return null;
        }
        List<HumidityTem> humidityTems = SensorDealAssist.splitThum(message);
        System.out.println("sensorChangeto" + humidityTems.size());
        if (humidityTems.size() == 0) {
            return null;
        }
        for (HumidityTem hu :
                humidityTems) {
            System.out.println(hu.getAddr() + "--" + hu.getHumidity() + "--" + hu.getTem() + "--" + hu.getDate());

        }
        return humidityTems;
    }

}
