package cn.qqcn.service;

import cn.qqcn.entity.vo.EmpQuery;
import cn.qqcn.entity.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> getEmpList(EmpQuery param);
    Long countEmpList(EmpQuery param);
    void addEmp(Emp emp);
    void deleteEmpByIds(String ids);
    Emp getEmpById(Integer id);
    void updateEmp(Emp emp);
}
