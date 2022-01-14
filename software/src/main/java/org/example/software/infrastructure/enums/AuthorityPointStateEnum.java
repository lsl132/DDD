package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

@Getter
public enum AuthorityPointStateEnum {

    NORMAL(1,"正常"),
    UNDER(2,"下线"),
    DESTROY(9,"销毁");

    private Integer key;
    private String value;

    AuthorityPointStateEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (AuthorityPointStateEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "功能点状态不在界定范围内");
    }


}
