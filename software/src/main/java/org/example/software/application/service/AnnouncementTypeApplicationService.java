package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.dto.AnnouncementTypeDto;
import org.example.software.domain.aggregate.AnnouncementTypeAggregate;
import org.example.software.domain.entity.AnnouncementTypeEntity;
import org.example.software.domain.service.AnnouncementTypeDomainService;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.interfaces.in.AnnouncementTypeIn;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 公告类型应用层服务实现类
 * @author SHK
 */
@Slf4j
@Service
public class AnnouncementTypeApplicationService {

    @Resource
    private BaseRepository<AnnouncementTypeEntity, Integer> baseRepository;

    @Resource
    private AnnouncementTypeDomainService announcementTypeDomainService;

    /**
     * 新增
     */
    public ResponseResult create(String name) {
        AnnouncementTypeAggregate aggregate = new AnnouncementTypeAggregate.Builder()
                .name(name)
                .createTime(new Date())
                .build();
        AnnouncementTypeEntity type = aggregate.aggregateToEntity();
        baseRepository.save(type);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 信息修改修改
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Integer id, String name) {
        AnnouncementTypeEntity type = baseRepository.findById(AnnouncementTypeEntity.class, id);
        AnnouncementTypeEntity newType = announcementTypeDomainService.updateTypeName(type, name);
        baseRepository.replace(newType);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 信息删除
     */
    public ResponseResult remove(Integer id) {
        announcementTypeDomainService.removeType(id);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 条件查询列表
     */
    public ResponseResult<List<AnnouncementTypeEntity>> findCondition(AnnouncementTypeIn in) {
        AnnouncementTypeDto dto = new AnnouncementTypeDto();
        BeanUtils.copyProperties(in, dto);
        List<AnnouncementTypeEntity> list = announcementTypeDomainService.findCondition(dto);
        return new ResponseResult<List<AnnouncementTypeEntity>>(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, list);
    }

    /**
     * 分页条件查询
     * @param in
     * @return
     */
    public ResponseResult<Page<AnnouncementTypeEntity>> findByCondition(AnnouncementTypeIn in) {
        AnnouncementTypeDto dto = new AnnouncementTypeDto();
        BeanUtils.copyProperties(in, dto);
        Page<AnnouncementTypeEntity> page = announcementTypeDomainService.findByCondition(dto);
        return new ResponseResult<Page<AnnouncementTypeEntity>>(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, page);
    }

}
