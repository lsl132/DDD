package org.example.software.interfaces.in;

import lombok.Data;

/**
 * 创建公告入参
 *
 * @author SHK
 */
@Data
public class CreateAnnouncementIn {

    /** 公告标题 */
    private String title;
    /** 公告类型 */
    private Integer type;
    /** 公告标签 */
    private String lable;
    /** 发布人 */
    private String pulish;
    /** 公告内容 */
    private String content;


}
