package com.project.store.service.impl;

import com.project.store.entity.UserAddress;
import com.project.store.service.UserAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAddressServiceImplTest {

    @Autowired
    private UserAddressService userAddressService;

    @Test
    void test(){
        userAddressService.findAllUserAddressByUserId(10).forEach(System.out::println);
    }

}