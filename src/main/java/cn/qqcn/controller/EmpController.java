package cn.qqcn.controller;
import cn.qqcn.entity.vo.ResultVO;
import cn.qqcn.entity.Emp;
import cn.qqcn.service.EmpService;
import cn.qqcn.entity.vo.EmpQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class  EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("")
    public String toEmpListUI(HttpSession session){
        String kind= (String) session.getAttribute("kind");
        if(kind.equals("管理员"))
            return "emp/empList";
        else if (kind.equals("用户"))
            return "emp/empList";
        else
            return "/error";
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultVO<Object> getEmpList(EmpQuery param) {
        List<Emp> list = empService.getEmpList(param);
        Long count = empService.countEmpList(param);
        return ResultVO.success(list, count);
    }

    @PostMapping("")
    @ResponseBody
    public ResultVO<Object> addEmp(Emp emp, HttpSession session){
        System.out.println(emp);
        empService.addEmp(emp);
        return ResultVO.success("新增用户成功！");
    }

    @GetMapping("/add/ui")
    public String toAddUI(Model model,HttpSession session){
        String kind= (String) session.getAttribute("kind");
        if (kind.equals("用户"))
            return "/error";
        else
        return "emp/empAdd";
    }

    @DeleteMapping("/{ids}")
    @ResponseBody
    public ResultVO<Object> deleteEmpByIds(@PathVariable("ids") String ids){
        empService.deleteEmpByIds(ids);
        return ResultVO.success("删除用户成功！");
    }

    @GetMapping("/{id}")
    public String getEmpById(@PathVariable("id") Integer id,Model model,HttpSession session){
        Emp emp = empService.getEmpById(id);
        model.addAttribute("emp",emp);
        String kind= (String) session.getAttribute("kind");
        if (kind.equals("用户"))
            return "/error";
        else
        return "emp/empEdit";
    }

    @PutMapping("")
    @ResponseBody
    public ResultVO<Object> updateEmp(Emp emp){
        empService.updateEmp(emp);
        return ResultVO.success("用户信息修改成功！");
    }
}
