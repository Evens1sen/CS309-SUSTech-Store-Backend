package com.project.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.dto.UserLoginParam;
import com.project.store.dto.UserRegisterParam;
import com.project.store.entity.User;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-15
 */

@Api(tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册", notes = "")
    @PostMapping("/register")
    public String register(@Validated @RequestBody UserRegisterParam userRegisterParam) {
        User user = new User();
        user.setUserName(userRegisterParam.getUserName());
        user.setPassword(userRegisterParam.getPassword());
        user.setNickName(userRegisterParam.getNickName());
        user.setEmail(userRegisterParam.getEmail());

        boolean result = false;
        try {
            result = userService.save(user);
        } catch (Exception e) {
            return "register";
        }
        if (result) {
            return "login";
        }
        return "register";
    }

    @ApiOperation(value = "登录", notes = "")
    @PostMapping("/login")
    public String login(@Validated @RequestBody UserLoginParam userLoginParam, HttpSession session) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userLoginParam.getUserName());
        wrapper.eq("password", userLoginParam.getPassword());
        User user = userService.getOne(wrapper);
        if (user == null) {
            return "false";
        }

        session.setAttribute("user", user);
        return "success";
    }

    @ApiOperation(value = "登出", notes = "")
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据用户名来获取用户详细信息")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
    @GetMapping("getUser/{userName}")
    public User getAUser(@PathVariable("userName") String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", userName);
        User user = userService.getOne(wrapper);
        if (user == null) {
            return null;
        }
        return user;
    }
}


