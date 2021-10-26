package org.example.software.infrastructure.constant;

/**
 * 公共常量
 * @author SHK
 */
public class Constant {
    /** 通用名称长度 */
    public static final int GENERIC_NAME_MIN_LENGTH = 2;
    public static final int GENERIC_NAME_MAX_LENGTH = 10;

    /** 通用标题长度 */
    public static final int GENERIC_TITLE_MIN_LENGTH = 2;
    public static final int GENERIC_TITLE_MAX_LENGTH = 30;

    /** 通用简介长度 */
    public static final int GENERIC_INTRO_MIN_LENGTH = 8;
    public static final int GENERIC_INTRO_MAX_LENGTH = 500;

    /** 通用标签名称长度 */
    public static final int GENERIC_LABLE_MAX_LENGTH = 128;

    /** 通用内容长度 */
    public static final int ANNOUNCEMENT_CONTENT_MIN_LENGTH = 2;
    public static final int ANNOUNCEMENT_CONTENT_MAX_LENGTH = 60000;

    /** 提醒计时秒数区间 */
    public static final int REMIND_SECONDS_MIN_NUM = 0;
    public static final int REMIND_SECONDS_MAX_NUM = 30;

    /** 提醒有效天数区间 */
    public static final int REMIND_VAILD_DAY_MIN_NUM = 0;
    public static final int REMIND_VAILD_DAY_MAX_NUM = 15;
}
