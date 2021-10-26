package org.example.software.application.dto;

import lombok.Data;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseInteriorAdminRoleVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseTenantVO;

import java.util.Date;
import java.util.List;

/**
 * 公告信息
 *
 * @author SHK
 */
@Data
public class AnnouncementDto {

    /** 公告标题 */
    private String title;
    /** 公告简介 */
    private String intro;
    /** 公告类型 */
    private Integer type;
    /** 公告标签 */
    private String lableNames;
    /** 发布人 */
    private String author;
    /** 公告内容 */
    private String content;
    /**********************************************************/
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
    /*******************************************************************/
    private Date startCreateTime;
    private Date endCreateTime;
    private Integer pageNum;
    private Integer pageSize;






}
