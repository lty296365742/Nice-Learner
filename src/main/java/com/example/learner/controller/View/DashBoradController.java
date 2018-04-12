package com.example.learner.controller.View;


import com.example.learner.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LiQian_Nice on 2018/3/24
 */
@Controller
@Api(value="控制页面跳转接口",tags={"总管理台接口Api"})//接口简要标注，对中文的支持不太好*/
public class DashBoradController {

    private Logger logger= LoggerFactory.getLogger(DashBoradController.class);
    @Resource
    private HttpServletRequest req;
   /* @GetMapping("/")
    @ApiOperation(value = "跳转到主页",httpMethod = "GET")
    public String dashboard() {
      //  HttpSession session=req.getSession();
       // User user= (User) session.getAttribute("user");
//        logger.info("获取到的用户session---"+user.getName());
        Subject subject = SecurityUtils.getSubject();
        try {
            if(subject.hasRole("admin")){
                //subject.checkRole("admin");
                return "/admin/dashboard";
            }else if (subject.hasRole("assessor")){

            }else if (subject.hasRole("teacher")){

            }else if (subject.hasRole("student")){
                //subject.checkRole("student");
                return "/login";
            }
        } catch (UnauthorizedException exception) {
            logger.info("没有足够的权限");
            return "/admin/login";
        }
        return "/admin/login";
    }*/


    @GetMapping("/login")
    @ApiOperation(value = "用户登陆页面",httpMethod = "GET")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/register")
    @ApiOperation(value = "用户注册页面",httpMethod = "GET")
    public String toRegister(){
        return "register";
    }



}
