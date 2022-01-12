package org.example.software.domain.aggregate.vo;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @Describe : 权限包
 * @Author : SHK
 * @Date : 2022/1/12 15:44
 */
@Data
@Embeddable
public class UserAuthorityPackage {

    private Integer authorityPackageId;

    private String authorityPackageName;


}
