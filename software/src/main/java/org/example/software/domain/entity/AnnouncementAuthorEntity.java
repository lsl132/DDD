package org.example.software.domain.entity;

import lombok.Data;
import org.example.software.domain.aggregate.AnnouncementAuthorAggregate;
import org.example.software.domain.aggregate.AnnouncementLableAggregate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  公告作者发布人
 * @author SHK
 */
@Data
@Entity(name = "tb_announcement_author")
public class AnnouncementAuthorEntity implements Serializable {

    @Id
    @Column(columnDefinition = "int(11)  COMMENT '数据标识'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '发布人名称'")
    private String name;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

    public AnnouncementAuthorAggregate entityToAggregate() {
        AnnouncementAuthorAggregate aggregate = new AnnouncementAuthorAggregate.Builder()
                .id(id)
                .name(name)
                .createTime(createTime)
                .build();
        return aggregate;
    }

}
