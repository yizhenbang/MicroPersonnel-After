package com.yzb.security.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yzb.security.jwt.StarterJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StarterJWT.class)
public class TestTokenService {

    @Autowired
    private TokenService tokenService;
    private String token = "eyJ0ZWFjaGVyIjoi54iG5Y-v54ix55qE5bCP5p2OIiwiYXBwbGljYXRpb24iOiJkZWZhdWx0IiwiYXV0aG9yIjoi54iG5Y-v54ix55qE5bCP5piTIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ7XCLmiJHmmK_mhqjmhqhcIjpcIuS9oOWlvVwifSIsInNpdGUiOiJjb20ueXpiLmp3dCIsImNyZWF0ZVRpbWUiOjE2MzkxOTUxODg2NTQsImlzcyI6IuWwj-aYkyIsImV4cCI6MTYzOTE5ODE4OCwiaWF0IjoxNjM5MTk1MTg4LCJqdGkiOiJjM2YyMjVjNy1iMzdlLTQyYWYtOTY4Mi1kZTUyMDk3YTdiOTAifQ.zRN57B8KDsJY_vSoHo-lafRMYuvw8YTl_BEmwtJaNaQ";

    @Test
    public void create() throws JsonProcessingException {
        Map<String, Object> subject = new HashMap<>();
        subject.put("我是憨憨", "你好");
        String token = tokenService.createToken(UUID.randomUUID().toString(), subject);
        System.out.println(token);
    }

    @Test
    public void parse() throws JsonProcessingException {
        Jws<Claims> claimsJws = tokenService.parseToken(token);
        System.out.println(claimsJws);
    }

    @Test
    public void refresh() throws JsonProcessingException {
        String haha = tokenService.refreshToken(this.token);
        System.out.println(haha);
    }
}
