package com.project.store.mapper;

import com.project.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test(){
        User user = userMapper.selectById(11911001);
        user.setNickName("qx");
        userMapper.updateById(user);
    }

}