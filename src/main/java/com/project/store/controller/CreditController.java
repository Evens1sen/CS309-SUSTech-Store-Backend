package com.project.store.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.project.store.entity.User;
import com.project.store.mapper.UserMapper;
import com.project.store.service.CreditService;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HUAWEI
 */
@Api(tags = "CreditController")
@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    private CreditService creditService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信誉/{id}")
    @GetMapping("/getCredit/{id}")
    public int getCredit(@PathVariable Integer id){
        User buyer = userService.getById(id);
        return creditService.queryCredit(buyer.getUid());
    }

    @ApiOperation(value = "获取折扣力度")
    @GetMapping("/getDiscount/{id}")
    public double getDiscount(@PathVariable Integer id){
        User buyer = userService.getById(id);
        return creditService.getDiscount(buyer.getUid());
    }

    @ApiOperation(value = "给用户评价时增减信誉")
    @PutMapping("updateCreditByStar/{id}/{star}")
    public boolean updateCredit(@PathVariable Integer id,@PathVariable Double star){
        User user = userService.getById(id);
        int credit =  creditService.updateCredit(user.getUid(),star);
        user.setCredit(credit);
        userService.saveOrUpdate(user);
        return true;
    }

}
