package cn.qqcn.emp.mapper;

import cn.qqcn.emp.entity.Emp;
import cn.qqcn.emp.vo.EmpQuery;

import java.util.List;

public interface EmpMapper {
    List<Emp> getEmpList(EmpQuery param);
    Long countEmpList(EmpQuery param);
    void addEmp(Emp emp);
    void deleteEmpByIds(String ids);
    Emp getEmpById(Integer id);
    void updateEmp(Emp emp);
}
