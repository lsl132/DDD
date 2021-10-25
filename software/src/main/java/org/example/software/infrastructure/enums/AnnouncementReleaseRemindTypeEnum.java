package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

/**
 * 公告提醒类型
 * @author SHK
 */
@Getter
public enum AnnouncementReleaseRemindTypeEnum {

    NOT_REMIND(0,"不提醒"),
    POPUP_DETAILS(1,"弹窗-详情"),
    POPUP_INTRODUCTION(2,"弹窗-简介"),
    INSIDE_MAIL(3,"站内信");

    private int type;
    private String stateDescribe;

    AnnouncementReleaseRemindTypeEnum(int type, String stateDescribe) {
        this.type = type;
        this.stateDescribe = stateDescribe;
    }

    public static  String getValue(int type) {
        for(AnnouncementReleaseRemindTypeEnum e : AnnouncementReleaseRemindTypeEnum.values()) {
            if(e.getType() == type) {
                return e.getStateDescribe();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "公告发布提醒类型匹配失败");
    }
}
