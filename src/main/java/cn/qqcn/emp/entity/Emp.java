package cn.qqcn.emp.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Emp implements Serializable {
    private Integer sno;
    private String name;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;
}
