package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AnnouncementController {

    @PostMapping("/test")
    public ResponseResult test(@RequestBody @Validated Integer num) {

        log.info("-------test--------");
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);

    }


}
