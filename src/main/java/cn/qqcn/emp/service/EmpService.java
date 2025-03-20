package cn.qqcn.emp.service;

import cn.qqcn.emp.vo.EmpQuery;
import cn.qqcn.emp.entity.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> getEmpList(EmpQuery param);
    Long countEmpList(EmpQuery param);
    void addEmp(Emp emp);
    void deleteEmpByIds(String ids);
    Emp getEmpById(Integer id);
    void updateEmp(Emp emp);
}
