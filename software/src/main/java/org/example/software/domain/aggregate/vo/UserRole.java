package org.example.software.domain.aggregate.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @Describe : 用户角色
 * @Author : SHK
 * @Date : 2022/1/12 15:42
 */
@Getter
@Setter
@Embeddable
public class UserRole {

    private Integer roleId;

    private String roleName;


}
