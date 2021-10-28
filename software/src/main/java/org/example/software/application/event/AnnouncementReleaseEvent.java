package org.example.software.application.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.software.domain.aggregate.AnnouncementAggregate;
import org.springframework.context.ApplicationEvent;

/**
 * 公告发布事件
 * @author SHK
 */
public class AnnouncementReleaseEvent extends ApplicationEvent {

    private static final long serialVersionUID = 109182438154123L;

    //消息内容
    private AnnouncementAggregate aggregate;

    public AnnouncementReleaseEvent(Object source, AnnouncementAggregate aggregate) {
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
