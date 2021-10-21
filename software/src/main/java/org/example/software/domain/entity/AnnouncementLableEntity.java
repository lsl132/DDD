package org.example.software.domain.entity;

import lombok.Data;
import org.example.software.domain.aggregate.AnnouncementLableAggregate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 公告标签
 * @author SHK
 */
@Data
@Entity(name = "tb_announcement_lable")
public class AnnouncementLableEntity implements Serializable {

    @Id
    @Column(columnDefinition = "INT(11)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '标签名称'")
    private String name;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;


    public AnnouncementLableAggregate entityToAggregate() {
        AnnouncementLableAggregate aggregate = new AnnouncementLableAggregate.Builder()
                .id(id)
                .name(name)
                .createTime(createTime)
                .build();
        return aggregate;
    }

}
