package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

@Getter
public enum UserLevelEnum {

    SUPER_ADMIN(1,"超级管理员"),
    ADMIN(2,"管理员"),
    HELPER(3,"协助者");

    private Integer key;
    private String value;

    UserLevelEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (UserLevelEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "用户级别不在界定范围内");
    }


}
