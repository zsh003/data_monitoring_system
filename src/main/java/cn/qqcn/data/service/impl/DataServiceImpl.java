package cn.qqcn.data.service.impl;
import cn.qqcn.data.entity.*;
import cn.qqcn.data.service.DataService;
import cn.qqcn.data.vo.DataQuery;
import cn.qqcn.data.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired(required = false)
    private DataMapper dataMapper;
    @Override
    public List<speed> findSpeed() {
        return this.dataMapper.findSpeed();
    }

    @Override
    public List<tem> findTem() {
        return this.dataMapper.findTem();
    }

    @Override
    public List<yingli> findYingli() {
        return this.dataMapper.findYingli();
    }

    @Override
    public List<dirx> findDir_x() {
        return this.dataMapper.findDir_x();
    }

    @Override
    public List<diry> findDir_y() {
        return this.dataMapper.findDir_y();
    }

    @Override
    public List<data> getDataList(DataQuery param) {
        return dataMapper.getDataList(param);
    }

    @Override
    public Long countDataList(DataQuery param) {
        return dataMapper.countDataList(param);
    }

    @Override
    public List<data> errorselect(DataQuery param) {
        return dataMapper.errorselect(param);
    }

    @Override
    public Long counterrorList(DataQuery param) {
        return dataMapper.counterrorList(param);
    }

    @Override
    public List<data> findDataByTime(String start, String end) {
        return dataMapper.byTime(start, end);
    }


}

