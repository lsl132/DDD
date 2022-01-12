package org.example.common.redis.message;

import java.io.Serializable;

/**
 * @Describe : 业务消息对象-只做传递，无业务参杂
 * @Author : SHK
 * @Date : 2021/11/29 17:39
 */
public class BusinessMessage implements Serializable {

    /**
     * @Describe: 消息标识（雪花算法可行）
     */
    private Long id;
    /**
     * @Describe: 消息投递时间戳(建议统一格林威治时间)
     */
    private Long timeStamp;
    /**
     * @Describe: 业务类型编号
     */
    private String typeCode;
    /**
     * @Describe: 业务类型说明
     */
    private String typeDescribe;
    /**
     * @Describe: 操作员ID
     */
    private Long operatorId;
    /**
     * @Describe: 操作员名称
     */
    private String operatorName;
    /**
     * @Describe: 变更前信息
     */
    private String before;
    /**
     * @Describe: 变更后信息
     */
    private String after;



    public Long getId() {
        return id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeDescribe() {
        return typeDescribe;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public void setTypeDescribe(String typeDescribe) {
        this.typeDescribe = typeDescribe;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

}
