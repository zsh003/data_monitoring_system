package cn.qqcn.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PwdMapper {
    void  updatePwdById(String pwd,Integer sno);
}
