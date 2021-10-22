package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.AnnouncementAuthorApplicationService;
import org.example.software.application.service.AnnouncementTypeApplicationService;
import org.example.software.interfaces.in.AnnouncementAuthorIn;
import org.example.software.interfaces.in.AnnouncementTypeIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公告公布者请求入口
 * @author SHK
 */
@Slf4j
@RestController
@RequestMapping("/announcement/author")
public class AnnouncementAuthorController {


    @Resource
    private AnnouncementAuthorApplicationService announcementAuthorApplicationService;






    /**
     * 创建公告作者
     * @param in
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createAuthor(@RequestBody @Validated AnnouncementAuthorIn in) {

        log.info("-------create--------");

        return announcementAuthorApplicationService.create(in.getName());

    }

    @PostMapping("/edit")
    public ResponseResult editAuthor(@RequestBody @Validated AnnouncementAuthorIn in) {

        log.info("-------edit--------");

        return announcementAuthorApplicationService.update(in.getId(), in.getName());

    }

    @PostMapping("/remove")
    public ResponseResult removeAuthor(@RequestBody @Validated AnnouncementAuthorIn in) {

        log.info("-------remove--------");

        return announcementAuthorApplicationService.remove(in.getId());

    }

    @PostMapping("/find")
    public ResponseResult findConditionAuthor(@RequestBody @Validated AnnouncementAuthorIn in) {

        log.info("-------find--------");

        return announcementAuthorApplicationService.findCondition(in);
    }

    @PostMapping("/findBy")
    public ResponseResult findByAuthor(@RequestBody @Validated AnnouncementAuthorIn in) {
        log.info("-------findBy--------");
        return announcementAuthorApplicationService.findByCondition(in);
    }

}
