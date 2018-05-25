package com.dhlk.airsensor.biz.service.airDeal.airControl;

import com.dhlk.airsensor.biz.dao.SelectSensorMapper;
import com.dhlk.airsensor.biz.model.HumidityTem;
import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.service.airDeal.AirDeal;
import com.dhlk.airsensor.biz.service.airDeal.SensorDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public abstract class AirControl {
    @Autowired
    SensorDeal sensorDeal;

    @Autowired
    AirDeal airDeal;

    @Autowired
    SelectSensorMapper sensorSelectMapper;
    //恒温设置
    static String tempAir = "";

    /**
     * 定时控制类
     */
    public int airControl() {
        List<HumidityTem> list = sensorDeal.sensorChangeTo(sensorSelectMapper.findAllSensorInfo());
        System.out.println(list.size() + "-----------");
        if (list == null) {
            return 1;
        }
        if (list.size() == 0) {
            return 1;
        }
        //空调温度
        String airTem = "";
        //空调发送命令码
        Map map = new HashMap<>();
        //冬季或者夏季
        int flag = 0;
        //标准比较温度
        String standTem = "";
        //设定的结果值
        int result = dealTem(list, airTem, tempAir, map);
        return result;
    }

    /**
     * @param list    传感器温度
     * @param airTem  空调温度
     * @param tempAir 设定的温度
     * @param map     发送命令
     * @return
     */
    protected abstract int dealTem(List<HumidityTem> list, String airTem, String tempAir, Map map);

    public void airConditionTemSet(List airCondition, List<HumidityTem> list) {
        for (int i = 1; i < list.size(); ++i) {
            List<SenInfo> sensorInfoByAddr = sensorSelectMapper.findSensorInfoByAddr(list.get(i).getAddr());
            //todo
          /*  List airAddr = new Gson().fromJson(sensorInfoByAddr.get(0).getAir_addr(), new TypeToken<List<String>>() {
            }.getType());*/
            //todo
            /*if (Integer.parseInt(airTem) > Integer.parseInt(tempAir)) {
                //发送空调命令  调整至25°

                //传感器温度>设定温度 temAir
                System.out.println("夏天设定成功" + i);

            }*/
            System.out.println(sensorInfoByAddr.size() + "-------------");
        }
        return;
    }
}
