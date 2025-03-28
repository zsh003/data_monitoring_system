package cn.qqcn.controller;

import cn.qqcn.common.vo.Page;
import cn.qqcn.entity.SensorData;
import cn.qqcn.entity.vo.ResultVO;
import cn.qqcn.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

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

    @PostMapping("/list/error")
    public ResultVO<Object> listerror(@RequestParam int page, @RequestParam int limit,
                                      @RequestParam(required = false) Integer plcId,
                                      @RequestParam(required = false) Double angleMax,
                                      @RequestParam(required = false) Double angleMin,
                                      @RequestParam(required = false) Double lightMax,
                                      @RequestParam(required = false) Double lightMin,
                                      @RequestParam(required = false) Double speedMax,
                                      @RequestParam(required = false) Double speedMin,
                                      @RequestParam(required = false) Double tempMax,
                                      @RequestParam(required = false) Double tempMin
                                      ){
        Page page1 = new Page(page, limit);
        Map<String, Object> params = new HashMap<>();
        params.put("plcId", plcId);
        params.put("angleMax", angleMax);
        params.put("angleMin", angleMin);
        params.put("lightMax", lightMax);
        params.put("lightMin", lightMin);
        params.put("speedMax", speedMax);
        params.put("speedMin", speedMin);
        params.put("tempMax", tempMax);
        params.put("tempMin", tempMin);
        System.out.println("page" + page1);
        System.out.println("params" + params);
        List<SensorData> list = sensorDataService.listerror(page1, params);
        System.out.println("list:"+list);
        Long count = sensorDataService.countErrorDataList(params);
        return ResultVO.success(list, count);
    }

    @GetMapping("/list/{id}")
    public ResultVO<Object> list(@RequestParam int page, @RequestParam int limit, @PathVariable int id){
        Page page1 = new Page(page, limit);
        List<SensorData> list = sensorDataService.list(page1, id);
        Long count = sensorDataService.countDataList(id);
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

    @PostMapping("/getdatabytime/{plcid}")
    public ResultVO<Object> handleRequest(@PathVariable int plcid, @RequestBody Map<String, String> payload) {
        String dateTimeStr = payload.get("dateTime");

        // 解析ISO 8601格式的日期时间字符串
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

        System.out.println("Received date-time: " + dateTime);
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        SensorData sensorData =  sensorDataService.getDataByIdAndTime(plcid, date);
        return sensorData != null ? ResultVO.success(sensorData, 1L) : ResultVO.fail("No data found for the given date-time.");
    }

    @PostMapping("/deletebytime/{plcid}")
    public ResultVO<Object> deleteByTime(@PathVariable int plcid, @RequestBody Map<String, String> payload) throws ParseException {
        String dateTimeStr = payload.get("dateTime");
        System.out.println("plcid" + plcid);
        System.out.println("Received date-time: " + dateTimeStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = formatter.parse(dateTimeStr);
        sensorDataService.deleteByTime(plcid, date2);
        return ResultVO.success();
    }
}
