package cn.qqcn.controller;

import cn.qqcn.entity.Plc;
import cn.qqcn.entity.vo.ResultVO;
import cn.qqcn.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/plc")
@RestController
public class PLCController {
    @Autowired
    private PlcService plcService;

    @GetMapping("/list")
    public ResultVO<Object> list(@RequestParam(required = false) String plcName,
                                 @RequestParam(required = false) String plcIp) {
        System.out.println(plcName + plcIp);
        List<Plc> list = plcService.list(plcName, plcIp);
        return ResultVO.success(list, (long) list.size());
    }

    @GetMapping("/detail/{id}")
    public ResultVO<Object> getPlcById(@PathVariable Integer id) {
        Plc plc = plcService.getPlcById(id);
        return ResultVO.success(plc, 1L);
    }

    @PostMapping("/add")
    public ResultVO<Object> addPlc(@RequestBody Plc plc) {
        //设置默认图片
        plc.setPlc_image("/images/plc/1.png");
        int result = plcService.addPlc(plc);
        if (result > 0) {
            return ResultVO.success("添加PLC成功");
        }
        return ResultVO.fail("添加PLC失败");
    }

    @PostMapping("/update")
    public ResultVO<Object> updatePlc(@RequestBody Plc plc) {
        int result = plcService.updatePlc(plc);
        if (result > 0) {
            return ResultVO.success("更新PLC成功");
        }
        return ResultVO.fail("更新PLC失败");
    }

    @PostMapping("/delete/{id}")
    public ResultVO<Object> deletePlc(@PathVariable Integer id) {
        int result = plcService.deletePlc(id);
        if (result > 0) {
            return ResultVO.success("删除PLC成功");
        }
        return ResultVO.fail("删除PLC失败");
    }

    @PostMapping("/upload")
    public ResultVO<Object> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = plcService.uploadImage(file);
            return ResultVO.success(imageUrl);
        } catch (Exception e) {
            return ResultVO.fail("图片上传失败: " + e.getMessage());
        }
    }
}