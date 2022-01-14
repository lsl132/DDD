package org.example.software.domain.aggregate.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * @Describe : 权限包
 * @Author : SHK
 * @Date : 2022/1/12 15:44
 */
@Getter
@Setter
@Embeddable
public class UserAuthorityPackage {

    private Integer authorityPackageId;

    private String authorityPackageName;

    private Date startValidDate;

    private Date endValidDate;


}
