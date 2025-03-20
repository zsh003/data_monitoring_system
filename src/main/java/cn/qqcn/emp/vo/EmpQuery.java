package cn.qqcn.emp.vo;
import cn.qqcn.common.vo.Page;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class EmpQuery extends Page {
    private String sno;
    private String name;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;


}
