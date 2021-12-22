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
        String str = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UH9rFFGGuJEy";
        System.out.println(str.substring(23));
    }
}