package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.software.domain.aggregate.entity.UserAccount;
import org.example.software.infrastructure.repository.BaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 公告应用层服务实现类
 * @author SHK
 */
@Slf4j
@Service
public class UserApplicationService {

    @Resource
    private BaseRepository<UserAccount, Long> baseRepository;


}
