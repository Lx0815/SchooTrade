package com.D.school_trade.web.interceptor;

import com.D.school_trade.common.utils.JWTUtils;
import com.D.school_trade.web.response_obj.ResponseCode;
import com.D.school_trade.web.response_obj.ResponseObj;
import com.D.school_trade.web.response_obj.ResponseResultData;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import logger.LoggerUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author: Ding
 * @date: 2022/7/16 23:12
 * @description: Token 拦截器
 * @modify:
 */

public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 拦截器和过滤器的区别
     * 1. 拦截器针对访问控制器进行拦截及 @RequestMapping(value = {"/test"})
     *          简而言说就是访问方法的url
     *    应用：可以作为权限的判断，
     * 2. 过滤器则是针对全局的请求
     *    包括：css/js/html/jpg/png/git/... 及静态文件
     *
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果是预检请求则直接放行
        if ("OPTIONS".equals(request.getMethod())) {
//            // 必填
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//            response.setHeader("Access-Control-Request-Method", request.getHeader("Access-Control-Request-Method"));
//            response.setHeader("Access-Control-Request-Headers", request.getHeader("Access-Control-Request-Headers"));
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setHeader("Content-Type", "application/json");
//            LoggerUtils.logInfo(request.getHeader("Content-Type"));
////                response.setHeader("Access-Control-Max-Age", );
//            response.setStatus(200);
            return true;
        }
        // 判断正常请求
        String url = request.getRequestURI();
        if(!url.endsWith("user/login")){

            // 尝试从http请求头中取出token
            String token = request.getHeader("X-Token");
            LoggerUtils.logInfo("从 http 请求头中取出token : " + token);
//            if (token == null) {
//                // 尝试从url中取出token
//                token = request.getParameter("token");
//                LoggerUtils.logInfo("从 url 参数中取出token : " + token);
//            }
            if (token == null) {
                LoggerUtils.logWarn("请求未携带 token");
                sendErrorResponse("未携带 token", response);
                return false;
            }

            //执行认证
            try {
                JWTUtils.verifyAndThrowException(token);
                return true;

            } catch (AlgorithmMismatchException e) {
                LoggerUtils.logWarn("令牌标头中声明的算法不等于JWTVerifier中定义的算法。");

                sendErrorResponse(ResponseCode.FAIL, "声明包含与预期不同的值", response);
                return false;

            } catch (SignatureVerificationException e) {
                LoggerUtils.logWarn("签名无效");

                sendErrorResponse(ResponseCode.FAIL, "声明包含与预期不同的值", response);
                return false;

            } catch (TokenExpiredException e) {
                LoggerUtils.logWarn("Token 已过期");

                sendErrorResponse(ResponseCode.FAIL, "声明包含与预期不同的值", response);
                return false;

            } catch (InvalidClaimException e) {
                LoggerUtils.logWarn("声明包含与预期不同的值");

                sendErrorResponse(ResponseCode.FAIL, "声明包含与预期不同的值", response);
                return false;

            } catch (Exception e) {
                sendErrorResponse(ResponseCode.FAIL, "服务器异常", response);
                throw new RuntimeException(e);

            }
        }
        return true;
    }

    private void sendErrorResponse(String message, HttpServletResponse response) {
        sendErrorResponse(ResponseCode.FAIL, message, response);
    }

    private void sendErrorResponse(ResponseCode code, String message, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            PrintWriter responseWriter = response.getWriter();
            ResponseObj responseObj = new ResponseObj(code, message, new ResponseResultData(null, null));

            ObjectMapper om = new ObjectMapper();
            String jsonStr = om.writeValueAsString(responseObj);
            responseWriter.write(jsonStr);
            // 在PrintWriter上调用 flush() 会提交响应。
            responseWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
