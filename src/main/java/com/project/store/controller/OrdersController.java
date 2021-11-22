package com.project.store.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.project.store.entity.Orders;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    //FIXME: add more restrictions on orders, and money
    @ApiOperation(value = "添加订单，订单状态为0")
    @PostMapping("/add/{useAddress}")
    public boolean add(@RequestBody Product product, @PathVariable String useAddress, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ordersService.save(product, useAddress, user);
    }

//    @ApiOperation(value = "支付订单，订单状态改变为1")
//    public boolean pay() {
//        return false;
//    }
//
//    @ApiOperation(value = "关闭订单，订单状态改变为2")
//    @PutMapping("close/{id}")
//    public boolean closeById(@PathVariable Integer id) {
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("id", id);
//        Orders orders = ordersService.getById(id);
//        if (orders.getStatus() == 0) {
//            UpdateWrapper wrapper1 = new UpdateWrapper();
//            wrapper1.set("status", 2);
//            wrapper1.eq("id", id);
//            return ordersService.update(wrapper);
//        }
//
//        return false;
//    }

    @ApiOperation(value = "彻底删除订单")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return ordersService.removeById(id);
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

