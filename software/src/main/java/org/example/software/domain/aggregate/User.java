package org.example.software.domain.aggregate;


import lombok.Getter;
import lombok.Setter;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.domain.aggregate.entity.UserAccount;
import org.example.software.domain.aggregate.vo.UserAuthorityPackage;
import org.example.software.domain.aggregate.vo.UserContact;
import org.example.software.domain.aggregate.vo.UserRole;
import org.example.software.infrastructure.enums.UserStateEnum;
import org.example.software.infrastructure.enums.UserTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 用户
 * 这是一个实体同时也是一个聚合根
 * 注意：不要用lombok的@Data ，会自动生成toString引起关系调用堆栈溢出
 * @author SHK
 */
@Getter
@Setter
@Entity(name = "tb_user")
public class User implements Serializable {

    @Id
    @Column(columnDefinition = "BIGINT(20)  COMMENT '用户标识'")
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '用户名称'")
    private String userName;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '用户昵称'")
    private String nickName;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '用户头像'")
    private String avatar;

    @Column(columnDefinition = "VARCHAR(1024)  COMMENT '用户备注说明'")
    private String remarks;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '用户级别-9：正式用户，2：三方映射用户'")
    private Integer level;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '用户类型-1：正式用户，2：三方映射用户'")
    private Integer type;

    @Column(columnDefinition = "VARCHAR(255)  COMMENT '用户状态-0：休眠，1：正常，2：冻结，9销毁'")
    private Integer state;

    @Column(columnDefinition = "datetime  COMMENT '创建时间'")
    private Date createTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "mobile",column = @Column(columnDefinition = "VARCHAR(255)  COMMENT '手机号码'")),
            @AttributeOverride(name = "email",column = @Column(columnDefinition = "VARCHAR(255)  COMMENT '邮箱'"))
    })
    private UserContact userContact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<UserAccount> userAccount;

    @ElementCollection
    @CollectionTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @AttributeOverrides({
            @AttributeOverride(name = "roleId",column = @Column(columnDefinition = "INT(10)  COMMENT '角色ID'")),
            @AttributeOverride(name = "roleName",column = @Column(columnDefinition = "VARCHAR(255)  COMMENT '角色名称'"))
    })
    private Set<UserRole> userRoles;

    @ElementCollection
    @CollectionTable(
            name = "tb_user_authority_package",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @AttributeOverrides({
            @AttributeOverride(name = "authorityPackageId",column = @Column(columnDefinition = "INT(10)  COMMENT '权限包ID'")),
            @AttributeOverride(name = "authorityPackageName",column = @Column(columnDefinition = "VARCHAR(255)  COMMENT '权限包名称'"))
    })
    private Set<UserAuthorityPackage> userAuthorityPackages;

    // ------------------------------------------------------------------------------------------------------

    public void replaceBaseInfo(String newNickName, String newRemarks) {
        //参数检查...

        //替换值
        this.nickName = newNickName;
        this.remarks = newRemarks;

    }

    public void replaceAvatar(String newAvatar) {
        //参数检查...

        this.avatar = newAvatar;
    }


    //切换成正式用户
    public void changeTypeFormal() {
        //前置条件检查...例如必须是三方映射用户类型


        this.type = UserTypeEnum.FORMAL.getKey();

        //给用户的帐号密码进行一次重置



        //发送此帐号的密码邮件

    }


    public void userActivate() {
        //不能是已销毁用户
        if(this.state == UserStateEnum.DESTROY.getKey()) {
            new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(),GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getDescribe());
        }
        //正常用户无需激活
        if(this.state == UserStateEnum.NORMAL.getKey()) {
            new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(),GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getDescribe());
        }

        this.state = UserStateEnum.NORMAL.getKey();

    }

    public void userFreeze() {
        //不能是已销毁用户
        if(this.state == UserStateEnum.DESTROY.getKey()) {
            new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(),GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getDescribe());
        }
        //冻结不应再冻结
        if(this.state == UserStateEnum.FREEZE.getKey()) {
            new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(),GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getDescribe());
        }

        this.state = UserStateEnum.FREEZE.getKey();
    }


    public void userDestroy() {
        //不能是已销毁用户
        if(this.state == UserStateEnum.DESTROY.getKey()) {
            new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(),GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getDescribe());
        }

        this.state = UserStateEnum.DESTROY.getKey();

        //帐号的一些销毁工作，例如密码重置或清空

        //发出通知强制清除在线的用户、销毁记录留痕等

    }



    public void replaceUserRole(Set<UserRole> roles) {
        //清空现有用户角色集
        this.userRoles = new HashSet<>();
        //替换现有用户角色集
        this.userRoles = roles;
    }


    public void replaceUserAuthorityPackage(Set<UserAuthorityPackage> packages) {
        this.userAuthorityPackages = new HashSet<>();
        this.userAuthorityPackages = packages;
    }

}
