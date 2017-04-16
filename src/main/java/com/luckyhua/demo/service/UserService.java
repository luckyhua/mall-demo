package com.luckyhua.demo.service;

import com.luckyhua.demo.dao.UserMapper;
import com.luckyhua.demo.enums.UserEnums;
import com.luckyhua.demo.model.User;
import com.luckyhua.demo.model.UserExample;
import com.luckyhua.demo.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务类
 *
 * @author luckyhua
 * @version 1.0
 * @date 2017/4/16
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public User login(String username, String password) {
        User user = this.findByUsername(username);
        AssertUtils.notNull(UserEnums.USER_NOT_EXISTS, user);
        AssertUtils.isTrue(UserEnums.PASSWORD_ERROR, password.equals(user.getPassword()));
        return user;
    }

    /**
     * 用户注册
     * @param user 用户填写的注册信息
     * @return 用户
     */
    public User register(User user) {
        User user1 = this.findByUsername(user.getUsername());
        AssertUtils.isNull(UserEnums.USER_IS_EXISTS, user1);
        userMapper.insertSelective(user);
        return user;
    }

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 用户
     */
    public User findByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

}
