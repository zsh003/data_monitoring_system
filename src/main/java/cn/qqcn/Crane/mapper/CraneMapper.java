package cn.qqcn.Crane.mapper;

import cn.qqcn.Crane.entity.crane;


public interface CraneMapper {
        public crane CraneSit();
        public Long CountOn();
        public Long CountOff();

}
