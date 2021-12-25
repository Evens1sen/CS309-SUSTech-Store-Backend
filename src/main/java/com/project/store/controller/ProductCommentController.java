package com.project.store.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.project.store.entity.ProductComment;
import com.project.store.entity.User;
import com.project.store.service.ProductCommentService;
import com.project.store.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Api(tags = "ProductCommentController")
@RestController
@RequestMapping("/productComment")
public class ProductCommentController {
    @Autowired
    private ProductCommentService productCommentService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "对商品进行评价")
    @PostMapping("/commentProduct/{product_id}/{comment}")
    public boolean commentProduct(@PathVariable Integer product_id,@PathVariable String comment){
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        ProductComment productComment = new ProductComment();
        productComment.setComments(comment);
        productComment.setProduct_id(product_id);
        productComment.setBuyer_id(user.getUid());
        productComment.setId(5);
        productComment.setCreate_at(LocalDateTime.now());
        return productCommentService.save(productComment);
    }
}
