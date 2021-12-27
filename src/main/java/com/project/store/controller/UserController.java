package com.project.store.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.dto.Result;
import com.project.store.dto.ResultCode;
import com.project.store.dto.UserLoginParam;
import com.project.store.dto.UserRegisterParam;
import com.project.store.entity.User;
import com.project.store.service.UserService;
import com.project.store.util.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        User user = new User();
        user.setUid(userRegisterParam.getUid());
        user.setPassword(SaSecureUtil.md5(userRegisterParam.getPassword()));
        user.setNickName(userRegisterParam.getNickName());
        user.setEmail(userRegisterParam.getEmail());
        user.setCredit(100);

        boolean result;
        try {
            result = userService.save(user);
        } catch (Exception e) {
            return new Result<>(ResultCode.FAILED, "uid或昵称已存在");
        }

        if (result) {
            return new Result<>(ResultCode.SUCCESS, "注册成功");
        }
        return new Result<>(ResultCode.FAILED, "注册失败");
    }

    @ApiOperation(value = "发送验证码")
    @PostMapping("/verifyEmail/{email}")
    public String verifyEmail(@PathVariable String email) {
        return userService.sendVerification(email);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<SaTokenInfo> login(@Valid @RequestBody UserLoginParam userLoginParam) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", userLoginParam.getUid());
        User user = userService.getOne(wrapper);
        if (user == null) {
            return new Result<>(ResultCode.UNREGISTERED, null);
        }
        if (StpUtil.isDisable(user.getUid())){
            return new Result<>(ResultCode.DISABLED, null);
        }
        if (!Objects.equals(user.getPassword(), SaSecureUtil.md5(userLoginParam.getPassword()))) {
            return new Result<>(ResultCode.WRONG_PASSWORD, null);
        }
        StpUtil.login(user.getUid());
        return new Result<>(StpUtil.getTokenInfo());
    }

    @ApiOperation(value = "检测是否登录")
    @GetMapping("/isLogin")
    public boolean isLogin() {
        return StpUtil.isLogin();
    }

    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public String logout() {
        StpUtil.logout();
        return "登出成功";
    }

    @ApiOperation("踢人下线")
    @PutMapping("/kickout/{uid}")
    public String kickout(@PathVariable Integer uid){
        StpUtil.kickout(uid);
        return "踢人成功";
    }

    @ApiOperation("封号")
    @PutMapping("/disable/{uid}/{hour}")
    public String disableUser(@PathVariable Integer uid, @PathVariable Integer hour){
        StpUtil.kickout(uid);
        StpUtil.disable(uid, hour * 3600);
        return String.format("封禁用户%d, %d小时", uid, hour);
    }

    @ApiOperation("解封")
    @PutMapping("/untieDisable/{uid}")
    public String untieDisableUser(@PathVariable Integer uid){
        StpUtil.untieDisable(uid);
        return "解封成功";
    }

    @ApiOperation(value = "获取所有用户")
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
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

    @ApiOperation(value = "修改用户密码")
    @PostMapping("/changePassword/{password}")
    public boolean changePassword(@PathVariable String password){
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        user.setPassword(SaSecureUtil.md5(password));
        return userService.saveOrUpdate(user);
    }

    @ApiOperation(value = "修改用户昵称")
    @PostMapping("/changeNickname/{nickName}")
    public boolean changeNickname(@PathVariable String nickName){
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        user.setNickName(nickName);
        return userService.saveOrUpdate(user);
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

    @ApiOperation("添加用户头像")
    @PostMapping("/addUserIcon")
    public boolean addUserIcon(@RequestBody String[] baseStr) {
        // Random generate a file name
        String base = baseStr[0];
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        String objectName = ImageUtil.generateObjectName(user.getUid().toString(), 4);
        String url = ImageUtil.postImage(base, objectName);
        user.setIcon(url);
        return userService.saveOrUpdate(user);
    }

    @ApiOperation("删除用户头像")
    @DeleteMapping("deleteUserIcon")
    public boolean deleteUserIcon() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        user.setIcon("");
        String[] temp = user.getIcon().split("/");
        String objectName = temp[temp.length - 1];
        ImageUtil.deleteImage(objectName);
        return userService.saveOrUpdate(user);
    }

    @ApiOperation("注销用户")
    @DeleteMapping("deleteById/{uid}")
    public boolean deleteById(@PathVariable Integer uid) {
        return userService.removeById(uid);
    }
}


