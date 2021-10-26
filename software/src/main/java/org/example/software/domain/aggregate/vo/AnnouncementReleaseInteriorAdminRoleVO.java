package org.example.software.domain.aggregate.vo;

import lombok.Getter;
import org.example.software.domain.entity.AnnouncementReleaseInteriorAdminRoleEntity;
import org.springframework.beans.BeanUtils;
import org.wildfly.common.annotation.NotNull;

import java.util.Date;

/**
 *  公告发布内部管理员角色
 *
 * @author SHK
 */
@Getter
public class AnnouncementReleaseInteriorAdminRoleVO {

    /** 数据标识 */
    private Long id;
    /**  公告ID */
    private Long announcementId;
    /**  角色ID */
    private Integer roleId;
    /**  角色名称 */
    private String roleName;
    /**  数据创建时间 */
    private Date createTime;


    public AnnouncementReleaseInteriorAdminRoleEntity voToEntity() {
        AnnouncementReleaseInteriorAdminRoleEntity entity = new AnnouncementReleaseInteriorAdminRoleEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    public AnnouncementReleaseInteriorAdminRoleVO() {
        super();
    }

    private AnnouncementReleaseInteriorAdminRoleVO(Builder builder) {
        super();
        this.id = builder.id;
        this.announcementId = builder.announcementId;
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.createTime = builder.createTime;

    }


    public class Builder {

        private Long id;
        private Long announcementId;
        private Integer roleId;
        private String roleName;
        private Date createTime;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder announcementId(@NotNull Long announcementId) {
            this.announcementId = announcementId;
            return this;
        }

        public Builder roleId(@NotNull Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder roleName(@NotNull String roleName) {
            this.roleName = roleName;
            return this;
        }

        public Builder createTime(Date createTime) {
            if(createTime == null) {
                createTime = new Date();
            }
            this.createTime = createTime;
            return this;
        }

        public AnnouncementReleaseInteriorAdminRoleVO buil() {
            return new AnnouncementReleaseInteriorAdminRoleVO(this);
        }

    }


}
