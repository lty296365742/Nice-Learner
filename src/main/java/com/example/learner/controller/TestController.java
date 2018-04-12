package com.example.learner.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiQian_Nice on 2018/4/12
 */

@Controller
@RequestMapping(value = "/test")
@Api(value="测试接口",tags={"测试Api"})
public class TestController {

    @Resource
    private HttpServletRequest req;

    @GetMapping("/session")
    @ResponseBody
    @ApiOperation(value = "查看浏览器保存的用户session",httpMethod = "GET")
    public Object  uid() {
        HttpSession session = req.getSession();
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("message", session.getAttribute("user"));
        return map;

    }

    @GetMapping("/admin")
    public String admin(){
        return "/admin/admin";
    }

    @GetMapping("/comment")
    public String comment(){
        return "/admin/comment";
    }
    @GetMapping("/notes")
    public String notes(){
        return "/admin/notes";
    }
    @GetMapping("/student")
    public String student(){
        return "/admin/student";
    }
    @GetMapping("/teacher")
    public String teacher(){
        return "/admin/teacher";
    }





}
