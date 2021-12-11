package com.yzb.security.jwt.enums;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: JWTResponseCode
 * Description: JWT 所返回的状态码信息
 * date: 2021/12/10 22:53
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public enum JWTResponseCode {
    TOKEN_SUCCESS_CODE(HttpServletResponse.SC_OK, "token数据正确，可以正常访问"),
    Token_Expire(HttpServletResponse.SC_BAD_REQUEST, "token数据失效，请验证token"),
    TOKEN_NOT_FOUND_CODE(HttpServletResponse.SC_NOT_FOUND, "未找到相关token信息，请先获取token后进行访问");
    private int code;
    private String message;

    JWTResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "JWTResponseCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
