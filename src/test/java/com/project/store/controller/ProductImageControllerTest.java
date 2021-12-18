package com.project.store.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductImageControllerTest {

    @Autowired
    private ProductImageController productImageController;

    @Test
    void test(){
        productImageController.postImage();
    }
}