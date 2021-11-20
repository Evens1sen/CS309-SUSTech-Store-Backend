package com.project.store.controller;


import com.project.store.entity.Product;
import com.project.store.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Api(tags = "ProductController")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("获取所有商品列表")
    @GetMapping("/list")
    public List<Product> list() {
        return productService.list();
    }

    @ApiOperation(value = "根据分类id获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level", value = "分类层级(one, two, three)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "分类号", required = true, dataType = "int")}
    )
    @GetMapping("/list/{level}/{id}")
    public List<Product> listByCategoryId(@PathVariable String level, @PathVariable Integer id) {
        return productService.findByCategoryId(level, id);
    }

    @ApiOperation(value = "获取商品详情")
    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable Integer id) {
        return productService.getById(id);
    }


}

