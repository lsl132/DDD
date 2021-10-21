package org.example.common.exception;

import lombok.Getter;

/**
 * 自定义异常类
 * @author 李双隆
 */
@Getter
public class GlobalException extends RuntimeException {

    /** 自定义异常枚举 */
    private String code;
    /** 异常详情（告知给前端的） */
    private String details;

    public GlobalException() {
        super();
    }

    public  GlobalException(String code) {
        super(code);
        this.code = code;
        this.details = GlobalExceptionEnum.getValue(code).getDescribe();
    }

    public  GlobalException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.details = GlobalExceptionEnum.getValue(code).getDescribe();
    }

    public  GlobalException(String code, String details, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.details = details;
    }
}
