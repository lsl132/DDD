package org.example.common.response;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回模板类
 * @author SHK
 */
@Getter
@ToString
@Builder
public class ResponseResult<T> implements Serializable {

    /** 成功 */
    public static final String SUCCESS_CODE = "000000";
    public static final String SUCCESS_MSG = "成功";

    /** 返回码 */
    private String code;
    /** 返回详情说明 */
    private String msg;
    /** 返回具体值 */
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
