package cn.qqcn.service;

import cn.qqcn.entity.Plc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlcService {
    List<Plc> list(String plcName, String plcIp);
    Plc getPlcById(Integer id);
    int addPlc(Plc plc);
    int updatePlc(Plc plc);
    int deletePlc(Integer id);
    String uploadImage(MultipartFile file) throws Exception;
}