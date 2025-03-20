package cn.qqcn.mapper;
import cn.qqcn.entity.vo.DataQuery;
import cn.qqcn.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DataMapper {
        public List<speed> findSpeed();
        public List<tem> findTem();
        public List<yingli> findYingli();
        public List<dirx> findDir_x();
        public List<diry> findDir_y();
        List<data> getDataList(DataQuery param);
        List<data> errorselect(DataQuery param);
        Long countDataList(DataQuery param);
        Long counterrorList(DataQuery param);

        List<data> byTime(@Param("start") String start, @Param("end") String end);

}
