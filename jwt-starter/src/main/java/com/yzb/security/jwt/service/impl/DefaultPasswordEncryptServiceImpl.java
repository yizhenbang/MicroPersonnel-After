package com.yzb.security.jwt.service.impl;

import com.yzb.security.jwt.config.PasswordEncryptConfigProperties;
import com.yzb.security.jwt.service.PasswordEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardTypeComparator;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * ClassName: PasswordEncryptServiceImpl
 * Description: 密码加密的具体实现子类
 * date: 2021/12/11 11:27
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DefaultPasswordEncryptServiceImpl implements PasswordEncryptService {

    @Autowired
    private PasswordEncryptConfigProperties passwordEncryptConfigProperties;//加密的相关属性
    private static MessageDigest MD5_MESSAGE_DIGEST;//MD5
    private Base64.Encoder ENCODER = Base64.getEncoder();//加密器

    static {
        try {
            MD5_MESSAGE_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getEncryptPassword(String pwd) {
        String saltPassword = "【" + this.passwordEncryptConfigProperties.getSalt() + "】" + pwd;
        for (int i = 0; i < this.passwordEncryptConfigProperties.getRepeat(); i++) {
            saltPassword = ENCODER.encodeToString(MD5_MESSAGE_DIGEST.digest(saltPassword.getBytes()));
        }
        return saltPassword;
    }
}
