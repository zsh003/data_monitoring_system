package cn.qqcn.service;
import cn.qqcn.entity.Kind;
import cn.qqcn.entity.Pwd;
import cn.qqcn.entity.User;
import cn.qqcn.entity.apply;
import cn.qqcn.entity.vo.ApplyVO;

import java.util.List;


public interface UserService {
    public User login(User user);
    void  updatePwdById(Pwd pwd);
    void  applymanage(Kind kind);
    List<apply> getapplyList(ApplyVO param);
    Long countapplyList(ApplyVO param);

}