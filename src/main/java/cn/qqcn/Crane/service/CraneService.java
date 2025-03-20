package cn.qqcn.Crane.service;

import cn.qqcn.Crane.entity.crane;

public interface CraneService {
    public crane CraneSit();

    public Long CountOn();

    public Long CountOff();
}
