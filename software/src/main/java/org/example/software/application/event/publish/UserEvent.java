package org.example.software.application.event.publish;

import org.example.software.domain.aggregate.User;
import org.springframework.context.ApplicationEvent;


/**
 * @Describe : 用户创建事件
 * @Author : SHK
 * @Date : 2022/1/13 13:52
 */
public class UserEvent  {


    /**
     * @Describe: 用户创建事件
     * @Date:
     */
    public static class UserCreateEvent extends ApplicationEvent {
        //发布的消息
        private User msg;
        private String pwd;

        public UserCreateEvent(Object source, User msg, String pwd) {
            super(source);
            this.msg = msg;
            this.pwd = pwd;
        }

        public User getMsg() {
            return msg;
        }

        public String getPwd() {
            return pwd;
        }
    }




}
