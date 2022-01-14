package org.example.software.domain.aggregate;

import lombok.Getter;
import lombok.Setter;
import org.example.software.domain.aggregate.AuthorityPoint;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Describe : 权限包——聚合根
 * @Author : SHK
 * @Date : 2022/1/13 18:36
 */
@Getter
@Setter
@Entity(name = "tb_authority_package")
public class AuthorityPackage implements Serializable {

    @Id
    @Column(columnDefinition = "INT(10)  COMMENT 'ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private AuthorityPoint authorityPoint;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限包代码'")
    private String code;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限包名称'")
    private String name;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限包简介'")
    private String intro;

    @Column(columnDefinition = "INT(2)  COMMENT '权限包状态'")
    private Integer state;

    @Column(columnDefinition = "datetime  COMMENT '权限包创建时间'")
    private Date createTime;



}
