package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.dto.AnnouncementAuthorDto;
import org.example.software.application.dto.AnnouncementTypeDto;
import org.example.software.domain.aggregate.AnnouncementAuthorAggregate;
import org.example.software.domain.entity.AnnouncementAuthorEntity;
import org.example.software.domain.entity.AnnouncementTypeEntity;
import org.example.software.domain.service.AnnouncementAuthorDomainService;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.interfaces.in.AnnouncementAuthorIn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 公告公布人应用层服务实现类
 * @author SHK
 */
@Slf4j
@Service
public class AnnouncementAuthorApplicationService {

    @Resource
    private BaseRepository<AnnouncementAuthorEntity, Integer> baseRepository;
    @Resource
    private AnnouncementAuthorDomainService announcementAuthorDomainService;

    /**
     * 新增
     */
    public ResponseResult create(String name) {
        AnnouncementAuthorAggregate aggregate = new AnnouncementAuthorAggregate.Builder()
                .name(name)
                .createTime(new Date())
                .build();
        AnnouncementAuthorEntity type = aggregate.aggregateToEntity();
        baseRepository.save(type);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 信息修改修改
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Integer id, String name) {
        AnnouncementAuthorEntity author = baseRepository.findById(AnnouncementAuthorEntity.class, id);
        AnnouncementAuthorEntity newAuthor = announcementAuthorDomainService.updateAuthorName(author, name);
        baseRepository.replace(newAuthor);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 信息删除
     */
    public ResponseResult remove(Integer id) {
        announcementAuthorDomainService.removeType(id);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 条件查询列表
     */
    public ResponseResult findCondition(AnnouncementAuthorIn in) {
        AnnouncementAuthorDto dto = new AnnouncementAuthorDto();
        BeanUtils.copyProperties(in, dto);
        List<AnnouncementAuthorEntity> list = announcementAuthorDomainService.findCondition(dto);
        return new ResponseResult<List<AnnouncementAuthorEntity>>(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, list);
    }

    /**
     * 分页条件查询
     * @param in
     * @return
     */
    public ResponseResult findByCondition(AnnouncementAuthorIn in) {
        AnnouncementAuthorDto dto = new AnnouncementAuthorDto();
        BeanUtils.copyProperties(in, dto);
        Page<AnnouncementAuthorEntity> page = announcementAuthorDomainService.findByCondition(dto);
        return new ResponseResult<Page<AnnouncementAuthorEntity>>(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, page);
    }

}
