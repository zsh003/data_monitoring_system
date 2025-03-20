package cn.qqcn.service.impl;
import cn.qqcn.entity.crane;
import cn.qqcn.mapper.CraneMapper;
import cn.qqcn.service.CraneService;
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

