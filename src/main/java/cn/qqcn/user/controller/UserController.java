package cn.qqcn.user.controller;


import cn.qqcn.emp.entity.Emp;
import cn.qqcn.user.entity.Kind;
import cn.qqcn.user.entity.Pwd;
import cn.qqcn.user.entity.User;
import cn.qqcn.common.vo.Result;
import cn.qqcn.user.entity.apply;
import cn.qqcn.user.service.UserService;
import cn.qqcn.user.vo.Apply;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(User param, @RequestParam("captcha") String captcha, HttpServletRequest request, HttpSession session){
        if (!CaptchaUtil.ver(captcha, request)) {
            return Result.fail("验证码错误！");
        }
        User user = userService.login(param);
        System.out.print(user.getPwd());
        String kind= user.getKind();
        String sno= user.getSno();
        if(user != null){
            session.setAttribute("loginInfo",user);
            session.setAttribute("kind",kind);
            session.setAttribute("sno",sno);
            System.out.println(session.getAttribute("loginInfo"));
            return Result.success();
        }
        return Result.fail("用户或密码错误");

    }

    @PutMapping("/pwd")
    @ResponseBody
    public Result<Object> updatePwdById(Pwd param){
        System.out.println(param);
        userService.updatePwdById(param);
        return Result.success("密码修改成功！");
    }

    @GetMapping("/getList")
    @ResponseBody
    public Result<Object> getapplyList(Apply param){
        List<apply> list = userService.getapplyList(param);
        Long count = userService.countapplyList(param);
        return Result.success(list,count);
    }
    @PutMapping("/{id}")
    public Result<Object> agree(@PathVariable("id") String id){
     Kind kind=new Kind(id,"用户");
     userService.applymanage(kind);
     return Result.success("修改成功！");
    }

    @PostMapping("/{ids}")
    public Result<Object> refuse(@PathVariable("ids") String ids){
        Kind kind=new Kind(ids,"用户");
        userService.applymanage(kind);
        return Result.success("修改成功！");
    }
}
