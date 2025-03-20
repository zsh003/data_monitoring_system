package cn.qqcn.common.interceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginInfo");
        if(user == null){
            // 未登录
            log.debug("未登录请求：" + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        log.debug("放行请求：" + request.getRequestURI());
        return true;
    }
}
