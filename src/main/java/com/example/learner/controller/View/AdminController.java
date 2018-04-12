package com.example.learner.controller.View;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by LiQian_Nice on 2018/4/12
 */
@Controller
@RequestMapping(value = "/admin")
@Api(value="管理员跳转接口",tags={"管理员接口Api"})//接口简要标注，对中文的支持不太好*/
public class AdminController {
    private Logger logger= LoggerFactory.getLogger(AdminController.class);
    @Resource
    private HttpServletRequest req;

    @GetMapping("/")
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
    }
    @GetMapping("/signin")
    @ApiOperation(value = "跳转管理员登陆界面",httpMethod = "GET")
    public String toAdmin(){
        return "/admin/login";
    }

    @GetMapping("/password_reset")
    @ApiOperation(value = "管理员找回密码界面",httpMethod = "GET")
    public String toPassword(){
        return "/admin/forgot_password";
    }

    @GetMapping("/signout")
    @ApiOperation(value = "退出页面",httpMethod = "GET")
    public String logout(){
        HttpSession session=req.getSession();
        session.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/admin/login";
    }
}
