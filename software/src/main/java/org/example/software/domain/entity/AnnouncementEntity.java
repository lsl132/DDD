package org.example.software.domain.entity;

import lombok.Data;
import org.example.software.domain.aggregate.AnnouncementAggregate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * 公告数据表实体类
 * @author SHK
 */

@Data
@Entity(name = "tb_announcement")
public class AnnouncementEntity implements Serializable {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '公告ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '标题'")
    private String title;

    @Column(columnDefinition = "int(10)  COMMENT '公告类型'")
    private Integer type;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '标签'")
    private String label;

    @Column(columnDefinition = "TEXT  COMMENT '内容'")
    private String content;

    @Column(columnDefinition = "int(2)  COMMENT '状态 0未发布 1已发布 2撤回'")
    private Integer state;

    @Column(columnDefinition = "int(2)  COMMENT '是否置顶 0不置顶 1置顶'")
    private Integer isTop;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "int(2)  COMMENT '弹窗方式 1弹窗(详情) 2弹窗(简介) 3站内信 4无特殊提醒'")
    private Integer continueType;

    @Column(columnDefinition = "int(10)  COMMENT '弹窗持续时间'")
    private Integer continueSeconds;

    @Column(columnDefinition = "int(2)  COMMENT '内部范围 0未选择 1部分角色 2全部角色'")
    private Integer interiorScopeState;

    @Column(columnDefinition = "int(11)  COMMENT '有效期天数'")
    private Integer validNum;

    @Column(columnDefinition = "datetime  COMMENT '有效期开始时间'")
    private Date validStart;

    @Column(columnDefinition = "datetime  COMMENT '有效期结束时间'")
    private Date validEnd;

    @Column(columnDefinition = "datetime  COMMENT '发布时间'")
    private Date publisherTime;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '发布人'")
    private String publisherName;


    /**  租户范围 */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "announcementEntity", fetch = FetchType.LAZY)
    private Collection<AnnouncementTenantScopeEntity> tenantScopes;

    /**  租户下可见用户 */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "announcementEntity", fetch = FetchType.LAZY)
    private Collection<AnnouncementConsultUserRefEntity> ref;



    public AnnouncementAggregate entityToAggregate() {
        return null;
    }


}
