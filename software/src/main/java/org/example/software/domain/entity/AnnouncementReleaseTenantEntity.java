package org.example.software.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 租户范围
 *  去掉内部范围，云管其实也是个租户，租户ID是1
 *
 *
 * @author SHK
 */

@Data
@Entity(name = "tb_announcement_release_tenant")
public class AnnouncementReleaseTenantEntity implements Serializable {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "announcement_id", columnDefinition = "BIGINT(20) NOT NULL COMMENT '所属公告ID'")
    private AnnouncementEntity announcementEntity;

    @Column(columnDefinition = "BIGINT(20)  COMMENT '租户ID'")
    private Integer tenantId;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '租户名称（快照形式）'")
    private String tenantName;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

}
