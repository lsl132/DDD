package org.example.software.domain.entity;


import lombok.Data;
import org.example.software.domain.aggregate.AnnouncementLableAggregate;
import org.example.software.domain.aggregate.AnnouncementTypeAggregate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 公告类型
 *
 * @author SHK
 */
@Data
@Entity(name = "tb_announcement_type")
public class AnnouncementTypeEntity implements Serializable {

    @Id
    @Column(columnDefinition = "INT(20)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '类型名称'")
    private String name;

    @Column(columnDefinition = "DATETIME  COMMENT '创建时间'")
    private Date createTime;

    public AnnouncementTypeAggregate entityToAggregate() {
        AnnouncementTypeAggregate aggregate = new AnnouncementTypeAggregate.Builder()
                .id(id)
                .name(name)
                .createTime(createTime)
                .build();
        return aggregate;
    }

}
