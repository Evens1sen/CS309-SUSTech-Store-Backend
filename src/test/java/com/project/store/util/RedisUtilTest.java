package com.project.store.util;

import com.project.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void test(){
        Product product = new Product();
        product.setId(114514);
        product.setName("qx");
        redisUtil.set(product.getId().toString(), product);
//        System.out.println(redisUtil.hasKey(product.getId().toString()));
        Product res = (Product) redisUtil.get(product.getId().toString());
        System.out.println(res.getName());
    }


}
