package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

@Getter
public enum AuthorityPointTypeEnum {

    QUERY(1,"查看"),
    EDIT(2,"编辑"),
    REMOVE(3,"移除");

    private Integer key;
    private String value;

    AuthorityPointTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (AuthorityPointTypeEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "功能点类型不在界定范围内");
    }


}
