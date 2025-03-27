package cn.qqcn.mapper;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SensorDataMapper {
    List<SensorData> list(Page page);

    Long countDataList();

    List<SensorData> getSpeed(int plcid);

    List<SensorData> getTemperature(int plcid);

    List<SensorData> getLight(int plcid);

    List<SensorData> getAngle(int plcid);

    SensorData getDataByIdAndTime(int plcid, Date date);

    List<SensorData> listById(@Param("page")Page page, int id);

    Long countDataListById(int id);

}
