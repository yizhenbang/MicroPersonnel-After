package com.yzb.security.jwt.service;

/**
 * ClassName: PasswordEncryptService
 * Description: 既然所有的数据要保存到数据表那么就需要有一个密码加密器
 * date: 2021/12/11 11:23
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public interface PasswordEncryptService {
    /*
     * @Author ZhenBang-Yi
     * @Date 2021/12/11 11:25
     * @Description //TODO 对密码进行加密处理
     * @param pwd:  加密之前的密码
     * @return java.lang.String 加密后的密码
     * @Since version-1.0
    **/
    String getEncryptPassword(String pwd);
}
