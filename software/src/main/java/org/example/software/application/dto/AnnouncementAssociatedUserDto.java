package org.example.software.application.dto;

import lombok.Data;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseInteriorAdminRoleVO;
import org.example.software.domain.aggregate.vo.AnnouncementReleaseTenantVO;

import java.util.Date;
import java.util.List;

/**
 * 公告关联用户
 *
 * @author SHK
 */
@Data
public class AnnouncementAssociatedUserDto {

    private Long id;
    private String name;

    /*******************************************************************/
    private Date startCreateTime;
    private Date endCreateTime;
    private Integer pageNum;
    private Integer pageSize;






}
