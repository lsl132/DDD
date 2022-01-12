package org.example.software.interfaces.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @Describe : TODO
 * @Author : SHK
 * @Date : 2022/1/7 10:17
 */
@Getter
@Builder
public class UserDto {

    private String title;

    private String intro;

    private String lableNames;

    private String author;

    private Integer type;

    private String content;

}
