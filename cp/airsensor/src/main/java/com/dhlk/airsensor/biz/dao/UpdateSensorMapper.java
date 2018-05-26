package com.dhlk.airsensor.biz.dao;

import com.dhlk.airsensor.biz.model.SenInfo;
import com.dhlk.airsensor.biz.model.SensorInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UpdateSensorMapper {
    /**
     * 初始化数据插入
     *
     * @param list
     * @return
     */
    int insertSensorInfo(List<SensorInfo> list);

    /**
     * 初始化温度和标准温度、时间的设定
     *
     * @param list
     * @return
     */
    int insertSenInfo(List<SenInfo> list);

    /**
     * 更新绑定的地址数据
     *
     * @param sensorInfo
     */
    int updateSensorInfo(@Param("sensorInfo") SensorInfo sensorInfo);

}
