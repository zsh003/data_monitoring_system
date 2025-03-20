package cn.qqcn.mapper;

import cn.qqcn.entity.crane;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CraneMapper {
        public crane CraneSit();
        public Long CountOn();
        public Long CountOff();

}
