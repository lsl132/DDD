package org.example.software.domain.event.monitor;

import lombok.extern.slf4j.Slf4j;
import org.example.software.application.event.AnnouncementReleaseEvent;
import org.example.software.application.event.AnnouncementUnReleaseEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 公告关联用户事件监听器
 * @author SHK
 */
@Slf4j
@Component
public class AnnouncementAssociatedUserListener {

    @Async
    @EventListener(classes = AnnouncementReleaseEvent.class)
    public void onAnnouncementReleaseEvent(AnnouncementReleaseEvent event) {
        log.info("收到发布事件通知-{}",event.getAggregate());
    }



    @Async
    @EventListener(classes = AnnouncementUnReleaseEvent.class)
    public void onAnnouncementUnReleaseEvent(AnnouncementUnReleaseEvent event) {
        log.info("收到取消发布事件通知-{}",event.getAggregate());
    }

}
