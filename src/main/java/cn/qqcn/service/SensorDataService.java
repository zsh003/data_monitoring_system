package cn.qqcn.service;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SensorDataService {
    List<SensorData> list(Page page1);

    Long countDataList();

    List<SensorData> getSpeed(int plcid);

    List<SensorData> getTemperature(int plcid);

    List<SensorData> getLight(int plcid);

    List<SensorData> getAngle(int plcid);

    SensorData getDataByIdAndTime(int plcid, Date date);

    List<SensorData> list(Page page1, int id);

    Long countDataList(int id);

    List<SensorData> listerror(Page page1, Map<String, Object> params);

    Long countErrorDataList(Map<String, Object> params);

    void deleteByTime(int plcid, Date date);
}
