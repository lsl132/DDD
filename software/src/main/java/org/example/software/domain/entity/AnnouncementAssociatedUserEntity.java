package org.example.software.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 公告关联的用户 -- 这个属于公告的弱关联
 *
 * @author SHK
 */

@Data
@Entity(name = "tb_announcement_associated_user")
public class AnnouncementAssociatedUserEntity implements Serializable {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '所属公告ID'")
    private Long announcementId;

    @Column(columnDefinition = "INT(11)  COMMENT '租户ID'")
    private Integer tenantId;

    @Column(columnDefinition = "BIGINT(20) COMMENT '用户id'")
    private Long userId;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户名称（快照）'")
    private String userName;

    @Column(columnDefinition = "int(11) COMMENT '用户类型 1系统管理员 2租户管理员'")
    private Integer userType;

    @Column(columnDefinition = "int(11) COMMENT '回执状态 0未查看 1已查看'")
    private Integer receiptState;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '回执状态描述'")
    private String receiptDescribe;

    @Column(columnDefinition = "datetime COMMENT '查看时间'")
    private Date checkTime;


}
