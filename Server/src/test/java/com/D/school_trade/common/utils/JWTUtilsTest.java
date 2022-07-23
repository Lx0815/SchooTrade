package com.D.school_trade.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

/**
 * @author: Ding
 * @date: 2022/7/23 16:52
 * @description:
 * @modify:
 */


public class JWTUtilsTest {


    @Test
    public void test01() {
        String sign = JWTUtils.sign(1, "2", "3");
        DecodedJWT decode = JWT.decode(sign);
        System.out.println(decode.getClaim("id").asInt());
        System.out.println(decode.getClaim("schoolCardId").asString());
        System.out.println(decode.getClaim("password").asString());
    }

}
