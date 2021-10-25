package org.example.software.infrastructure.enums;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;

/**
 * 公告租户范围:枚举
 * @author SHK
 */
@Getter
public enum AnnouncementReleaseTenantScopeStateEnum {

    NOT_INCLUDED(0,"不包含"),
    INCLUDED(1,"包含");

    private int state;
    private String stateDescribe;

    AnnouncementReleaseTenantScopeStateEnum(int state, String stateDescribe) {
        this.state = state;
        this.stateDescribe = stateDescribe;
    }

    public static  String getValue(int state) {
        for(AnnouncementReleaseTenantScopeStateEnum e : AnnouncementReleaseTenantScopeStateEnum.values()) {
            if(e.getState() == state) {
                return e.getStateDescribe();
            }
        }
        throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "公告发布租户范围状态匹配失败");
    }
}
