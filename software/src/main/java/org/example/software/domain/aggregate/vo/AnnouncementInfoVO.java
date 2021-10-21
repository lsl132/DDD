package org.example.software.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;


/**
 * 公告信息值对象
 */
@Getter
public class AnnouncementInfoVO {

    /** 标题 */
    private String title;
    /** 类型 */
    private Integer type;
    /** 标签 */
    private String label;
    /** 内容 */
    private String content;
    /** 发布人 */
    private String publisherName;

    private AnnouncementInfoVO(Builder builder) {

    }

    public static class Builder {
        private String title;
        private String label;
        private String content;




    }
}
