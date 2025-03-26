package cn.qqcn.entity;

import cn.qqcn.common.vo.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorData{
    private Integer plcId;
    private Date time;
    private Double temperature;
    private Double speed;
    private Double light;
    private Double angle;
}
