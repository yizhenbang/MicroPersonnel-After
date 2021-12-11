package com.yzb.security.jwt.service;

import com.yzb.security.jwt.StarterJWT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StarterJWT.class)
public class TestPasswordEncrypt {
    @Autowired
    private PasswordEncryptService passwordEncryptService;

    @Test
    public void getPwdEncrypt() {
        String hello = passwordEncryptService.getEncryptPassword("hello");
        System.out.println(hello);
    }
}
