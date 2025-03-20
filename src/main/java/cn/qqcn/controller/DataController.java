package cn.qqcn.controller;

import cn.qqcn.entity.*;
import cn.qqcn.service.MailService;
import cn.qqcn.common.vo.Result;
import cn.qqcn.service.DataService;
import cn.qqcn.entity.vo.DataQuery;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;
    @Autowired
    private MailService mailService;
    @RequestMapping("/image")
    public String image(){
        return "image";
    }

    @RequestMapping("/getspeed")
    @ResponseBody
    public List<speed> list1()
    {
        List<speed> list = dataService.findSpeed();
        return list;
    }

    @RequestMapping("/gettem")
    @ResponseBody
    public List<tem> list2()
    {
        List<tem> list = dataService.findTem();
        return list;
    }

    @RequestMapping("/getyingli")
    @ResponseBody
    public List<yingli> list3()
    {
        List<yingli> list = dataService.findYingli();
        return list;
    }

    @RequestMapping("/getdirx")
    @ResponseBody
    public List<dirx> list4()
    {
        List<dirx> list = dataService.findDir_x();
        return list;
    }

    @RequestMapping("/getdiry")
    @ResponseBody
    public List<diry> list5()
    {
        List<diry> list = dataService.findDir_y();
        return list;
    }

    @GetMapping("/getlist")
    @ResponseBody
    public Result<Object> getDataList(DataQuery param){
        List<data> list = dataService.getDataList(param);
        Long count = dataService.countDataList(param);
        return Result.success(list,count);
    }

    @GetMapping("/errorselect")
    @ResponseBody
    public Result<Object> errorselect(DataQuery param){
        List<data> list = dataService.errorselect(param);
        Long count = dataService.counterrorList(param);
        return Result.success(list,count);
    }

    @PostMapping("/alert")
    @ResponseBody
    public Result<Object> sendSimpleText(){
        String to="zhujiongos@126.com";
        String title="异常报警";
        String content="起重机数据出现异常，请及时查看处理。";
        Assertions.assertTrue(mailService.sendSimpleText(to,title,content));
        return Result.success();
    }
    @PostMapping("/alertfiles")
    @ResponseBody
    public Result<Object> sendWithWithEnclosure(){
        String to="zhujiongos@126.com";
        String title="异常报警";
        String content="起重机数据出现异常，请及时查看处理。";
        String[] filePaths=new String[]{
                "C:\\Users\\zhujiong\\Downloads\\table_1(4).csv"
        };
        Assertions.assertTrue(mailService.sendWithWithEnclosure(to,title,content,filePaths));
        return Result.success();
    }


}
