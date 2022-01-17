package org.example.software;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.common.util.Generator;
import org.example.software.application.service.UserApplicationService;
import org.example.software.infrastructure.enums.UserTypeEnum;
import org.example.software.interfaces.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @Describe : TODO
 * @Author : SHK
 * @Date : 2021/11/30 13:58
 */
@Slf4j
@SpringBootTest
public class SoftwareTests {

    @Resource
    private UserApplicationService userApplicationService;


    @Test
    public void saveUser() {
        UserDto dto = new UserDto();

        dto.setUserName(Generator.createChineseName());
        dto.setNickName(Generator.createChineseName()+Generator.createChineseName());
        dto.setType(UserTypeEnum.FORMAL.getKey());
        dto.setMobile(Generator.createMobile());
        dto.setEmail(Generator.createEmail());
        dto.setPassword(Generator.createPassword(2));


        ResponseResult res = userApplicationService.createUser(dto);
        log.info(res.toString());
    }





}
