package org.example.software.infrastructure.enums;

import lombok.Getter;

@Getter
public enum UserStateEnum {


    DORMANCY(0,"休眠"),
    NORMAL(1,"正常"),
    FREEZE(2,"冻结"),
    DESTROY(9,"销毁");

    private Integer key;
    private String value;

    UserStateEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (UserStateEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        return null;
    }

}
