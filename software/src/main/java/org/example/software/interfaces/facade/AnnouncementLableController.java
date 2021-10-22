package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.AnnouncementLableApplicationService;
import org.example.software.interfaces.in.AnnouncementLableIn;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公告标签入口
 * @author SHK
 */
@Slf4j
@RestController
@RequestMapping("/announcement/lable")
public class AnnouncementLableController {


    @Resource
    private AnnouncementLableApplicationService announcementLableApplicationService;


    /**
     * 创建公告标签
     * @param in
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createLable(@RequestBody @Validated AnnouncementLableIn in) {

        log.info("-------create--------");

        return announcementLableApplicationService.create(in.getName());

    }

    @PostMapping("/edit")
    public ResponseResult editLable(@RequestBody @Validated AnnouncementLableIn in) {

        log.info("-------edit--------");

        return announcementLableApplicationService.update(in.getId(), in.getName());

    }

    @PostMapping("/remove")
    public ResponseResult removeLable(@RequestBody @Validated AnnouncementLableIn in) {

        log.info("-------remove--------");

        return announcementLableApplicationService.remove(in.getId());

    }

    @PostMapping("/find")
    public ResponseResult findLable(@RequestBody @Validated AnnouncementLableIn in) {
        log.info("-------find--------");
        return null;
    }

    @PostMapping("/findBy")
    public ResponseResult findByLable(@RequestBody @Validated AnnouncementLableIn in) {
        log.info("-------findBy--------");
        return announcementLableApplicationService.findByCondition(in);
    }

}
