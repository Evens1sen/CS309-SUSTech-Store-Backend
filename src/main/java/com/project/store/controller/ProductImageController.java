package com.project.store.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.entity.ProductImage;
import com.project.store.service.ProductImageService;
import com.project.store.util.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "ProductImageController")
@RestController
@RequestMapping("/productImage")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @ApiOperation(value = "根据商品id获取所有商品图片")
    @GetMapping("/listProductImageByProductId/{id}")
    public List<String> listProductImageByProductId(@PathVariable Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("product_id", id);
        List<ProductImage> productImageList = productImageService.list(wrapper);
        return productImageList.stream().map(ProductImage::getImage).collect(Collectors.toList());
    }

    @ApiOperation(value = "上传商品图片")
    @PostMapping("/addProductImage/{productId}")
    public void addProductImage(@RequestBody String baseStr, @PathVariable Integer productId) {
        // Random generate a file name
        String objectName = ImageUtil.generateObjectName(productId.toString(), 4);
        String url = ImageUtil.postImage(baseStr, objectName);
        ProductImage productImage = new ProductImage();
        productImage.setImage(url);
        productImage.setProductId(productId);
        productImageService.save(productImage);
    }

    @ApiOperation(value = "删除商品图片")
    @DeleteMapping("/deleteProductImage/{productId}/{objectName}")
    public boolean deleteProductImage(@PathVariable Integer productId, @PathVariable String objectName) {
        ImageUtil.deleteImage(objectName);
        return productImageService.removeById(productId);
    }


}
