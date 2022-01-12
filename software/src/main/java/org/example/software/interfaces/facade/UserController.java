package org.example.software.interfaces.facade;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.service.UserApplicationService;
import org.example.software.interfaces.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 用户
 * @author SHK
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserApplicationService announcementApplicationService;


    /**
     * 创建用户
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public ResponseResult createUser(@RequestBody @Validated UserDto dto) {
        log.info("-------createUser--------");
       return null;
    }


}
