package cn.qqcn.controller;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;
import cn.qqcn.entity.vo.ResultVO;
import cn.qqcn.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sensordata")
@RestController
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping("/list")
    public ResultVO<Object> list(@RequestParam int page, @RequestParam int limit){
        Page page1 = new Page(page, limit);
        List<SensorData> list = sensorDataService.list(page1);
        Long count = sensorDataService.countDataList();
        return ResultVO.success(list, count);
    }

    @GetMapping("/getspeed/{plcid}")
    public List<SensorData> getSpeed(@PathVariable int plcid){
        return sensorDataService.getSpeed(plcid);
    }

    @GetMapping("/gettemp/{plcid}")
    public List<SensorData> getTemperature(@PathVariable int plcid){
        return sensorDataService.getTemperature(plcid);
    }

    @GetMapping("/getlight/{plcid}")
    public List<SensorData> getLight(@PathVariable int plcid){
        System.out.println("plcid:"+plcid);
        return sensorDataService.getLight(plcid);
    }

    @GetMapping("/getangle/{plcid}")
    public List<SensorData> getAngle(@PathVariable int plcid){
        return sensorDataService.getAngle(plcid);
    }
}
