package com.oo.interceptor.intercepter;

import com.oo.interceptor.entity.UserEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName
 * @Description TODO
 * @Author zhaoyuanyuan-ds
 * @Date 2018/8/1 19:35
 * @Version 1.0
 **/
public class SessionIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(request.getRequestURI());
        if (request.getRequestURI().equals("/user/login")) {
            UserEntity session = (UserEntity)request.getSession().getAttribute("_session_user");
            if (session == null) {
                return true;
            }else {
                String name = request.getParameter("name");
                String pwd = request.getParameter("pwd");
                if(session.getName().equals(name) && session.getPwd().equals(pwd)){
                    response.sendRedirect("/user/index");
                }else {
                    return true;
                }

            }
        } else {
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
