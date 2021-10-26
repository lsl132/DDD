package org.example.software.domain.aggregate.vo;

import lombok.Getter;
import org.example.software.domain.entity.AnnouncementReleaseInteriorAdminRoleEntity;
import org.example.software.domain.entity.AnnouncementReleaseTenantEntity;
import org.springframework.beans.BeanUtils;
import org.wildfly.common.annotation.NotNull;

import java.util.Date;

/**
 * @author SHK
 */
@Getter
public class AnnouncementReleaseTenantVO {

    /** 数据标识 */
    private Long id;
    /**  公告ID */
    private Long announcementId;
    /**  租户ID */
    private Integer tenantId;
    /**  租户名称 */
    private String tenantName;
    /**  数据创建时间 */
    private Date createTime;


    public AnnouncementReleaseTenantEntity voToEntity() {
        AnnouncementReleaseTenantEntity entity = new AnnouncementReleaseTenantEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    public  AnnouncementReleaseTenantVO() {
        super();
    }

    private AnnouncementReleaseTenantVO(Builder builder) {
        super();
        this.id = builder.id;
        this.announcementId = builder.announcementId;
        this.tenantId = builder.tenantId;
        this.tenantName = builder.tenantName;
        this.createTime = builder.createTime;

    }


    public class Builder {

        private Long id;
        private Long announcementId;
        private Integer tenantId;
        private String tenantName;
        private Date createTime;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder announcementId(@NotNull Long announcementId) {
            this.announcementId = announcementId;
            return this;
        }

        public Builder tenantId(@NotNull Integer tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder tenantName(@NotNull String tenantName) {
            this.tenantName = tenantName;
            return this;
        }

        public Builder createTime(Date createTime) {
            if(createTime == null) {
                createTime = new Date();
            }
            this.createTime = createTime;
            return this;
        }

        public AnnouncementReleaseTenantVO buil() {
            return new AnnouncementReleaseTenantVO(this);
        }

    }


}
