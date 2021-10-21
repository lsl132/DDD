package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.AnnouncementApplicationService;
import org.example.software.application.service.AnnouncementLableApplicationService;
import org.example.software.interfaces.in.AnnouncementLableIn;
import org.example.software.interfaces.in.CreateAnnouncementIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/announcement/lable")
public class AnnouncementLableController {


    @Autowired
    private AnnouncementLableApplicationService announcementLableApplicationService;






    /**
     * 创建公告标签
     * @param in
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createAnnouncementLable(@RequestBody @Validated AnnouncementLableIn in) {

        log.info("-------create--------");

        return announcementLableApplicationService.create(in.getName());

    }

    @PostMapping("/edit")
    public ResponseResult editAnnouncementLable(@RequestBody @Validated AnnouncementLableIn in) {

        log.info("-------edit--------");

        return announcementLableApplicationService.update(in.getId(), in.getName());

    }

    @PostMapping("/remove")
    public ResponseResult removeAnnouncementLable(@RequestBody @Validated AnnouncementLableIn in) {

        log.info("-------remove--------");

        return announcementLableApplicationService.remove(in.getId());

    }

    @PostMapping("/find")
    public ResponseResult findAnnouncementLableAll() {

        log.info("-------find--------");

        return announcementLableApplicationService.findAll();

    }

}
