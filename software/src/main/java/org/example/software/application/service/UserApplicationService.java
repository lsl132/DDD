package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.common.util.Generator;
import org.example.common.util.MD5Util;
import org.example.common.util.SnowFlakeID;
import org.example.software.domain.aggregate.User;
import org.example.software.domain.aggregate.entity.UserAccount;
import org.example.software.domain.aggregate.vo.UserContact;
import org.example.software.domain.service.UserDomainService;
import org.example.software.infrastructure.enums.UserAccountTypeEnum;
import org.example.software.infrastructure.enums.UserLevelEnum;
import org.example.software.infrastructure.enums.UserStateEnum;
import org.example.software.infrastructure.repository.user.UserAccountRepository;
import org.example.software.infrastructure.repository.user.UserRepository;
import org.example.software.interfaces.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * 公告应用层服务实现类
 * @author SHK
 */
@Slf4j
@Service
public class UserApplicationService {

    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserAccountRepository userAccountRepository;

    @Resource
    private UserDomainService userDomainService;

    public ResponseResult createUser(UserDto dto) {

        Date date = new Date();

        User user = new User();
        user.setId(SnowFlakeID.getSnowNumber());
        user.setUserName(dto.getUserName());
        user.setNickName(dto.getNickName());

        UserContact userContact = new UserContact();
        userContact.setMobile(dto.getMobile());
        userContact.setEmail(dto.getEmail());

        user.setUserContact(userContact);

        user.setType(dto.getType());
        user.setLevel(UserLevelEnum.ADMIN.getKey());
        user.setState(UserStateEnum.NORMAL.getKey());
        user.setCreateTime(date);

        List<UserAccount> userAccounts = new LinkedList<>();
        String pwd = MD5Util.encodeByMD5(dto.getPassword());

        UserAccount a1 = new UserAccount();
        a1.setUser(user);
        a1.setIdentifier(Generator.createAccount("A"));
        a1.setCredential(pwd);
        a1.setIdentifierType(UserAccountTypeEnum.ACCOUNT.getKey());
        a1.setCreateTime(new Date());
        userAccounts.add(a1);

        UserAccount a2 = new UserAccount();
        a2.setUser(user);
        a2.setIdentifier(user.getUserContact().getMobile());
        a2.setCredential(pwd);
        a2.setIdentifierType(UserAccountTypeEnum.MOBILE.getKey());
        a2.setCreateTime(new Date());
        userAccounts.add(a2);

        UserAccount a3 = new UserAccount();
        a3.setUser(user);
        a3.setIdentifier(user.getUserContact().getEmail());
        a3.setCredential(pwd);
        a3.setIdentifierType(UserAccountTypeEnum.EMAIL.getKey());
        a3.setCreateTime(new Date());
        userAccounts.add(a3);

        user.setUserAccount(userAccounts);

        userRepository.save(user);


        //发布消息，由消费者查找绑定关联用户...
//        applicationContext.publishEvent(new UserEvent.UserCreateEvent(applicationContext, user, pwd));


        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, user);
    }

    public void createUserAccount(User user, String pwd) {

        UserAccount a1 = new UserAccount();
        a1.setUser(user);
        a1.setIdentifier(Generator.createAccount("A"));
        a1.setCredential(pwd);
        a1.setIdentifierType(UserAccountTypeEnum.ACCOUNT.getKey());
        a1.setCreateTime(new Date());
        userAccountRepository.save(a1);

        UserAccount a2 = new UserAccount();
        a2.setIdentifier(user.getUserContact().getMobile());
        a2.setCredential(pwd);
        a2.setIdentifierType(UserAccountTypeEnum.MOBILE.getKey());
        a2.setCreateTime(new Date());
        userAccountRepository.save(a2);

        UserAccount a3 = new UserAccount();
        a3.setIdentifier(user.getUserContact().getEmail());
        a3.setCredential(pwd);
        a3.setIdentifierType(UserAccountTypeEnum.EMAIL.getKey());
        a3.setCreateTime(new Date());
        userAccountRepository.save(a3);
    }

}
