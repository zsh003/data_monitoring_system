package cn.qqcn.service.impl;

import cn.qqcn.entity.Plc;
import cn.qqcn.mapper.PlcMapper;
import cn.qqcn.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class PlcServiceImpl implements PlcService {

    @Autowired
    private PlcMapper plcMapper;

    @Override
    public List<Plc> list(String plcName, String plcIp) {
        return plcMapper.getPlcList(plcName, plcIp);
    }

    @Override
    public Plc getPlcById(Integer id) {
        return plcMapper.getPlcById(id);
    }

    @Override
    public int addPlc(Plc plc) {
        return plcMapper.addPlc(plc);
    }

    @Override
    public int updatePlc(Plc plc) {
        return plcMapper.updatePlc(plc);
    }

    @Override
    public int deletePlc(Integer id) {
        return plcMapper.deletePlc(id);
    }

    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        // 这里实现图片上传逻辑，可以保存到本地或云存储
        // 返回图片访问URL
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // 实际开发中应该保存文件到指定目录或云存储
        // 这里只是示例，返回一个模拟URL
        return "/uploads/" + newFileName;
    }
}