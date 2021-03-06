package com.project.store.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.project.store.entity.Orders;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.enums.OrdersStatus;
import com.project.store.enums.ProductStatus;
import com.project.store.enums.ProductType;
import com.project.store.service.CartService;
import com.project.store.service.OrdersService;
import com.project.store.service.ProductService;
import com.project.store.service.UserService;
import com.project.store.vo.OrdersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "OrdersController")
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "用户添加订单，订单状态为0")
    @PostMapping("/add/{productId}/{useAddress}")
    public Integer add(@PathVariable Integer productId, @PathVariable String useAddress) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        Product product = productService.getById(productId);
        product.setStatus(ProductStatus.SOLD);
        return ordersService.save(product, useAddress, user);
    }

    @ApiOperation(value = "用户支付订单，订单状态改变为1", notes = "仅对用户扣款")
    @PutMapping("/payById/{id}")
    public boolean payById(@PathVariable Integer id) {
        Orders orders = ordersService.getById(id);
        User buyer = userService.getById(orders.getBuyerId());
        User owner = userService.getById(orders.getOwnerId());
        Product product = productService.getById(orders.getProductId());
        boolean result;
        if (product.getType() == ProductType.SELL) {
            result = userService.pay(buyer.getUid(), orders.getCost());
        } else {
            result = userService.pay(owner.getUid(), orders.getCost());
        }

        if (!result) {
            return false;
        }
        orders.setStatus(OrdersStatus.PAYED);
        ordersService.saveOrUpdate(orders);

        product.setStatus(ProductStatus.SOLD);
        productService.saveOrUpdate(product);

        userService.sendNotification(owner.getEmail());
        return true;
    }

    @ApiOperation(value = "卖家确认订单，订单状态改变为2")
    @PutMapping("/confirmById/{id}")
    public boolean confirmById(@PathVariable String id) {
        Orders orders = ordersService.getById(id);
        if (orders.getStatus() == OrdersStatus.PAYED) {
            UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
            wrapper.set("status", 2);
            wrapper.eq("id", id);
            return ordersService.update(wrapper);
        }
        return false;
    }

    @ApiOperation(value = "用户确认收货，订单状态改变为3", notes = "对卖家转账")
    @PutMapping("close/{id}")
    public boolean closeById(@PathVariable Integer id) {
        Orders orders = ordersService.getById(id);
        if (orders.getStatus() == OrdersStatus.SHIPPED) {
            UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
            wrapper.set("status", 3);
            wrapper.eq("id", id);
            ordersService.update(wrapper);

            Product product = productService.getById(orders.getProductId());
            product.setStatus(ProductStatus.SOLD);

            User user;
            if (product.getType() == ProductType.SELL) {
                user = userService.getById(orders.getOwnerId());
            } else {
                user = userService.getById(orders.getBuyerId());
            }
            user.setBalance(user.getBalance() + orders.getCost());
            userService.saveOrUpdate(user);

            return productService.saveOrUpdate(product);
        }
        return false;
    }

    @ApiOperation(value = "彻底删除订单")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return ordersService.removeById(id);
    }

    @ApiOperation(value = "根据订单号获取订单VO")
    @GetMapping("/getOrdersVOByOrderId/{id}")
    public OrdersVO getOrdersVOByOrderId(@PathVariable Integer id) {
        return ordersService.findOrdersVOByOrdersId(id);
    }

    @ApiOperation(value = "获取当前登录用户所有购买订单")
    @GetMapping("/listBuy")
    public List<Orders> listBuy() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("buyer_id", user.getUid());
        return ordersService.list(wrapper);
    }

    @ApiOperation(value = "获取当前登录用户所有购买订单VO")
    @GetMapping("/listBuyVO")
    public List<OrdersVO> listBuyVO() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        return ordersService.findAllOrdersVOByBuyerID(user.getUid());
    }

    @ApiOperation(value = "获取当前登录用户所有卖出订单")
    @GetMapping("/listSell")
    public List<Orders> listSell() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("owner_id", user.getUid());
        return ordersService.list(wrapper);
    }

    @ApiOperation(value = "获取当前登录用户所有卖出订单VO")
    @GetMapping("/listSellVO")
    public List<OrdersVO> listSellVO() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        return ordersService.findAllOrdersVOByOwnerID(user.getUid());
    }
}

