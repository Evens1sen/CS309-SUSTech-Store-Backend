package com.project.store.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.store.dto.ProductDto;
import com.project.store.dto.SearchFilter;
import com.project.store.entity.Product;
import com.project.store.entity.ProductImage;
import com.project.store.entity.User;
import com.project.store.enums.ProductStatus;
import com.project.store.service.ProductImageService;
import com.project.store.service.ProductService;
import com.project.store.service.UserService;
import com.project.store.util.ImageUtil;
import com.project.store.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private UserService userService;

    @ApiOperation("获取所有商品列表")
    @GetMapping("/list")
    public List<Product> list() {
        return productService.list();
    }

    @ApiOperation(value = "获取所有未售出商品VO", notes = "默认按时间排序")
    @GetMapping("/listProductVO")
    public List<ProductVO> listProductVO() {
        return productService.findAllProductVO();
    }

    @ApiOperation(value = "获取未售出分页商品VO", notes = "默认按时间排序")
    @GetMapping("/findProductVOPage/{pageNum}/{pageSize}")
    public List<ProductVO> findProductVOPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return productService.findProductVOPage(pageNum, pageSize);
    }

    @ApiOperation(value = "根据分类id获取商品列表", notes = "默认按时间排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level", value = "分类层级(1, 2, 3)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "分类号", required = true, dataType = "int")}
    )
    @GetMapping("/list/{level}/{id}")
    public List<ProductVO> listByCategoryId(@PathVariable Integer level, @PathVariable Integer id) {
        return productService.findProductVOByCategoryId(level, id);
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

    @ApiOperation(value = "搜索商品获取所有未售出VO", notes = "默认按时间排序")
    @PostMapping("/search/{pageNum}/{pageSize}")
    public List<ProductVO> search(@RequestBody SearchFilter searchFilter, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return productService.searchAllProductVOPage(searchFilter, pageNum, pageSize);
    }

    @ApiOperation(value = "获取3个相关商品VO", notes = "默认按时间排序")
    @GetMapping("/findRelated/{productId}")
    public List<ProductVO> findRelated(@PathVariable Integer productId){
        Product current = productService.getById(productId);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("categorylevelone_id", current.getCategoryleveloneId());
        wrapper.eq("status", 0);
        wrapper.orderByDesc("update_time");
        wrapper.last("limit 3");

        List<Product> productList = productService.list(wrapper);
        List<ProductVO> productVOList = new ArrayList<>();
        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            User owner = userService.getById(product.getOwnerId());
            BeanUtils.copyProperties(product, productVO);
            BeanUtils.copyProperties(owner, productVO);
            productVOList.add(productVO);
        }
        if (productList.size() == 0){
           productVOList.add(new ProductVO());
           productVOList.add(new ProductVO());
           productVOList.add(new ProductVO());
        }else if (productList.size() == 1){
            productVOList.add(new ProductVO());
            productVOList.add(new ProductVO());
        }else if (productList.size() == 2){
            productVOList.add(new ProductVO());
        }

        return productVOList;
    }

    @ApiOperation(value = "添加商品")
    @PostMapping("/add")
    public boolean add(@RequestBody ProductDto productDto) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        productDto.setOwnerId(user.getUid());
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        boolean isFirst = true;
        for (String baseStr : productDto.getImages()) {
            String objectName = ImageUtil.generateObjectName(productDto.getId().toString(), 8);
            String url = ImageUtil.postImage(baseStr, objectName);
            if (isFirst) {
                product.setImage(url);
                productService.save(product);
                isFirst = false;
            }
            ProductImage productImage = new ProductImage();
            productImage.setImage(url);
            productImage.setProductId(product.getId());
            productImageService.save(productImage);
        }

        return true;
    }

    @ApiOperation(value = "修改商品信息")
    @PutMapping("/update/{id}")
    public boolean update(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @ApiOperation(value = "下架商品")
    @PutMapping("unShelfById/{id}")
    public boolean unShelfById(@PathVariable Integer id){
        Product product = productService.getById(id);
        product.setStatus(ProductStatus.OFF_SHELF);
        return productService.saveOrUpdate(product);
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return productService.removeById(id);
    }

}

