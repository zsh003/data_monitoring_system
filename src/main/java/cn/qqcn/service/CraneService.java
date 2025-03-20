package cn.qqcn.service;

import cn.qqcn.entity.crane;

public interface CraneService {
    public crane CraneSit();

    public Long CountOn();

    public Long CountOff();
}
