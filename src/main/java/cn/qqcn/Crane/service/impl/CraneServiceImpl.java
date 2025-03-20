package cn.qqcn.Crane.service.impl;
import cn.qqcn.Crane.entity.crane;
import cn.qqcn.Crane.mapper.CraneMapper;
import cn.qqcn.Crane.service.CraneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CraneServiceImpl implements CraneService {
    @Resource
    private CraneMapper craneMapper;
    @Override
    public crane CraneSit() {
        return craneMapper.CraneSit();
    }
    @Override
    public Long CountOn(){return craneMapper.CountOn();}
    @Override
    public Long CountOff(){return craneMapper.CountOff();}
}

