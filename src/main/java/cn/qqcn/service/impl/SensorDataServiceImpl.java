package cn.qqcn.service.impl;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;
import cn.qqcn.mapper.SensorDataMapper;
import cn.qqcn.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataServiceImpl implements SensorDataService {
    @Autowired
    private SensorDataMapper sensorDataMapper;

    @Override
    public List<SensorData> list(Page page) {
        return sensorDataMapper.list(page);
    }

    @Override
    public Long countDataList() {
        return sensorDataMapper.countDataList();
    }

    @Override
    public List<SensorData> getSpeed(int plcid) {
        return sensorDataMapper.getSpeed(plcid);
    }

    @Override
    public List<SensorData> getTemperature(int plcid) {
        return sensorDataMapper.getTemperature(plcid);
    }

    @Override
    public List<SensorData> getLight(int plcid) {
        return sensorDataMapper.getLight(plcid);
    }

    @Override
    public List<SensorData> getAngle(int plcid) {
        return sensorDataMapper.getAngle(plcid);
    }
}
