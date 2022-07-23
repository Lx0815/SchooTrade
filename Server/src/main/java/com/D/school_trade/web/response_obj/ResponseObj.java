package com.D.school_trade.web.response_obj;

/**
 * @author: Ding
 * @date: 2022/7/17 0:03
 * @description:
 * @modify:
 */

public class ResponseObj {

    /**
     * 响应状态码
     */
    private ResponseCode code;

    /**
     * 响应数据
     */
    private ResponseResultData data;

    private String message;

    public ResponseObj() {
    }

    public ResponseObj(ResponseCode code, String message, ResponseResultData data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public ResponseResultData getData() {
        return data;
    }

    public void setData(ResponseResultData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
