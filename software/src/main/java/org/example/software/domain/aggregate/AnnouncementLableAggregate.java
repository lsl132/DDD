package org.example.software.domain.aggregate;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.domain.entity.AnnouncementEntity;
import org.example.software.domain.entity.AnnouncementLableEntity;
import org.example.software.infrastructure.constant.Constant;
import org.wildfly.common.annotation.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告标签
 * @author SHK
 */
@Getter
public class AnnouncementLableAggregate implements Serializable {

    private Integer id;
    private String name;
    private Date createTime;

    public AnnouncementLableEntity aggregateToEntity() {
        AnnouncementLableEntity entity = new AnnouncementLableEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCreateTime(this.createTime);
        return entity;
    }

    /** 替换名称 */
    public void replaceName(@NotNull String name) {
        if(name.length() < Constant.GENERIC_NAME_MIN_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标签名称长度过短");
        }
        if(name.length() > Constant.GENERIC_NAME_MAX_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标签名称长度超出");
        }
        this.name = name;
    }

    private AnnouncementLableAggregate(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.createTime = builder.createTime;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Date createTime;


        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(@NotNull String name) {
            if(name.length() < Constant.GENERIC_NAME_MIN_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标签名称长度过短");
            }
            if(name.length() > Constant.GENERIC_NAME_MAX_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标签名称长度超出");
            }
            this.name = name;
            return this;
        }

        public Builder createTime(Date createTime) {
            if(createTime == null) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "创建时间为空");
            }
            this.createTime = createTime;
            return this;
        }

        public AnnouncementLableAggregate build(){
            return new AnnouncementLableAggregate(this);
        }
    }


}
