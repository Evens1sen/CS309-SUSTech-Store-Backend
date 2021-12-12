package com.project.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.ProductImage;
import com.project.store.service.ProductImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ProductImageController")
@RestController
@RequestMapping("/productImage")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @ApiOperation(value = "根据商品id获取所有商品图片")
    @GetMapping("/listProductImageByProductId/{id}")
    public List<ProductImage> listProductImageByProductId(@PathVariable Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("product_id", id);
        return productImageService.list(wrapper);
    }

    @ApiOperation(value = "根据商品id上传图片url")
    @PostMapping("/addProductImage/{id}/{url}")
    public boolean addProductImage(@PathVariable Integer id, @PathVariable String url) {
        ProductImage productImage = new ProductImage();
        productImage.setProductId(id);
        productImage.setImage(url);
        return productImageService.save(productImage);
    }
}
