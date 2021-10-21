package org.example.software.domain.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 公告类型
 *
 * @author SHK
 */
@Data
@Entity(name = "tb_announcement_type")
public class AnnouncementTypeEntity {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '类型名称'")
    private String name;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

}
