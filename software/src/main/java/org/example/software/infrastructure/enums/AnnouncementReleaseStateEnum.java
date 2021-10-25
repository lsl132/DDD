package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

/**
 * 公告状态:枚举
 * @author SHK
 */
@Getter
public enum AnnouncementReleaseStateEnum {

    UNRELEASED(0,"未发布"),
    RELEASED(1,"发布");

    private int state;
    private String stateDescribe;

    AnnouncementReleaseStateEnum(int state, String stateDescribe) {
        this.state = state;
        this.stateDescribe = stateDescribe;
    }

    public static  String getValue(int state) {
        for(AnnouncementReleaseStateEnum e : AnnouncementReleaseStateEnum.values()) {
            if(e.getState() == state) {
                return e.getStateDescribe();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "公告发布状态匹配失败");
    }

}
