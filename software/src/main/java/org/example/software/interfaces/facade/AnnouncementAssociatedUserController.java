package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.interfaces.in.AnnouncementLableIn;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public class AnnouncementAssociatedUserController {


    /**
     * 分页列表查询
     * @param in
     * @return
     */
    @PostMapping("/findBy")
    public ResponseResult findByLable(@RequestBody @Validated AnnouncementLableIn in) {
//        log.info("-------findBy--------");
//        return announcementLableApplicationService.findByCondition(in);
        return null;
    }
}
