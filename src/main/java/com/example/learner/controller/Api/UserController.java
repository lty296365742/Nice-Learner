package com.example.learner.controller.Api;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.example.learner.bean.User;
import com.example.learner.service.impl.RedisService;
import com.example.learner.service.impl.UserServiceImpl;
import com.example.learner.util.AliyunMessageUtil;
import com.example.learner.util.EmailUtil;
import com.example.learner.util.LqNiceUtil;
import com.example.learner.util.ResultUtil;
import io.github.isliqian.NiceEmail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by LiQian_Nice on 2018/3/20
 */
@Controller
@Api(value="用户管理接口",tags={"用户管理Api"})//接口简要标注，对中文的支持不太好
@RequestMapping(value = "/api/users")//接口基本路径
public class UserController {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserServiceImpl userService;

    @Resource
    private RedisService redisService ;

    private User user;
    private AuthenticateSigRequest request =null;
    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "获取全部用户的信息",httpMethod = "GET",response = User.class)
    public Object findAll(){
        return ResultUtil.success(userService.findAll());
    }

    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "通过用户姓名和密码查询账号是否存在",httpMethod = "POST")
    public Object doLogin(@ModelAttribute("user") User user, String sessionId, String sig, String Token, String scene, HttpServletRequest req) throws Exception {
        IAcsClient client = LqNiceUtil.setUp();
        init(sessionId,sig,Token,scene);
        try {
            AuthenticateSigResponse response = client.getAcsResponse(request);
            if(response.getCode() == 100) {
                System.out.println("验签通过");
                logger.info(user.getName(),user.getPassword());
                UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
                Subject subject = SecurityUtils.getSubject();
                try {
                    subject.login(token);	// 执行登录
                    req.getSession().setAttribute("user",user);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResultUtil.error(1,"登陆失败");
                }
            } else {
                System.out.println("验签失败");
                return ResultUtil.error(1,"登陆失败");
            }
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(1,"登陆失败");
        }
        return ResultUtil.success();

    }

    @GetMapping("/password")
    @ResponseBody
    @ApiOperation(value = "找回密码",httpMethod = "GET")
    public Object findPassword(String account,String sessionId,String sig,String Token,String scene) throws Exception {

        IAcsClient client = LqNiceUtil.setUp();
        init(sessionId,sig,Token,scene);
        try {
            AuthenticateSigResponse response = client.getAcsResponse(request);
            if(response.getCode() == 100) {
                System.out.println("验签通过");
                user=userService.findByAccount(account);
                if(LqNiceUtil.checkEmail(account)){
                    //判断邮箱
                user.setPlainPassword(EmailUtil.sendMail(account));
                }else if (LqNiceUtil.checkMobileNumber(account)){
                    //判断手机

                    AliyunMessageUtil.sendMsg(account);
                    user.setPlainPassword(AliyunMessageUtil.code);
                }else {
                    return ResultUtil.error(100,"手机或邮箱格式错误");
                }

            } else {
                System.out.println("验签失败");
                return ResultUtil.error(101,"1信息验证失败");
            }
            // TODO
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(102,"2信息验证失败");
        }
        return ResultUtil.success(user);
    }

    @PostMapping("/password")
    @ResponseBody
    @ApiOperation(value = "找回密码",httpMethod = "POST")
    public Object setNewPassword(String password){
        user.setCreateTime(LqNiceUtil.getCurrentDateTime());
        user.setPlainPassword(password);
        LqNiceUtil.entryptPassword(user);
        userService.update(user);
        logger.info("username",user.getId());
        return ResultUtil.success();

    }

    public void init(String sessionId,String sig,String Token,String scene){
        request = new AuthenticateSigRequest();
        request.setSessionId(sessionId);// 必填参数，从前端获取，不可更改，android和ios只变更这个参数即可，下面参数不变保留xxx
        request.setSig(sig);// 必填参数，从前端获取，不可更改
        request.setToken(Token);// 必填参数，从前端获取，不可更改
        request.setScene(scene);// 必填参数，从前端获取，不可更改
        request.setAppKey("FFFF0N00000000005BE9");// 必填参数，后端填写
        request.setRemoteIp("192.168.137.162");// 必填参数，后端填写
    }

}
