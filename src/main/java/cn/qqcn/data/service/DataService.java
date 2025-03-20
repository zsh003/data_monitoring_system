package cn.qqcn.data.service;
import cn.qqcn.data.entity.*;
import cn.qqcn.data.vo.DataQuery;

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
