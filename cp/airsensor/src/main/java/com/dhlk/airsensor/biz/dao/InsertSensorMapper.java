package com.dhlk.airsensor.biz.dao;

import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InsertSensorMapper {
    /**
     * 初始化数据插入
     *
     * @param list
     * @return
     */
    void initSensorInfo(List<SensorInfo> list);

    /**
     * 初始化温度和标准温度、时间的设定
     *
     * @param list
     * @return
     */
    void initSenInfo(List<SenInfo> list);
}
