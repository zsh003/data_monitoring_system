package cn.qqcn.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class data {
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int num;
    private String time;
    private float speed;
    private float tem;
    private float yingli;
    private float dir_x;
    private float dir_y;
}
