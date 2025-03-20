package cn.qqcn.user.service;
import cn.qqcn.user.entity.Kind;
import cn.qqcn.user.entity.Pwd;
import cn.qqcn.user.entity.User;
import cn.qqcn.user.entity.apply;
import cn.qqcn.user.vo.Apply;

import java.util.List;


public interface UserService {
    public User login(User user);
    void  updatePwdById(Pwd pwd);
    void  applymanage(Kind kind);
    List<apply> getapplyList(Apply param);
    Long countapplyList(Apply param);

}