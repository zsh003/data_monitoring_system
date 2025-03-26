package cn.qqcn.service;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;

import java.util.List;

public interface SensorDataService {
    List<SensorData> list(Page page1);

    Long countDataList();

    List<SensorData> getSpeed(int plcid);

    List<SensorData> getTemperature(int plcid);

    List<SensorData> getLight(int plcid);

    List<SensorData> getAngle(int plcid);
}
