package cn.qqcn.common.exception;

import cn.qqcn.entity.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVO<Object> myHandler(Exception e){
        return ResultVO.fail("系统错误：" + e.getMessage());
    }
}
