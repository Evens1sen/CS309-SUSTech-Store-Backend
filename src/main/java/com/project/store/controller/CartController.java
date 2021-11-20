package com.project.store.controller;


import com.project.store.entity.Cart;
import com.project.store.entity.User;
import com.project.store.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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

    @ApiOperation(value = "加入收藏夹")
    @GetMapping("/addCart/{productId}")
    public boolean addCart(@PathVariable Integer productId, HttpSession session) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        return cartService.save(cart);
    }
}

