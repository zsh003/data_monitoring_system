package cn.qqcn.mapper;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SensorDataMapper {
    List<SensorData> list(Page page);

    Long countDataList();

    List<SensorData> getSpeed(int plcid);

    List<SensorData> getTemperature(int plcid);

    List<SensorData> getLight(int plcid);

    List<SensorData> getAngle(int plcid);

    SensorData getDataByIdAndTime(@Param("plcid") int plcid, @Param("date") Date date);

    List<SensorData> listById(@Param("page") Page page,@Param("id") int id);

    Long countDataListById(int id);

    List<SensorData> listerror(@Param("page") Page page, @Param("params") Map<String, Object> params);

    Long countErrorDataList(@Param("params") Map<String, Object> params);

    void deleteByTime(int plcid, Date date);
}
