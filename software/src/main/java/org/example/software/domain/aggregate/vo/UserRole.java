package org.example.software.domain.aggregate.vo;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @Describe : 用户角色
 * @Author : SHK
 * @Date : 2022/1/12 15:42
 */
@Data
@Embeddable
public class UserRole {

    private Integer roleId;

    private String roleName;


}
