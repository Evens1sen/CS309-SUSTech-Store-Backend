package com.project.store.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.project.store.entity.Orders;
import com.project.store.entity.OrdersComment;
import com.project.store.entity.User;
import com.project.store.enums.OrdersStatus;
import com.project.store.mapper.UserMapper;
import com.project.store.service.OrdersCommentService;
import com.project.store.service.OrdersService;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author HUAWEI
 */
@Api(tags = "OrdersCommentController")
@RestController
@RequestMapping("/ordersComment")
public class OrdersCommentController {

    @Autowired
    private OrdersCommentService ordersCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrdersService ordersService;


    @ApiOperation(value = "获取用户信誉/{id}")
    @GetMapping("/getOrdersComment/{id}")
    public int getOrdersComment(@PathVariable Integer id) {
        User buyer = userService.getById(id);
        return ordersCommentService.queryCredit(buyer.getUid());
    }

    @ApiOperation(value = "获取折扣力度")
    @GetMapping("/getDiscount/{id}")
    public double getDiscount(@PathVariable Integer id) {
        User buyer = userService.getById(id);
        return ordersCommentService.getDiscount(buyer.getUid());
    }


    @ApiOperation(value = "对订单进行评价同时更新信誉， 订单状态为4")
    @PostMapping("commentOrders/{orders_id}/{sell_id}/{comment}/{star}")
    public boolean commentOrders(@PathVariable Integer orders_id, @PathVariable Integer sell_id, @PathVariable String comment, @PathVariable Double star) {
        // 买家每完成一次订单， 给他加100分
        // 买家每次被评论后， 更新卖家信誉
        Orders orders = ordersService.getById(orders_id);
        if (orders.getStatus() == OrdersStatus.CONFIRMED) {
            OrdersComment ordersComment = new OrdersComment();
            User buyer = userService.getById(StpUtil.getLoginIdAsInt());
            User owner = userService.getById(sell_id);
            owner.setCredit(ordersCommentService.updateCredit(sell_id, star));
            buyer.setCredit(buyer.getCredit() + 10);
            userService.saveOrUpdate(buyer);
            userService.saveOrUpdate(owner);
            ordersComment.setId(5);
            ordersComment.setStar(star);
            ordersComment.setComments(comment);
            ordersComment.setSell_id(sell_id);
            ordersComment.setBuy_id(buyer.getUid());
            ordersComment.setOrders_id(orders_id);
            ordersComment.setCreate_at(LocalDateTime.now());
            orders.setStatus(OrdersStatus.CLOSED);
            ordersService.saveOrUpdate(orders);
            return ordersCommentService.save(ordersComment);
        } else {
            return false;
        }
    }

}
