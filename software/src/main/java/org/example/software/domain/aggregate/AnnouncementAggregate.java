package org.example.software.domain.aggregate;


import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.domain.aggregate.vo.AnnouncementInfoVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseInteriorAdminRoleVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseTenantVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseVO;
import org.example.software.domain.entity.AnnouncementEntity;
import org.wildfly.common.annotation.NotNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 * 公告聚合根
 * @author SHK
 */
@Getter
public class AnnouncementAggregate implements Serializable {


    private Long id;

    private Date createTime;

    private AnnouncementInfoVO announcementInfo;

    private AnnouncementReleaseVO announcementRelease;


    public AnnouncementEntity aggregateToEntity() {
        AnnouncementEntity entity = new AnnouncementEntity();
        entity.setId(this.id);
        entity.setCreateTime(this.createTime);

        entity.setTitle(this.announcementInfo.getTitle());
        entity.setIntro(this.announcementInfo.getIntro());
        entity.setType(this.announcementInfo.getType());
        entity.setLabelNames(this.announcementInfo.getLabelNames());
        entity.setContent(this.announcementInfo.getContent());
        entity.setAuthor(this.announcementInfo.getAuthor());

        if(announcementRelease != null) {
            entity.setState(this.announcementRelease.getState());
            entity.setIsTop(this.announcementRelease.getIsTop());
            entity.setRemindType(this.announcementRelease.getRemindType());
            entity.setRemindContinueSeconds(this.announcementRelease.getRemindContinueSeconds());
            entity.setRemindValidDayNum(this.announcementRelease.getRemindValidDayNum());

            entity.setReleaseTime(this.announcementRelease.getReleaseTime());

            entity.setValidStart(this.announcementRelease.getValidStart());
            entity.setValidEnd(this.announcementRelease.getValidEnd());
            entity.setInteriorScopeState(this.announcementRelease.getInteriorScopeState());
            entity.setInteriorAdminRoleEntities(this.announcementRelease.interiorAdminRoleVOsTransform(entity));

            entity.setTenantScopeState(this.announcementRelease.getTenantScopeState());
            entity.setReleaseTenantEntities(this.announcementRelease.tenantVOsTransform(entity));
            entity.setTenantSuperAdminState(this.announcementRelease.getTenantSuperAdminState());

        }

        return entity;
    }

    private AnnouncementAggregate(Builder builder) {
        this.id = builder.id;
        this.createTime = builder.createTime;
        this.announcementInfo = builder.announcementInfo;
        this.announcementRelease = builder.announcementRelease;
    }

    public static class Builder {
        private Long id;
        private Date createTime;
        private AnnouncementInfoVO announcementInfo;
        private AnnouncementReleaseVO announcementRelease;

        public Builder id(@NotNull Long id) {

            if(id <= 0L) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_BOUNDARY_ERROR.getCode(), "公告ID有误");
            }
            this.id = id;
            return this;
        }

        public Builder createTime(Date createTime) {
            if(createTime == null) {
                createTime = new Date();
            }
            this.createTime = createTime;
            return this;
        }

        public Builder announcementInfo(AnnouncementInfoVO announcementInfo) {
            if(announcementInfo == null) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "公告信息为空");
            }
            this.announcementInfo = announcementInfo;
            return this;
        }

        public Builder announcementRelease(AnnouncementReleaseVO announcementRelease) {
            if(announcementRelease == null) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "公告发布内容为空");
            }
            this.announcementRelease = announcementRelease;
            return this;
        }


        public AnnouncementAggregate build(){
            return new AnnouncementAggregate(this);
        }
    }


}
