package com.project.store;

import com.project.store.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作key-value都是字符串，最常用

    @Test
    public void redisTest() {
        //字符串操作
        stringRedisTemplate.opsForValue().append("msg", "coder");

        //列表操作
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

}
