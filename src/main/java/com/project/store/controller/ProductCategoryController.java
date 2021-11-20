package com.project.store.controller;


import com.project.store.service.ProductCategoryService;
import com.project.store.vo.ProductCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "ProductCategoryController")
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation(value = "获取商品类别列表", notes = "按照分类嵌套存储类别VO对象")
    @GetMapping("/list")
    public List<ProductCategoryVO> list() {
        return productCategoryService.getAllProductCategoryVO();
    }
}

