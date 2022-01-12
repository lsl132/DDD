package org.example.common.exception;

import org.wildfly.common.annotation.NotNull;

public enum GlobalExceptionEnum {

    SYS_ERROR("999999","系统错误"),
    SQL_ERROR("900001","数据库异常"),

    BAD_REQUEST_ERROR("900001","请求参数解析失败"),
    METHOD_NOT_ALLOWED("900002","请求类型有误"),
    NOT_FOUND("900003","不存在的地址"),



    CHECK_NULL_ERROR("100001","数据为空"),
    CHECK_TYPE_ERROR("100002","数据类型有误"),
    CHECK_LENGTH_ERROR("100003","数据长度有误"),
    CHECK_BOUNDARY_ERROR("100004","数据不在界定内"),

    PROHIBITION_CHANGE_STATE("200005","当前状态不可变更数据")
    ;

    /** 错误代码 */
    private String code;
    /** 错误代码归类描述 */
    private String describe;


    GlobalExceptionEnum(String code,String describe) {
        this.code = code;
        this.describe = describe;
    }

    public static GlobalExceptionEnum getValue(@NotNull String code) {

        for (GlobalExceptionEnum e : values()) {
            if (e.getCode().equals(code) ) {
                return e;
            }
        }
        throw new IllegalArgumentException("获取异常说明失败");
    }

    public String getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
