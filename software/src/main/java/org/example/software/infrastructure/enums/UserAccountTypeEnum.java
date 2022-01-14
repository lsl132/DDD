package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

@Getter
public enum UserAccountTypeEnum {

    ACCOUNT(1,"帐号"),
    MOBILE(2,"手机号码"),
    EMAIL(3,"电子邮箱");

    private Integer key;
    private String value;

    UserAccountTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (UserAccountTypeEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "用户帐号类型不在界定范围内");
    }


}
