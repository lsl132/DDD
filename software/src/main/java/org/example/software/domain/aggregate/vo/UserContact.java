package org.example.software.domain.aggregate.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @Describe : 用户联系对象——值对象
 * @Author : SHK
 * @Date : 2022/1/12 15:26
 */
@Getter
@Setter
@Embeddable
public class UserContact {

    private String mobile;

    private String email;

}
