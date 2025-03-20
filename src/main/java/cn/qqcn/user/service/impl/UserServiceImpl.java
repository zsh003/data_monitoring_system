package cn.qqcn.user.service.impl;


import cn.qqcn.user.entity.Kind;
import cn.qqcn.user.entity.Pwd;
import cn.qqcn.user.entity.User;
import cn.qqcn.user.entity.apply;
import cn.qqcn.user.mapper.UserMapper;
import cn.qqcn.user.service.UserService;
import cn.qqcn.user.vo.Apply;
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
    public List<apply> getapplyList(Apply param) {
        return userMapper.getapplyList(param);
    }


    @Override
    public Long countapplyList(Apply param) {
        return userMapper.countapplyList(param);
    }


}
