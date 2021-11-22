package com.project.store.controller;


import com.project.store.entity.Product;
import com.project.store.service.ProductService;
import com.project.store.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.net.http.HttpClient;
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

    @ApiOperation(value = "根据id获取商品详情")
    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @ApiOperation(value = "根据id获取商品VO")
    @GetMapping("/findProductVOById/{id}")
    public ProductVO findProductVOById(@PathVariable Integer id) {
        return productService.findProductVOById(id);
    }

    @ApiOperation(value = "添加商品")
    @PostMapping("/add")
    public boolean add(@RequestBody Product product) {
        return productService.save(product);
    }

    @ApiOperation(value = "修改商品信息")
    @PutMapping("/update/{id}")
    public boolean update(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return productService.removeById(id);
    }

}

