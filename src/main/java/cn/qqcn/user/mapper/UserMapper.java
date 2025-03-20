package cn.qqcn.user.mapper;

import cn.qqcn.user.entity.apply;
import cn.qqcn.user.vo.Apply;
import cn.qqcn.user.entity.Kind;
import cn.qqcn.user.entity.Pwd;
import cn.qqcn.user.entity.User;


import java.util.List;

public interface UserMapper {
    public User getUser(User user);
     void  updatePwdById(Pwd pwd);
     void applymanage(Kind kind);
    List<apply> getapplyList(Apply param);
    Long countapplyList(Apply param);
}
