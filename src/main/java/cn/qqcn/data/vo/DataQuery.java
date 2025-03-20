package cn.qqcn.data.vo;
import cn.qqcn.common.vo.Page;
import lombok.Data;

@Data
public class DataQuery extends Page {
    private int num;
    private String time;
    private float speed;
    private float tem;
    private float yingli;
    private float dir_x;
    private float dir_y;
}
