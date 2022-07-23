package com.D.school_trade.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import logger.LoggerUtils;

import java.util.Date;

/**
 * @author: Ding
 * @date: 2022/7/16
 * @description: JWT 工具类
 * @modify:
 */

public class JWTUtils {

    private JWTUtils() {}

    /**
     * token 过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 密钥
     */
    private static final String TOKEN_SECRET = "java";


    /**
     * 验证 Token，在每次访问接口的时候使用，一般在拦截器中使用（拦截器后面有）
     * 此方法不跑出异常
     * @param token
     */
    public static void verify(String token) {
        try {
            verifyAndThrowException(token);
        } catch (AlgorithmMismatchException e) {
            LoggerUtils.logWarn("令牌标头中声明的算法不等于JWTVerifier中定义的算法。");
        } catch (SignatureVerificationException e) {
            LoggerUtils.logWarn("签名无效");
        } catch (TokenExpiredException e) {
            LoggerUtils.logWarn("Token 已过期");
        } catch (InvalidClaimException e) {
            LoggerUtils.logWarn("声明包含与预期不同的值");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 验证 Token，在每次访问接口的时候使用，一般在拦截器中使用（拦截器后面有）
     * 此方抛出异常，以便程序进行相应的处理，而不只是打印日志
     * @param token
     */
    public static void verifyAndThrowException(String token) throws AlgorithmMismatchException, SignatureVerificationException, TokenExpiredException, InvalidClaimException {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256((TOKEN_SECRET)))
                .withIssuer("auth0").build();
        /*
            AlgorithmMismatchException – 如果令牌标头中声明的算法不等于JWTVerifier中定义的算法。
            SignatureVerificationException – 如果签名无效。
            TokenExpiredException – 如果令牌已过期。
            InvalidClaimException – 如果声明包含与预期不同的值
         */
        DecodedJWT jwt = verifier.verify(token);
        LoggerUtils.logInfo(jwt.getClaim("schoolCardId").asString() + " 的 token 认证通过");
        LoggerUtils.logInfo("过期时间：" + jwt.getExpiresAt());
    }

    /**
     * 通过 Token 获取用户名
     * @param token
     * @return
     */
    public static String getSchoolCardId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("schoolCardId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 通过 Token 获取密码
     * @param token
     * @return
     */
    public static String getPassword(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("password").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 通过 Token 获取 id
     * @param token
     * @return
     */
    public static Integer getId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,30min 后过期
     *
     * @param id id
     * @param schoolCardId 校园卡 id
     * @param password 密码
     * @return 加密的token
     */
    public static String sign(Integer id, String schoolCardId, String password) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id",id)
                    .withClaim("schoolCardId",schoolCardId)
                    .withClaim("password",password)
                    .withExpiresAt(expiresAt)
                    //使用HMAC256算法加密
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
}
