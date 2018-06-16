package com.github.cumtfc.srs.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String requestURL = request.getRequestURL().toString();
        boolean isLogin = requestURL.lastIndexOf("auth/login") == 0;
        boolean hasLogin = request.getSession().getAttribute("user") != null;
        if (isLogin || hasLogin) {
            return super.preHandle(request, response, handler);
        }else {
            return false;
        }
    }
}
