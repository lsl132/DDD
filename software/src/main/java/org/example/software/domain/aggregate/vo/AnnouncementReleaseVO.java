package org.example.software.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import java.util.Date;

/**
 * 公告-发布值对象
 */
@Getter
public class AnnouncementReleaseVO {


    /** 发布状态  */
    private Integer state;
    /**  是否置顶 */
    private Integer isTop;
    /**  弹窗类型 */
    private Integer continueType;
    /**  弹窗时间 (秒) */
    private Integer continueSeconds;
    /**   */
    private Integer scopeState;

    /**  有效天数 */
    private Integer validNum;

    /** 有效期开始时间 */
    private Date validStart;

    /** 有效期结束时间 */
    private Date validEnd;

    /** 发布时间 */
    private Date publisherTime;


}
