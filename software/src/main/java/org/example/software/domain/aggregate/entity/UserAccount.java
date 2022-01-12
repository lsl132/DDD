package org.example.software.domain.aggregate.entity;

import lombok.Data;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.common.util.MD5Util;
import org.example.software.domain.aggregate.UserAggregate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 公告数据表实体类
 * @author SHK
 */

@Data
@Entity(name = "tb_user_account")
public class UserAccount implements Serializable {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '公告ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserAggregate user;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '帐号'")
    private String identifier;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '密码/凭证'")
    private String credential;

    @Column(columnDefinition = "INT(10)  COMMENT '帐号类型'")
    private Integer identifierType;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;


    public void replaceCredential(String newCredential) {
        //内容检查
        if(newCredential == null || newCredential.length() == 0) {
            new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(),GlobalExceptionEnum.CHECK_NULL_ERROR.getDescribe());
        }
        this.credential = MD5Util.encodeByMD5(newCredential);

    }

}
