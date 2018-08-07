package com.oo.interceptor.controller;

import com.oo.interceptor.entity.UserEntity;
import com.oo.interceptor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName
 * @Description TODO
 * @Author zhaoyuanyuan-ds
 * @Date 2018/8/1 17:37
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/user")
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return userService.login(userEntity, request, response);
    }

    @RequestMapping(value = "/index")
    public String index() {
        throw new RuntimeException("edit person throw exception");
    }

    @RequestMapping(value = "/login_view")
    public String login_view() {

        return "please login!";
    }
}
