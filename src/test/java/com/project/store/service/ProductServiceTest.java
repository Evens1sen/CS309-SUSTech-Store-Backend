package com.project.store.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void test() {
        productService.findProductVOPage(3, 2);
    }
}