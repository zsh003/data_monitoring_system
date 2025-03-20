package cn.qqcn.service.impl;

import cn.qqcn.entity.Emp;
//import cn.qqcn.emp.mapper.DeptMapper;
import cn.qqcn.mapper.EmpMapper;
import cn.qqcn.service.EmpService;
import cn.qqcn.entity.vo.EmpQuery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

//    @Resource
//    private DeptMapper deptMapper;

    @Override
    public List<Emp> getEmpList(EmpQuery param) {
        return empMapper.getEmpList(param);
    }

    @Override
    public Long countEmpList(EmpQuery param) {
        return empMapper.countEmpList(param);
    }

    @Override
    public void addEmp(Emp emp) {
        empMapper.addEmp(emp);
    }

//    @Override
//    public List<Dept> getAllDept() {
//        return deptMapper.getAllDept();
//    }

    @Override
    public void deleteEmpByIds(String ids) {
        empMapper.deleteEmpByIds(ids);
    }

    @Override
    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }
}
