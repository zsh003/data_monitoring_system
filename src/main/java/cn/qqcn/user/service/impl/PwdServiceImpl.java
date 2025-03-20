package cn.qqcn.user.service.impl;
import cn.qqcn.user.service.PwdService;
import cn.qqcn.user.mapper.PwdMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PwdServiceImpl implements PwdService {

    @Resource
    private PwdMapper pwdMapper;

//    @Override
//    public void updatePwdById(User user) {userMapper.updatePwdById(user);}
    @Override
    public void updatePwdById(String pwd, Integer sno) {

    }
}