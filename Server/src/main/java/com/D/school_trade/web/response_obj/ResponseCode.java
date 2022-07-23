package com.D.school_trade.web.response_obj;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCode {

    SUCCESS(20000),

    FAIL(20001);

    private Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }

    //
    @JsonValue
    public Integer getCode() {
        return code;
    }
}
