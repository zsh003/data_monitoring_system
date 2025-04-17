package cn.qqcn.service.impl;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.Plc;
import cn.qqcn.service.PlcService;
import cn.qqcn.mapper.PlcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 29808
* @description 针对表【plc】的数据库操作Service实现
* @createDate 2025-04-17 17:21:55
*/
@Service
public class PlcServiceImpl implements PlcService{

    @Autowired
    private PlcMapper plcMapper;
    @Override
    public List<Plc> list() {
        return plcMapper.list();
    }
}




