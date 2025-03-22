package cn.qqcn.entity.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private ResultVO(){}
    private ResultVO(Integer code, String message, T data, Long count) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    private Integer code;   // 返回码，0成功
    private String message; // 返回描述
    private T data;         // 返回数据
    private Long count;     // 分页查询的总记录数

    public static ResultVO<Object> success(){return new ResultVO(0,"success",null,null);}

    public static ResultVO<Object> success(String message){
        return new ResultVO(0,message,null,null);
    }

    public static ResultVO<Object> success(Object data, Long count){
        return new ResultVO(0,"success",data,count);
    }

    public static ResultVO<Object> fail(){
        return new ResultVO(-1,"fail",null,null);
    }

    public static ResultVO<Object> fail(String message){
        return new ResultVO(-1,message,null,null);
    }
}
