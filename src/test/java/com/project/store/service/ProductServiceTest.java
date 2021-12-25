package com.project.store.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.store.dto.SearchFilter;
import com.project.store.entity.Product;
import com.project.store.mapper.ProductMapper;
import com.project.store.vo.ProductVO;
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

    @Autowired
    ProductMapper productMapper;

    @Test
    void test() {
//        SearchFilter searchFilter = new SearchFilter();
//        searchFilter.setKey("椰羊");
//        searchFilter.setMaxPrice(400.0);
//        searchFilter.setCreditLevel(0.0);
//        searchFilter.setMinPrice(0.0);
//        List<ProductVO> list = productService.searchAllProductVO(searchFilter);
//        System.out.println(list.size());
    }
}