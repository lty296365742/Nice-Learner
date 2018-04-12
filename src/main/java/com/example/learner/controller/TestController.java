package com.example.learner.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@RestController
@RequestMapping(name = "/test")
@Api(value="测试接口",tags={"测试Api"})
public class TestController {

    @Resource
    private HttpServletRequest req;

    @GetMapping("/session")
    @ApiOperation(value = "查看浏览器保存的用户session",httpMethod = "GET")
    public Object  uid() {
        HttpSession session = req.getSession();
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("message", session.getAttribute("user"));
        return map;

    }
}
