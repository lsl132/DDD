package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

@Getter
public enum AuthorityPointResourceTypeEnum {

    FUNCTION(1,"功能"),
    DATA(2,"数据");

    private Integer key;
    private String value;

    AuthorityPointResourceTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (AuthorityPointResourceTypeEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "功能点资源类型不在界定范围内");
    }


}
