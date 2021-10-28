package org.example.software.domain.entity;

import lombok.Data;
import org.example.software.domain.aggregate.AnnouncementAggregate;
import org.example.software.domain.aggregate.vo.AnnouncementInfoVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseInteriorAdminRoleVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseTenantVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseVO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(columnDefinition = "VARCHAR(1024)  COMMENT '简介'")
    private String intro;

    @Column(columnDefinition = "TEXT  COMMENT '内容'")
    private String content;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "INT(10)  COMMENT '公告类型'")
    private Integer type;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '标签(多个用逗号分隔)'")
    private String labelNames;

    @Column(columnDefinition = "INT(10)  COMMENT '作者（发布人）'")
    private Integer author;

    @Column(columnDefinition = "int(2)  COMMENT '是否置顶 0不置顶 1置顶'")
    private int isTop;

    @Column(columnDefinition = "int(2)  COMMENT '提醒类型 0不提醒 1弹窗(详情) 2弹窗(简介) 3站内信'")
    private int remindType;

    @Column(columnDefinition = "int(5)  COMMENT '提醒持续时间(秒)'")
    private int remindContinueSeconds ;

    @Column(columnDefinition = "int(5)  COMMENT '提醒有效天数'")
    private int remindValidDayNum ;

    @Column(columnDefinition = "int(2)  COMMENT '状态 0未发布 1已发布'")
    private int state;

    @Column(columnDefinition = "datetime  COMMENT '有效期开始时间'")
    private Date validStart;

    @Column(columnDefinition = "datetime  COMMENT '有效期结束时间'")
    private Date validEnd;

    @Column(columnDefinition = "datetime  COMMENT '发布时间'")
    private Date releaseTime;

    /**  租户范围 */
    @Column(columnDefinition = "int(2)  COMMENT '内部范围 0不包含 1包含'")
    private int interiorScopeState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "announcementEntity", fetch = FetchType.LAZY)
    private Collection<AnnouncementReleaseInteriorAdminRoleEntity> interiorAdminRoleEntities;

    /**  租户下可见用户 */
    @Column(columnDefinition = "int(2)  COMMENT '租户范围 0不包含 1包含'")
    private int tenantScopeState;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "announcementEntity", fetch = FetchType.LAZY)
    private Collection<AnnouncementReleaseTenantEntity> releaseTenantEntities;

    @Column(columnDefinition = "int(2)  COMMENT '只发给租管超管 0全部管理员 1只发给超管'")
    private int tenantSuperAdminState;


    public AnnouncementAggregate entityToAggregate() {

        Collection<AnnouncementReleaseInteriorAdminRoleVO> interiorAdminRoleVOs = new ArrayList<AnnouncementReleaseInteriorAdminRoleVO>();
        BeanUtils.copyProperties(this.interiorAdminRoleEntities, interiorAdminRoleVOs);
        Collection<AnnouncementReleaseTenantVO> releaseTenantVOs = new ArrayList<AnnouncementReleaseTenantVO>();
        BeanUtils.copyProperties(this.releaseTenantEntities, releaseTenantVOs);

        AnnouncementInfoVO infoVO = new AnnouncementInfoVO.Builder()
                .title(this.title)
                .intro(this.intro)
                .labelNames(this.labelNames)
                .author(this.author)
                .type(this.type)
                .content(this.content)
                .buil();

        AnnouncementReleaseVO releaseVO = new AnnouncementReleaseVO.Builder()
                .state(this.state)
                .isTop(this.isTop)
                .releaseTime(this.releaseTime)
                .remindType(this.remindType)
                .remindContinueSeconds(this.remindContinueSeconds)
                .remindValidDayNum(this.remindValidDayNum)
                .validStart(this.validStart)
                .validEnd(this.validEnd)
                .interiorScopeState(this.interiorScopeState)
                .adminRoleVOs(interiorAdminRoleVOs)
                .tenantScopeState(this.tenantScopeState)
                .tenantVOs(releaseTenantVOs)
                .tenantSuperAdminState(this.tenantSuperAdminState)
                .buil();

        AnnouncementAggregate aggregate = new AnnouncementAggregate.Builder()
                .id(this.id)
                .createTime(this.createTime)
                .announcementInfo(infoVO)
                .announcementRelease(releaseVO)
                .build();

        return aggregate;
    }


}
