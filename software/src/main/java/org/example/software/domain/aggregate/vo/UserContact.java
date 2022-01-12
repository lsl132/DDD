package org.example.software.domain.aggregate.vo;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * @Describe : 用户联系对象——值对象
 * @Author : SHK
 * @Date : 2022/1/12 15:26
 */
@Data
@Embeddable
public class UserContact {

    private String mobile;

    private String email;

}
