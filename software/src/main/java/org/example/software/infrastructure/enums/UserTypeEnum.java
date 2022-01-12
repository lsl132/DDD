package org.example.software.infrastructure.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    FORMAL(1,"正式"),
    TRILATERAL(2,"三方映射");

    private Integer key;
    private String value;

    UserTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (UserTypeEnum e : values()) {
            if(e.getKey().equals(key)) {
                return e.getValue();
            }
        }
        return null;
    }


}
