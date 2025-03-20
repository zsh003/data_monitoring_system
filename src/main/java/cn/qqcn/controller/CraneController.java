package cn.qqcn.controller;

import cn.qqcn.entity.count;
import cn.qqcn.entity.crane;
import cn.qqcn.service.CraneService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/crane")
public class CraneController {
    @Autowired
    private CraneService craneService;
    @GetMapping("")
    public String zhuangtai(Model model){
        crane crane=craneService.CraneSit();
        Long CountOn=craneService.CountOn();
        Long CountOff=craneService.CountOff();
        System.out.print(crane+"\n");
        model.addAttribute("crane",crane);
        count count = new count();
        count.setCounton(CountOn);
        count.setCountoff(CountOff);
        System.out.print(count+"\n");
        model.addAttribute("count",count);
        return "cranepages";
    }


}
