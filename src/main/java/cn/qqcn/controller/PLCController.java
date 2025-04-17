package cn.qqcn.controller;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.Plc;
import cn.qqcn.entity.SensorData;
import cn.qqcn.entity.vo.ResultVO;
import cn.qqcn.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/plc")
@RestController
public class PLCController {
    @Autowired
    private PlcService plcService;

    @GetMapping("/list")
    public ResultVO<Object> list(){
        List<Plc> list = plcService.list();
        return ResultVO.success(list, 100L);
    }
}
