package com.example.learner.mapper;

import com.example.learner.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by LiQian_Nice on 2018/4/11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    private Logger logger= LoggerFactory.getLogger(UserMapperTest.class);
    @Resource
    private UserMapper userMapper;

    @Test
    public void findByAccount(){
        User user=userMapper.findByAccount("1234560");
        logger.info(user.getRoleId().toString());
    }
}
