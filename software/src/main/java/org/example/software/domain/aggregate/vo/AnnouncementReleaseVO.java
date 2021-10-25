package org.example.software.domain.aggregate.vo;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.infrastructure.constant.Constant;
import org.example.software.infrastructure.enums.AnnouncementReleaseInteriorScopeStateEnum;
import org.example.software.infrastructure.enums.AnnouncementReleaseRemindTypeEnum;
import org.example.software.infrastructure.enums.AnnouncementReleaseStateEnum;
import org.example.software.infrastructure.enums.AnnouncementReleaseTenantScopeStateEnum;
import org.wildfly.common.annotation.NotNull;

import javax.persistence.Column;
import java.util.Collection;
import java.util.Date;

/**
 * 公告-发布值对象
 * @author SHK
 */
@Getter
public class AnnouncementReleaseVO {


    /** 发布状态  */
    private Integer state;
    /**  是否置顶 */
    private Integer isTop;
    /**  提醒类型 */
    private Integer remindType ;
    /**  提醒弹窗时间 (秒) */
    private Integer remindContinueSeconds;
    /**  有效天数 0:永久有效   */
    private Integer remindValidDayNum;

    /** 发布时间 */
    private Date releaseTime;

    /** 有效期开始时间 */
    private Date validStart;
    /** 有效期结束时间 */
    private Date validEnd;

    /** 内部范围 */
    private Integer interiorScopeState ;
    private Collection<AnnouncementReleaseInteriorAdminRoleVO> adminRoleVOs;

    /** 租户范围 */
    private Integer tenantScopeState;
    private Collection<AnnouncementReleaseTenantVO> tenantVOs;
    private Integer tenantSuperAdminState;


    /**
     * 取消发布
     */
    public void cancelRelease() {
        this.state = AnnouncementReleaseStateEnum.UNRELEASED.getState();
        this.isTop = 0;
        this.releaseTime = null;
    }

    /** 置顶 */
    public void top() {
        if(this.state == AnnouncementReleaseStateEnum.UNRELEASED.getState()) {
            throw new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(), "非发布状态不可置顶");
        }
        this.isTop = 1;
    }

    public void cancelTop() {
        this.isTop = 0;
    }

    /** 替换提醒信息 */
    public void replaceRemind(@NotNull Integer remindType,@NotNull Integer remindContinueSeconds,@NotNull Integer remindValidDayNum) {
        System.out.println(AnnouncementReleaseStateEnum.getValue(state));

        if(remindContinueSeconds < Constant.REMIND_SECONDS_MIN_NUM) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒时间设置过小低于【"+Constant.REMIND_SECONDS_MIN_NUM+"】");
        }
        if(remindContinueSeconds < Constant.REMIND_SECONDS_MIN_NUM) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒时间设置超过最大【"+Constant.REMIND_SECONDS_MAX_NUM+"】");
        }

        if(remindValidDayNum < Constant.REMIND_VAILD_DAY_MIN_NUM) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒天数设置过小小于【"+Constant.REMIND_VAILD_DAY_MIN_NUM+"】");
        }
        if(remindValidDayNum < Constant.REMIND_VAILD_DAY_MAX_NUM) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒天数设置过大超过【"+Constant.REMIND_VAILD_DAY_MAX_NUM+"】");
        }

        this.remindType = remindType;
        this.remindContinueSeconds = remindContinueSeconds;
        this.remindValidDayNum = remindValidDayNum;

    }


    /**  替换内部范围信息 */
    public void replaceInteriorScope(Integer interiorScopeState, Collection<AnnouncementReleaseInteriorAdminRoleVO> adminRoleVOs) {

    }

    /**  替换租户范围信息 */
    public void replaceTenantScope(Integer tenantScopeState, Collection<AnnouncementReleaseTenantVO> tenantVOs, Integer tenantSuperAdminState) {

    }


    private AnnouncementReleaseVO(Builder builder) {
        this.state = builder.state;
        this.isTop = builder.isTop;
        this.remindType = builder.remindType;
        this.remindContinueSeconds = builder.remindContinueSeconds;
        this.remindValidDayNum = builder.remindValidDayNum;
        this.releaseTime = builder.releaseTime;
        this.validStart = builder.validStart;
        this.validEnd = builder.validEnd;
        this.interiorScopeState = builder.interiorScopeState;
        this.adminRoleVOs = builder.adminRoleVOs;
        this.tenantScopeState = builder.tenantScopeState;
        this.tenantVOs = builder.tenantVOs;
        this.tenantSuperAdminState = builder.tenantSuperAdminState;
    }


    public class Builder {
        private Integer state;
        private Integer isTop;
        private Integer remindType ;
        private Integer remindContinueSeconds;
        private Integer remindValidDayNum;

        private Date releaseTime;

        private Date validStart;
        private Date validEnd;

        private Integer interiorScopeState ;
        private Collection<AnnouncementReleaseInteriorAdminRoleVO> adminRoleVOs;

        private Integer tenantScopeState;
        private Collection<AnnouncementReleaseTenantVO> tenantVOs;
        private Integer tenantSuperAdminState;

        public Builder state(@NotNull Integer state) {
            System.out.println(AnnouncementReleaseStateEnum.getValue(state));
            this.state = state;
            return this;
        }

        public Builder isTop(@NotNull Integer isTop) {
            this.state = state;
            return this;
        }

        public Builder remindType(@NotNull Integer remindType) {
            System.out.println(AnnouncementReleaseRemindTypeEnum.getValue(remindType));
            this.remindType = remindType;
            return this;
        }

        public Builder remindContinueSeconds(@NotNull Integer remindContinueSeconds) {
            if(remindContinueSeconds < Constant.REMIND_SECONDS_MIN_NUM) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒时间设置过小低于【"+Constant.REMIND_SECONDS_MIN_NUM+"】");
            }
            if(remindContinueSeconds < Constant.REMIND_SECONDS_MIN_NUM) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒时间设置超过最大【"+Constant.REMIND_SECONDS_MAX_NUM+"】");
            }
            this.remindContinueSeconds = remindContinueSeconds;
            return this;
        }

        public Builder remindValidDayNum(@NotNull Integer remindValidDayNum) {
            if(remindValidDayNum < Constant.REMIND_VAILD_DAY_MIN_NUM) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒天数设置过小小于【"+Constant.REMIND_VAILD_DAY_MIN_NUM+"】");
            }
            if(remindValidDayNum < Constant.REMIND_VAILD_DAY_MAX_NUM) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "提醒天数设置过大超过【"+Constant.REMIND_VAILD_DAY_MAX_NUM+"】");
            }
            this.remindType = remindType;
            return this;
        }

        public Builder releaseTime(Date releaseTime) {
            this.releaseTime = releaseTime;
            return this;
        }

        public Builder validStart(Date validStart) {
            this.validStart = validStart;
            return this;
        }

        public Builder validEnd(Date validEnd) {
            this.validEnd = validEnd;
            return this;
        }

        public Builder interiorScopeState(@NotNull Integer interiorScopeState) {
            System.out.println(AnnouncementReleaseInteriorScopeStateEnum.getValue(interiorScopeState));
            this.interiorScopeState = interiorScopeState;
            return this;
        }

        public Builder adminRoleVOs(Collection<AnnouncementReleaseInteriorAdminRoleVO> adminRoleVOs) {
            this.adminRoleVOs = adminRoleVOs;
            return this;
        }

        public Builder tenantScopeState(@NotNull Integer tenantScopeState) {
            this.tenantScopeState = tenantScopeState;
            return this;
        }

        public Builder tenantVOs(Collection<AnnouncementReleaseTenantVO> tenantVOs) {
            this.tenantVOs = tenantVOs;
            return this;
        }

        public Builder tenantSuperAdminState(@NotNull Integer tenantSuperAdminState) {
            this.tenantSuperAdminState = tenantSuperAdminState;
            return this;
        }

        public AnnouncementReleaseVO buil() {
            return new AnnouncementReleaseVO(this);
        }

    }


}
