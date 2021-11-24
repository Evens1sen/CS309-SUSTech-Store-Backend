package com.project.store.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.dto.UserLoginParam;
import com.project.store.dto.UserRegisterParam;
import com.project.store.entity.User;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

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
    public String register(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        User user = new User();
        user.setUid(userRegisterParam.getUid());
        user.setPassword(userRegisterParam.getPassword());
        user.setNickName(userRegisterParam.getNickName());
        user.setEmail(userRegisterParam.getEmail());

        boolean result;
        try {
            result = userService.save(user);
        } catch (Exception e) {
            return "uid或昵称已存在";
        }

        if (result) {
            return "注册成功";
        }
        return "注册失败";
    }

    @ApiOperation(value = "登录", notes = "")
    @PostMapping("/login")
    //FIXME: Salt hash the password
    //FIXME: Add the common result
    public SaTokenInfo login(@Valid @RequestBody UserLoginParam userLoginParam) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", userLoginParam.getUid());
        User user = userService.getOne(wrapper);
        if (user == null) {
//            return "请先注册账号";
            return null;
        }
        if (!Objects.equals(user.getPassword(), userLoginParam.getPassword())) {
//            return "密码错误";
            return null;
        }

        StpUtil.login(user.getUid());
//        return "登录成功";
        return StpUtil.getTokenInfo();
    }

    @ApiOperation(value = "登出", notes = "")
    @GetMapping("/logout")
    public String logout() {
        StpUtil.logout();
        return "登出成功";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据uid来获取用户详细信息")
    @ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "int")
    @GetMapping("/findById/{uid}")
    public User findById(@PathVariable("uid") Integer uid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return userService.getOne(wrapper);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/userInfo")
    public User userInfo() {
        return userService.getById(StpUtil.getLoginIdAsInt());
    }

    @ApiOperation(value = "为当前用户充值")
    @PutMapping("/addBalance/{amount}")
    public boolean addBalance(@PathVariable Float amount) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        user.setBalance(user.getBalance() + amount);
        return userService.saveOrUpdate(user);
    }

    @ApiOperation(value = "为当前用户提现")
    @PutMapping("/withdraw/{amount}")
    public boolean withdraw(@PathVariable Float amount) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        if (user.getBalance() - amount < 0) {
            return false;
        }
        user.setBalance(user.getBalance() - amount);
        return userService.saveOrUpdate(user);
    }
}


