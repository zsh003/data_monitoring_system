package cn.qqcn.service;
import cn.qqcn.entity.vo.DataQuery;
import cn.qqcn.entity.*;

import java.util.List;

public interface DataService {
    List<speed> findSpeed();

    List<tem> findTem();

    List<yingli> findYingli();

    List<dirx> findDir_x();

    List<diry> findDir_y();

    List<data> getDataList(DataQuery param);
    Long countDataList(DataQuery param);
    List<data> errorselect(DataQuery param);
    Long counterrorList(DataQuery param);

    List<data> findDataByTime(String start, String end);
}
