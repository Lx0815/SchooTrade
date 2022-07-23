package com.D.school_trade.web.response_obj;

/**
 * @author: Ding
 * @date: 2022/7/16 23:41
 * @description: 响应结果 的 实体类对象
 * @modify:
 */

public class ResponseResultData {

    private Object obj;

    private String token;

    public ResponseResultData() {
    }

    public ResponseResultData(Object obj, String token) {
        this.obj = obj;
        this.token = token;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                ", obj=" + obj +
                ", token='" + token + '\'' +
                '}';
    }
}
