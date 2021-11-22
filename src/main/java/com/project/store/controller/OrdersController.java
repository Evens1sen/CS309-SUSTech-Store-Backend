package com.project.store.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.Orders;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Api(tags = "OrdersController")
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/add/{useAddress}")
    public boolean add(@RequestBody Product product, @PathVariable String useAddress, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ordersService.save(product, useAddress, user);
    }

    //FIXME
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id){
        return false;
    }

    @PutMapping("close/{id}")
    public boolean closeById(@PathVariable Integer id){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        ordersService.update(wrapper);
        return false;
    }

    @ApiOperation(value = "获取当前登录用户所有购买订单")
    @GetMapping("/listBuy")
    public List<Orders> listBuy(HttpSession session) {
        User user = (User) session.getAttribute("user");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("buyer_id", user.getUid());
        ordersService.list();
        return ordersService.list(wrapper);
    }

    @ApiOperation(value = "获取当前登录用户所有卖出订单")
    @GetMapping("/listSell")
    public List<Orders> listSell(HttpSession session) {
        User user = (User) session.getAttribute("user");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("owner_id", user.getUid());
        ordersService.list();
        return ordersService.list(wrapper);
    }
}

