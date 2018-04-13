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
@Api(value="管理员以及审核员跳转接口",tags={"管理员以及审核员接口Api"})//接口简要标注，对中文的支持不太好*/
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
                return "/background/dashboard";
            }else if (subject.hasRole("assessor")){

            }else if (subject.hasRole("teacher")){

            }else if (subject.hasRole("student")){
                //subject.checkRole("student");
                return "/background/login";
            }
        } catch (UnauthorizedException exception) {
            logger.info("没有足够的权限");
            return "/background/login";
        }
        return "/background/login";
    }
    @GetMapping("/signin")
    @ApiOperation(value = "跳转管理员登陆界面",httpMethod = "GET")
    public String toAdmin(){
        return "/background/login";
    }

    @GetMapping("/password_reset")
    @ApiOperation(value = "管理员找回密码界面",httpMethod = "GET")
    public String toPassword(){
        return "/background/forgot_password";
    }

    @GetMapping("/signout")
    @ApiOperation(value = "退出页面",httpMethod = "GET")
    public String logout(){
        HttpSession session=req.getSession();
        session.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/background/login";
    }
    @GetMapping("/signup")
    @ApiOperation(value = "注册界面",httpMethod = "GET")
    public String regist(){

        return "/background/register";
    }
    @GetMapping("/users")
    @ApiOperation(value = "跳转到管理用户所有",httpMethod = "GET")
    public String users(){
        return "/background/users";
    }
    @GetMapping("/student/hobby")
    @ApiOperation(value = "用户爱好分析界面",httpMethod = "GET")
    public String hoby(){
        return "/background/hobby";
    }
    @GetMapping("/aspect")
    @ApiOperation(value = "课程方向管理界面",httpMethod = "GET")
    public String aspect(){
        return "/background/aspect";
    }
    @GetMapping("/category")
    @ApiOperation(value = "课程类别管理界面",httpMethod = "GET")
    public String category(){
        return "/background/category";
    }
    @GetMapping("/assessor")
    @ApiOperation(value = "审核员审核界面",httpMethod = "GET")
    public String assessor(){
        return "/background/assessor";
    }
    @GetMapping("/teacher")
    @ApiOperation(value = "教师审核界面",httpMethod = "GET")
    public String state(){
        return "background/teacher";
    }
    @GetMapping("/course")
    @ApiOperation(value = "课程审核界面",httpMethod = "GET")
    public String course(){
        return "/background/course";
    }

    @GetMapping("/users/message")
    @ApiOperation(value = "用户留言管理界面",httpMethod = "GET")
    public String message(){
            return "/background/users_message";
    }

    @GetMapping("/student/review")
    @ApiOperation(value = "用户评论管理界面",httpMethod = "GET")
    public String review(){
        return "/background/users_review";
    }

    @GetMapping("/files")
    @ApiOperation(value = "文件管理界面",httpMethod = "GET")
    public String files(){
        return "/background/files";
    }
    @GetMapping("/info")
    @ApiOperation(value = "个人信息界面",httpMethod = "GET")
    public String info(){
        return "/background/info";
    }
    @GetMapping("role")
    @ApiOperation(value = "管理员角色界面",httpMethod = "GET")
    public String role(){
        return "/background/role";
    }
    @GetMapping("manager")
    @ApiOperation(value = "管理员界面",httpMethod = "GET")
    public String manager(){
        return "/background/manager";
    }

    @GetMapping("email")
    @ApiOperation(value = "公共邮箱界面",httpMethod = "GET")
    public String email(){
        return "/background/email";
    }
    @GetMapping("student")
    @ApiOperation(value = "学生管理界面",httpMethod = "GET")
    public String student(){
        return "/background/student";
    }

}
