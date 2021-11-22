package org.example.software.application.event;

import org.example.software.domain.aggregate.AnnouncementAggregate;
import org.springframework.context.ApplicationEvent;

/**
 * 公告取消发布事件
 * @author SHK
 */
public class AnnouncementUnReleaseEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1151284154123L;

    //消息内容
    private AnnouncementAggregate aggregate;

    public AnnouncementUnReleaseEvent(Object source, AnnouncementAggregate aggregate) {
        super(source);
        this.aggregate = aggregate;
    }

    public AnnouncementAggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(AnnouncementAggregate aggregate) {
        this.aggregate = aggregate;
    }
}
