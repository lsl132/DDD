package org.example.software.interfaces.in;

import lombok.Data;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseInteriorAdminRoleVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseTenantVO;

import java.util.Date;
import java.util.List;

/**
 * 创建公告入参
 *
 * @author SHK
 */
@Data
public class AnnouncementIn {

    private Long id;
    /** 公告标题 */
    private String title;
    /** 公告简介 */
    private String intro;
    /** 公告类型 */
    private Integer type;
    /** 公告标签 */
    private String lableNames;
    /** 发布人 */
    private Integer author;
    /** 公告内容 */
    private String content;


    private Integer state;
    private Integer isTop;

    private Integer remindType;
    private Integer remindContinueSeconds;
    private Integer remindValidDayNum;

    private Date validStart;
    private Date validEnd;

    private Integer interiorScopeState;
    private List<AnnouncementReleaseInteriorAdminRoleVO> interiorAdminRoleEntities;

    private Integer tenantScopeState;
    private List<AnnouncementReleaseTenantVO> releaseTenantEntities;
    private Integer tenantSuperAdminState;

    private Integer pageNum;
    private Integer pageSize;


}
