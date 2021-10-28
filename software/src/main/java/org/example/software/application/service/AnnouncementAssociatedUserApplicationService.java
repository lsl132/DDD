package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.software.domain.entity.AnnouncementAssociatedUserEntity;
import org.example.software.domain.entity.AnnouncementEntity;
import org.example.software.infrastructure.repository.BaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author SHK
 */

@Slf4j
@Service
public class AnnouncementAssociatedUserApplicationService {

    @Resource
    private BaseRepository<AnnouncementAssociatedUserEntity, Long> baseRepository;

    /**
     * 查看公告-修改关联个人查看状态
     */



}
