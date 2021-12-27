package com.project.store.util;

import com.project.store.entity.Product;
import com.project.store.vo.ProductVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RedisUtilTest {
    @Autowired
    RedisUtil redisUtil;

    @Test
    void test() {
        ProductVO p1 = new ProductVO();
        p1.setId(11123);
        p1.setName("qx");

        ProductVO p2 = new ProductVO();
        p2.setId(11126);
        p2.setName("sf");

        List<ProductVO> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

//        redisUtil.set("list",list);
//        System.out.println(redisUtil.hasKey("list"));
//        List<ProductVO> ls = (List<ProductVO>) redisUtil.get("list");
//        System.out.println(ls.get(0));

        redisUtil.set(p1.getId().toString(),p1);
        ProductVO p1_new = (ProductVO) redisUtil.get(p1.getId().toString());
        System.out.println(p1_new);
        redisUtil.set(p1.getId().toString(),p1);
        System.out.println(redisUtil.hasKey("11123"));
    }

}