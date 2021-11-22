package com.project.store.service.impl;

import com.project.store.entity.User;
import com.project.store.mapper.UserMapper;
import com.project.store.service.UserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAddressServiceImplTest {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserMapper userMapper;

    private

    @Test
    void test(){
        User user = userMapper.selectById(11911001);
        System.out.println(user);
    }

}