package cn.qqcn.service.impl;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;
import cn.qqcn.mapper.SensorDataMapper;
import cn.qqcn.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public SensorData getDataByIdAndTime(int plcid, Date date) {

        return sensorDataMapper.getDataByIdAndTime(plcid, date);
    }

    @Override
    public List<SensorData> list(Page page, int id) {
        return sensorDataMapper.listById(page, id);
    }

    @Override
    public Long countDataList(int id) {
        return sensorDataMapper.countDataListById(id);
    }

    @Override
    public List<SensorData> listerror(Page page, Map<String, Object> params) {
        return sensorDataMapper.listerror(page, params);
    }

    @Override
    public Long countErrorDataList(Map<String, Object> params) {
        return sensorDataMapper.countErrorDataList(params);

    }

    @Override
    public void deleteByTime(int plcid, Date date) {
        sensorDataMapper.deleteByTime(plcid, date);
    }

}
