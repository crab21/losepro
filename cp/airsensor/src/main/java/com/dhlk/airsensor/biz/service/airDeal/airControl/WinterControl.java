package com.dhlk.airsensor.biz.service.airDeal.airControl;

import com.dhlk.airsensor.biz.model.HumidityTem;
import com.dhlk.airsensor.biz.model.SenInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WinterControl extends AirControl {


    /**
     * @param list   传感器温度
     * @param airTem 空调温度
     * @param map    发送命令
     * @return
     */
    @Override
    protected int dealTem(List<HumidityTem> list, String airTem, Map map) {
        //外界温度低于17°
        List<SenInfo> sensorInfoByAddr = sensorSelectMapper.findSensorInfoByAddr(list.get(0).getAddr());
        SenInfo senInfo = sensorInfoByAddr.get(0);
        String standTem = senInfo.getSensor_tem();
        //外界温度低于17°
        if (Float.parseFloat(list.get(0).getTem()) < Float.parseFloat(standTem)) {
            //todo
            List lists = new ArrayList();
            airConditionTemSet(lists, list, senInfo.getAir_tem());
        }
        return 1;
    }
}
