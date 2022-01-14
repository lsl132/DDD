package org.example.software.application.event.monitor;

import lombok.extern.slf4j.Slf4j;
import org.example.software.application.event.publish.UserEvent;
import org.example.software.application.service.UserApplicationService;
import org.example.software.domain.aggregate.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Describe : TODO
 * @Author : SHK
 * @Date : 2022/1/13 14:02
 */
@Slf4j
@Component
public class UserEventListener {

    @Resource
    private UserApplicationService userApplicationService;

    @EventListener(classes = UserEvent.UserCreateEvent.class)
    public void onCreateUserEvent(UserEvent.UserCreateEvent event) {
        log.info("onCreateUserEvent收到发布事件通知-{}",event.getMsg());
        User user = event.getMsg();
        String pwd = event.getPwd();
        userApplicationService.createUserAccount(user, pwd);
    }

}
