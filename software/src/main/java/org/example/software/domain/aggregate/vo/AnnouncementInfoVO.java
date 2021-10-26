package org.example.software.domain.aggregate.vo;

import lombok.Getter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.infrastructure.constant.Constant;
import org.wildfly.common.annotation.NotNull;
import sun.awt.image.IntegerComponentRaster;

import javax.persistence.Column;


/**
 * 公告信息值对象
 */
@Getter
public class AnnouncementInfoVO {

    /** 标题 */
    private String title;
    /** 简介 */
    private String intro;
    /** 类型 */
    private Integer type;
    /** 标签 */
    private String labelNames;
    /** 内容 */
    private String content;
    /** 发布人 */
    private Integer author;

    public void replaceTitle(@NotNull String title) {
        if(title.length() > Constant.GENERIC_TITLE_MAX_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标题长度大于【"+Constant.GENERIC_TITLE_MAX_LENGTH+"】最大要求");
        }
        if(title.length() < Constant.GENERIC_TITLE_MIN_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标题长度小于【"+Constant.GENERIC_TITLE_MIN_LENGTH+"】最小要求");
        }
        this.title = title;
    }

    public void replaceIntro(@NotNull String intro) {
        if(intro.length() > Constant.GENERIC_INTRO_MAX_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "简介长度大于【"+Constant.GENERIC_INTRO_MIN_LENGTH+"】最大要求");
        }
        if(intro.length() < Constant.GENERIC_INTRO_MIN_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "简介长度小于【"+Constant.GENERIC_INTRO_MIN_LENGTH+"】最小要求");
        }
        this.intro = intro;
    }

    public void replaceType(@NotNull Integer type) {
        this.type = type;
    }

    public void replaceLabelNames(String labelNames) {
        if(labelNames.length() > Constant.GENERIC_LABLE_MAX_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标签名称长度大于【"+Constant.GENERIC_LABLE_MAX_LENGTH+"】最大要求");
        }
        this.labelNames = labelNames;
    }

    public void replaceAuthor(@NotNull Integer author) {
        this.author = author;
    }

    public void replaceContent(@NotNull String content) {

        if(content.length() > Constant.ANNOUNCEMENT_CONTENT_MAX_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "公告内容长度大于【"+Constant.ANNOUNCEMENT_CONTENT_MAX_LENGTH+"】最大要求");
        }
        if(content.length() < Constant.ANNOUNCEMENT_CONTENT_MIN_LENGTH) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "公告内容长度小于【"+Constant.ANNOUNCEMENT_CONTENT_MIN_LENGTH+"】最小要求");
        }
        this.content = content;
    }


    private AnnouncementInfoVO(Builder builder) {
        this.title = builder.title;
        this.intro = builder.intro;
        this.type = builder.type;
        this.labelNames = builder.labelNames;
        this.content = builder.content;
        this.author = builder.author;
    }

    public static class Builder {
        private String title;
        private String intro;
        private Integer type;
        private String labelNames;
        private Integer author;
        private String content;

        public Builder title(@NotNull String title) {
            if(title.length() < Constant.GENERIC_TITLE_MIN_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标题长度小于【"+Constant.GENERIC_TITLE_MIN_LENGTH+"】最小要求");
            }
            if(title.length() > Constant.GENERIC_TITLE_MAX_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标题长度大于【"+Constant.GENERIC_TITLE_MAX_LENGTH+"】最大要求");
            }
            this.title = title;
            return this;
        }

        public Builder intro(@NotNull String intro) {
            if(intro.length() < Constant.GENERIC_INTRO_MIN_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "简介长度小于【"+Constant.GENERIC_INTRO_MIN_LENGTH+"】最小要求");
            }
            if(intro.length() > Constant.GENERIC_INTRO_MAX_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "简介长度大于【"+Constant.GENERIC_INTRO_MIN_LENGTH+"】最大要求");
            }
            this.intro = intro;
            return this;
        }

        public Builder type(@NotNull Integer type) {
            this.type = type;
            return this;
        }

        public Builder labelNames(String labelNames) {
            if(labelNames.length() > Constant.GENERIC_LABLE_MAX_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "标签名称长度大于【"+Constant.GENERIC_LABLE_MAX_LENGTH+"】最大要求");
            }
            this.labelNames = labelNames;
            return this;
        }

        public Builder author(@NotNull Integer author) {
            this.author = author;
            return this;
        }

        public Builder content(@NotNull String content) {
            if(content.length() < Constant.ANNOUNCEMENT_CONTENT_MIN_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "公告内容长度小于【"+Constant.ANNOUNCEMENT_CONTENT_MIN_LENGTH+"】最小要求");
            }
            if(content.length() > Constant.ANNOUNCEMENT_CONTENT_MAX_LENGTH) {
                throw new GlobalException(GlobalExceptionEnum.CHECK_LENGTH_ERROR.getCode(), "公告内容长度大于【"+Constant.ANNOUNCEMENT_CONTENT_MAX_LENGTH+"】最大要求");
            }
            this.content = content;
            return this;
        }

        public AnnouncementInfoVO buil() {
            return new AnnouncementInfoVO(this);
        }

    }
}
