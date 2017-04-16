package com.luckyhua.demo.controller;

import com.luckyhua.demo.enums.PublicEnums;
import com.luckyhua.demo.enums.UserEnums;
import com.luckyhua.demo.global.context.json.ResponseInfo;
import com.luckyhua.demo.global.context.utils.ResponseUtils;
import com.luckyhua.demo.model.User;
import com.luckyhua.demo.service.UserService;
import com.luckyhua.demo.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户控制类
 *
 * @author luckyhua
 * @version 1.0
 * @date 2017/4/16
 */
@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        AssertUtils.notNull(UserEnums.USER_IS_NULL, username);
        AssertUtils.notNull(UserEnums.PASSWORD_IS_NULL, password);
        AssertUtils.isTrue(UserEnums.PASSWORD_LENGTH_ERROR, password.length() >=6 && password.length() <= 18);
        User user = userService.login(username, password);
        session.setAttribute("user", user);

        ResponseInfo responseInfo = ResponseUtils.buildResponseInfo();
        responseInfo.putData("user", user);
        return responseInfo;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo register(@RequestBody User user, HttpSession session) {
        AssertUtils.notNull(PublicEnums.PARAMS_IS_NULL, user);
        AssertUtils.notNull(UserEnums.USER_IS_NULL, user.getUsername());
        AssertUtils.notNull(UserEnums.PASSWORD_IS_NULL, user.getPassword());
        AssertUtils.isTrue(UserEnums.PASSWORD_LENGTH_ERROR, user.getPassword().length() >=6 && user.getPassword().length() <= 18);
        User registerUser = userService.register(user);
        return this.login(registerUser.getUsername(), registerUser.getPassword(), session);
    }

}
