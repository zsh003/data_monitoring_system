package cn.qqcn.service.impl;


import cn.qqcn.entity.Kind;
import cn.qqcn.entity.Pwd;
import cn.qqcn.entity.User;
import cn.qqcn.entity.apply;
import cn.qqcn.mapper.UserMapper;
import cn.qqcn.service.UserService;
import cn.qqcn.entity.vo.ApplyVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getUser(user);
    }

//    @Override
//    public void updatePwdById(User user) {userMapper.updatePwdById(user);}

    @Override
    public void updatePwdById(Pwd pwd) {
        userMapper.updatePwdById(pwd);
    }

    @Override
    public void applymanage(Kind kind) {
        userMapper.applymanage(kind);
    }

    @Override
    public List<apply> getapplyList(ApplyVO param) {
        return userMapper.getapplyList(param);
    }


    @Override
    public Long countapplyList(ApplyVO param) {
        return userMapper.countapplyList(param);
    }


}
