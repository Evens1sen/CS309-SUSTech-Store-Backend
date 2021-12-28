package com.project.store.service;

import com.project.store.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @Test

    void test1() {
        long start1 = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            productService.findProductVOById(random.nextInt(32) + 733);
        }
        long end1 =System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            productService.findProductVOById(random.nextInt(10) + 765);
        }
        long end2 =System.currentTimeMillis();
        System.out.println("Redis time:");
        System.err.println(end1-start1);
        System.out.println("MySQL time:");
        System.err.println(end2-start2);
    }
    
    @Test
    void test2(){
        long start1 = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            productService.findProductVOById(random.nextInt(32) + 733);
        }
        long end1 =System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            productService.findProductVOById(random.nextInt(10) + 765);
        }
        long end2 =System.currentTimeMillis();
        System.out.println("Redis time:");
        System.err.println(end1-start1);
        System.out.println("MySQL time:");
        System.err.println(end2-start2);
    }

    @Test
    void test3(){
        long start1 = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            productService.findProductVOById(random.nextInt(32) + 733);
        }
        long end1 =System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            productService.findProductVOById(random.nextInt(10) + 765);
        }
        long end2 =System.currentTimeMillis();
        System.out.println("Redis time:");
        System.err.println(end1-start1);
        System.out.println("MySQL time:");
        System.err.println(end2-start2);
    }
}
