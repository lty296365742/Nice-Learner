package com.example.learner.mapper;

import com.example.learner.bean.Role;
import com.example.learner.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by LiQian_Nice on 2018/3/20
 */
@Mapper
@Component//mapper层使用@Component注解
public interface UserMapper {
    /**
     * 查找全部用户
     * @return
     */
    List<User> findAll();

    /**
     * 通过昵称查找对应得用户
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 添加一位用户
     * @param user
     */
    void add(User user);

    /**
     * 根据rid查询对应的角色信息
     * @param rid
     * @return
     */
    Role findRoleByRid(Integer rid);

    /**
     * 根据rid查询相同角色的所有用户
     * @param rid
     * @return
     */
    List<User> findUsersByRid(Integer rid);

    /**
     * 根据id删除一位用户
     * @param id
     */
    void delete(Integer id);

    /**
     * 通过用户手机号或者邮箱查找用户
     * @param account
     * @return
     */
    @Select("select * from tb_user where email = #{account} or phone = #{account} or name = #{account}")
    User findByAccount(String account);

    /**
     * 更新用户信息
     * @param newUser
     */
    @Update("update tb_user set name= #{name} ,email = #{email},salt = #{salt},password = #{password},createTime= #{createTime},phone = #{phone},logo = #{logo},status = #{status},organization = #{organization} where id = #{id}")
    void update(User newUser);
}
