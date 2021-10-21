package org.example.software.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author SHK
 */
@Data
public class AnnouncementLableDto {


    private Integer id;
    private String name;
    private Date start;
    private Date end;

}
