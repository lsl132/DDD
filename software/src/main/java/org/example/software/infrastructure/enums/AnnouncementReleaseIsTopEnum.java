package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

/**
 * 公告置顶:枚举
 * @author SHK
 */
@Getter
public enum AnnouncementReleaseIsTopEnum {

    NOT_TOP(0,"不置顶"),
    TOP(1,"置顶");

    private int state;
    private String stateDescribe;

    AnnouncementReleaseIsTopEnum(int state, String stateDescribe) {
        this.state = state;
        this.stateDescribe = stateDescribe;
    }

    public static  String getValue(int state) {
        for(AnnouncementReleaseIsTopEnum e : AnnouncementReleaseIsTopEnum.values()) {
            if(e.getState() == state) {
                return e.getStateDescribe();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "公告置顶状态匹配失败");
    }
}
