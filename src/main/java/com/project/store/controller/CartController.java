package com.project.store.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.project.store.entity.Cart;
import com.project.store.entity.Product;
import com.project.store.entity.User;
import com.project.store.service.CartService;
import com.project.store.service.UserService;
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
@Api(tags = "CartController")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "加入收藏夹")
    @PostMapping("/addCart/{productId}")
    public boolean addCart(@PathVariable Integer productId) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        cart.setUserId(user.getUid());
        return cartService.save(cart);
    }

    @ApiOperation(value = "获取收藏列表")
    @GetMapping("/findAll")
    public List<Product> findAll() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        return cartService.findAllCartByUserId(user.getUid());
    }

    @ApiOperation(value = "根据收藏id删除收藏")
    @DeleteMapping("/deleteByCartId/{id}")
    public boolean deleteByCartId(@PathVariable Integer id) {
        return cartService.removeById(id);
    }
}

