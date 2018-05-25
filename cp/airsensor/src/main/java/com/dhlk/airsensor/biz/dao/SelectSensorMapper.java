package com.dhlk.airsensor.biz.dao;

import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SelectSensorMapper {
    /**
     * 查询所有传感器的信息
     *
     * @return
     */
    List<SensorInfo> findAllSensorInfo();

    /**
     * 通过地址查询默认信息
     *
     * @param sensorAddr
     * @return
     */
    List<SenInfo> findSensorInfoByAddr(@Param("sensorAddr") String sensorAddr);

}
