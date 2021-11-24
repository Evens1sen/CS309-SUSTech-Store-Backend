package com.project.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.GeneralSecurityException;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService UserService;

    @Test
    void test() throws GeneralSecurityException {
        UserService.sendNotification("11912725@mail.sustech.edu.cn");
        UserService.sendVerification("11912725@mail.sustech.edu.cn");
    }

}