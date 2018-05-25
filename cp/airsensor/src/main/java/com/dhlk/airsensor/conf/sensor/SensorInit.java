package com.dhlk.airsensor.conf.sensor;

import com.dhlk.airsensor.biz.dao.InsertSensorMapper;
import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SensorInit {
    @Autowired
    InsertSensorMapper insertSensorMapper;

    public void init() {
        List list = initSensor(null);
        insertSensorMapper.initSensorInfo((List<SensorInfo>) list.get(0));
        insertSensorMapper.initSenInfo((List<SenInfo>) list.get(1));
    }

    private List initSensor(List list) {
        final String addr1 = "01";
        final String addr2 = "02";
        final String addr3 = "03";
        final String addr4 = "04";
        List<SensorInfo> sensorInfo = new ArrayList();
        sensorInfo.add(new SensorInfo(addr1, ""));
        sensorInfo.add(new SensorInfo(addr2, ""));
        sensorInfo.add(new SensorInfo(addr3, ""));
        sensorInfo.add(new SensorInfo(addr4, ""));

        List<SenInfo> seninfo = new ArrayList<>();
        seninfo.add(new SenInfo(addr1, "17", "25", "", ""));
        seninfo.add(new SenInfo(addr2, "17", "25", "", ""));
        seninfo.add(new SenInfo(addr3, "17", "25", "", ""));
        seninfo.add(new SenInfo(addr4, "17", "25", "", ""));

        List listAll = new ArrayList();
        listAll.add(sensorInfo);
        listAll.add(seninfo);
        return listAll;
    }
}
