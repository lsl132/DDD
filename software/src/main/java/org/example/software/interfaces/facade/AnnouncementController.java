package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.AnnouncementApplicationService;
import org.example.software.interfaces.in.AnnouncementIn;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 公告
 * @author SHK
 */

@Slf4j
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {


    @Resource
    private AnnouncementApplicationService announcementApplicationService;


    /**
     * 创建公告
     * @param in
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createAnnouncement(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------createAnnouncement--------");
        ResponseResult res = announcementApplicationService.initCreate(in);
        return res;
    }

    /**
     * 发布公告
     * @param in
     * @return
     */
    @PostMapping("/release")
    public ResponseResult releaseAnnouncement(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------releaseAnnouncement--------");
        ResponseResult res = announcementApplicationService.release(in);
        return res;
    }

    /**
     * 撤销发布
     * @param in
     * @return
     */
    @PostMapping("/unrelease")
    public ResponseResult unreleaseAnnouncement(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------unreleaseAnnouncement--------");
        ResponseResult res = announcementApplicationService.unRelease(in.getId());
        return res;
    }


    @PostMapping("/details")
    public ResponseResult getAnnouncementDetails(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------unreleaseAnnouncement--------");
        ResponseResult res = announcementApplicationService.details(in.getId());
        return res;
    }

    /**
     * 编辑信息
     * @param in
     * @return
     */
    @PostMapping("/edit")
    public ResponseResult editAnnouncement(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------editAnnouncement--------");
        ResponseResult res = announcementApplicationService.updateInfo(in);
        return res;
    }

    /**
     * 删除公告
     * @param in
     * @return
     */
    @PostMapping("/remove")
    public ResponseResult removeAnnouncement(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------removeAnnouncement--------");
        ResponseResult res = announcementApplicationService.delete(in.getId());
        return res;
    }

    /**
     * 分页条件查询
     * @param in
     * @return
     */
    @PostMapping("/findBy")
    public ResponseResult findByAnnouncement(@RequestBody @Validated AnnouncementIn in) {
        log.info("-------findByAnnouncement--------");
        ResponseResult res = announcementApplicationService.findByCondition(in);
        return res;
    }
}
