package cn.qqcn.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class crane {
    private int num;
    private String type;
    private float weight;
    private String sit;
}
