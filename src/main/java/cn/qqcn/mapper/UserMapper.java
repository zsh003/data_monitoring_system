package cn.qqcn.mapper;

import cn.qqcn.entity.apply;
import cn.qqcn.entity.vo.ApplyVO;
import cn.qqcn.entity.Kind;
import cn.qqcn.entity.Pwd;
import cn.qqcn.entity.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface UserMapper {
    public User getUser(User user);
     void  updatePwdById(Pwd pwd);
     void applymanage(Kind kind);
    List<apply> getapplyList(ApplyVO param);
    Long countapplyList(ApplyVO param);
}
