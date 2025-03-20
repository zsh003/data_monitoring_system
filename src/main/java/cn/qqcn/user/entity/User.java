package cn.qqcn.user.entity;

import lombok.Data;

@Data
public class User {
    private String sno;//工号
    private String pwd;//密码
    private String name;//名称
    private String kind;//类别
}
