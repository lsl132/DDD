package org.example.software.interfaces.in;

import lombok.Data;

import java.util.Date;

/**
 * 公告标签入参
 * @author SHK
 */
@Data
public class AnnouncementLableIn {

    private Integer id;
    private String name;
    private Date start;
    private Date end;

}
