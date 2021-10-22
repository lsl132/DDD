package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.AnnouncementLableApplicationService;
import org.example.software.application.service.AnnouncementTypeApplicationService;
import org.example.software.interfaces.in.AnnouncementLableIn;
import org.example.software.interfaces.in.AnnouncementTypeIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公告类型请求入口
 * @author SHK
 */
@Slf4j
@RestController
@RequestMapping("/announcement/type")
public class AnnouncementTypeController {

    @Resource
    private AnnouncementTypeApplicationService announcementTypeApplicationService;


    /**
     * 创建公告标签
     * @param in
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createType(@RequestBody @Validated AnnouncementTypeIn in) {
        log.info("-------create--------");
        return announcementTypeApplicationService.create(in.getName());

    }

    @PostMapping("/edit")
    public ResponseResult editType(@RequestBody @Validated AnnouncementTypeIn in) {
        log.info("-------edit--------");
        return announcementTypeApplicationService.update(in.getId(), in.getName());

    }

    @PostMapping("/remove")
    public ResponseResult removeType(@RequestBody @Validated AnnouncementTypeIn in) {

        log.info("-------remove--------");

        return announcementTypeApplicationService.remove(in.getId());

    }

    @PostMapping("/find")
    public ResponseResult findConditionType(@RequestBody @Validated AnnouncementTypeIn in) {
        log.info("-------find--------");
        return announcementTypeApplicationService.findCondition(in);
    }

    @PostMapping("/findBy")
    public ResponseResult findByConditionType(@RequestBody @Validated AnnouncementTypeIn in) {
        log.info("-------findBy--------");
        return announcementTypeApplicationService.findByCondition(in);
    }

}
