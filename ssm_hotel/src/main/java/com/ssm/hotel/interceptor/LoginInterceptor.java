package com.ssm.hotel.interceptor;

import com.ssm.hotel.utils.SystemConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session是否为空
        if(request.getSession().getAttribute(SystemConstant.LOGINUSER)==null){
            //如果没有登录，则去到登录页面
            response.sendRedirect(request.getContextPath()+"/admin/login.html");
            return false;//验证失败，进行拦截
        }
        //验证通过
        return true;
    }
}
