package cn.qqcn.service;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.Plc;
import cn.qqcn.entity.SensorData;

import java.util.List;

/**
* @author 29808
* @description 针对表【plc】的数据库操作Service
* @createDate 2025-04-17 17:21:55
*/
public interface PlcService{

    List<Plc> list();
}
