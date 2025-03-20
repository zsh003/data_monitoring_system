package cn.qqcn.common.controller;

import cn.qqcn.Crane.service.CraneService;
import cn.qqcn.user.entity.Kind;
import cn.qqcn.user.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class CommonController {
    @Autowired
    private CraneService craneService;
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/pwdEdit")
    public String pwdEdit(){
        return "pwdEdit";
    }

    @GetMapping("/speed")
    public String test(){
        return "speed";
    }

    @GetMapping("/tem")
    public String tem(){
        return "tem";
    }

    @GetMapping("/yingli")
    public String yingli(){
        return "yingli";
    }

    @GetMapping("/dir_x")
    public String dir_x(){
        return "dir_x";
    }

    @GetMapping("/dir_y")
    public String dir_y(){
        return "dir_y";
    }

    @GetMapping("/datalist")
    public String datalist(){
        return "datalist";
    }

    @GetMapping("/errorselect")
    public String errorselect(){
        return "errorselect";
    }

    @GetMapping("/applycheck")
    public String applycheck(){
        return "applycheck";
    }


    @GetMapping("/apply")
    public void apply(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
    request.setCharacterEncoding("UTF-8");
    response.setHeader("content-type", "text/html;charset=UTF-8");
    String sno= (String) session.getAttribute("sno");
    Kind t1=new Kind(sno,"待审核");
    userService.applymanage(t1);
    response.getWriter().write("<script language='javascript'>alert('申请成功');</script>");
    }




    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
