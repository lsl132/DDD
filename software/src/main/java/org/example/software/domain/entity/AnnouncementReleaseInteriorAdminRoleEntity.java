package org.example.software.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "tb_announcement_release_interior_admin_role")
public class AnnouncementReleaseInteriorAdminRoleEntity {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( columnDefinition= "BIGINT(20) NOT NULL COMMENT '所属公告ID'")
    private Long announcementId;

    @Column(columnDefinition = "int(11)  COMMENT '角色ID'")
    private Integer roleId;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '角色名称（快照）'")
    private String roleName;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

//    public AnnouncementAuthorAggregate entityToAggregate() {




}
