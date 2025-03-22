package cn.qqcn.controller;


import cn.qqcn.entity.Kind;
import cn.qqcn.entity.Pwd;
import cn.qqcn.entity.User;
import cn.qqcn.entity.vo.ResultVO;
import cn.qqcn.entity.apply;
import cn.qqcn.service.UserService;
import cn.qqcn.entity.vo.ApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultVO login(User param, @RequestParam("captcha") String captcha, HttpServletRequest request, HttpSession session){
        //暂时忽略验证码
//        if (!CaptchaUtil.ver(captcha, request)) {
//            return Result.fail("验证码错误！");
//        }
        User user = userService.login(param);
        System.out.print(user.getPwd());
        String kind= user.getKind();
        String sno= user.getSno();
        if(user != null){
            session.setAttribute("loginInfo",user);
            session.setAttribute("kind",kind);
            session.setAttribute("sno",sno);
            System.out.println(session.getAttribute("loginInfo"));
            return ResultVO.success();
        }
        return ResultVO.fail("用户或密码错误");

    }

    @PutMapping("/pwd")
    @ResponseBody
    public ResultVO<Object> updatePwdById(Pwd param){
        System.out.println(param);
        userService.updatePwdById(param);
        return ResultVO.success("密码修改成功！");
    }

    @GetMapping("/getList")
    @ResponseBody
    public ResultVO<Object> getapplyList(ApplyVO param){
        List<apply> list = userService.getapplyList(param);
        Long count = userService.countapplyList(param);
        return ResultVO.success(list,count);
    }
    @PutMapping("/{id}")
    public ResultVO<Object> agree(@PathVariable("id") String id){
     Kind kind=new Kind(id,"用户");
     userService.applymanage(kind);
     return ResultVO.success("修改成功！");
    }

    @PostMapping("/{ids}")
    public ResultVO<Object> refuse(@PathVariable("ids") String ids){
        Kind kind=new Kind(ids,"用户");
        userService.applymanage(kind);
        return ResultVO.success("修改成功！");
    }
}
