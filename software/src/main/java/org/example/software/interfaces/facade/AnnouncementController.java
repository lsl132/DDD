package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.AnnouncementApplicationService;
import org.example.software.interfaces.in.CreateAnnouncementIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 公告
 */

@Slf4j
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {


    @Autowired
    private AnnouncementApplicationService announcementApplicationService;






    /**
     * 创建公告
     * @param in
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createAnnouncement(@RequestBody @Validated CreateAnnouncementIn in) {

        log.info("-------test--------");






        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);

    }


}
