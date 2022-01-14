package org.example.software.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Describe : 权限点(聚合根/实体)
 * @Author : SHK
 * @Date : 2022/1/13 18:42
 */
@Getter
@Setter
@Entity(name = "tb_authority_point")
public class AuthorityPoint implements Serializable {

    @Id
    @Column(columnDefinition = "INT(20)  COMMENT 'ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限点代码'")
    private String code;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限点名称'")
    private String name;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限点标签'")
    private String label;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '资源Key'")
    private String resourceKey;

    @Column(columnDefinition = "INT(2)  COMMENT '资源类型'")
    private Integer resourceType;

    @Column(columnDefinition = "INT(2)  COMMENT '权限点类型'")
    private Integer type;

    @Column(columnDefinition = "INT(2)  COMMENT '权限点状态'")
    private String state;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限点描述'")
    private String describe;

    @Column(columnDefinition = "datetime  COMMENT '权限点创建时间'")
    private Date createTime;


    public void replaceInfo(String newName, String newDescribe, String newLabel) {
        //
        this.name = newName;
        this.describe = newDescribe;
        this.label = newLabel;
    }

    public void replaceResource(Integer newType, String newKey) {
        //
        this.resourceType = newType;
        this.resourceKey = newKey;
    }

    public void changeType(Integer newType) {
        //
        this.type = newType;
    }

    public void authorityActivate() {

    }

    public void authorityUnder() {

    }

    public void authorityDestroy() {

    }

}
