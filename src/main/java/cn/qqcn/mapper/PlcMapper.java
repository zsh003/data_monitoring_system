package cn.qqcn.mapper;

import cn.qqcn.entity.Plc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlcMapper {
    List<Plc> getPlcList(@Param("plcName") String plcName, @Param("plcIp") String plcIp);
    Plc getPlcById(@Param("id") Integer id);
    int addPlc(Plc plc);
    int updatePlc(Plc plc);
    int deletePlc(@Param("id") Integer id);
}