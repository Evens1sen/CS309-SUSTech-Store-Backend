package com.project.store.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.dto.UserAddressParam;
import com.project.store.entity.User;
import com.project.store.entity.UserAddress;
import com.project.store.service.UserAddressService;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Api(tags = "UserAddressController")
@RestController
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取登录用户所有地址")
    @GetMapping("/findAll")
    public List<UserAddress> findAll() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        return userAddressService.findAllUserAddressByUserId(user.getUid());
    }

    @ApiOperation(value = "为登录用户添加地址")
    @PostMapping("/add")
    public boolean add(@RequestBody UserAddressParam userAddressParam) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressParam, userAddress);
        userAddress.setIsdefault(userAddressParam.getIsDefault());
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        ;
        userAddress.setUserId(user.getUid());
        if (userAddressParam.getIsDefault() == 1) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id", user.getUid());
            wrapper.eq("isdefault", 1);
            UserAddress userAddress1 = userAddressService.getOne(wrapper);
            if (userAddress1 != null) {
                userAddress1.setIsdefault(0);
                userAddressService.updateById(userAddress1);
            }
        }
        return userAddressService.save(userAddress);
    }

    @ApiOperation(value = "根据地址id删除地址")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return userAddressService.removeById(id);
    }

    @ApiOperation(value = "修改地址")
    @PutMapping("/update/{id}")
    public boolean updateById(@RequestBody UserAddressParam userAddressParam, @PathVariable Integer id) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        UserAddress userAddress = userAddressService.getOne(wrapper);
        userAddress.setAddress(userAddressParam.getAddress());
        userAddress.setRemark(userAddressParam.getRemark());
        if (userAddressParam.getIsDefault() == 1) {
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("user_id", user.getUid());
            wrapper1.eq("isdefault", 1);
            UserAddress userAddress1 = userAddressService.getOne(wrapper1);
            if (userAddress1 != null) {
                userAddress1.setIsdefault(0);
                userAddressService.updateById(userAddress1);
            }
            userAddress.setIsdefault(1);
        }
        return userAddressService.updateById(userAddress);
    }
}

