package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.domain.aggregate.AnnouncementLableAggregate;
import org.example.software.domain.entity.AnnouncementLableEntity;
import org.example.software.domain.service.AnnouncementLableDomainService;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.interfaces.in.AnnouncementLableIn;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.example.software.application.dto.AnnouncementLableDto;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 公告标签应用层服务实现类
 * @author SHK
 */
@Slf4j
@Service
public class AnnouncementLableApplicationService {

    @Resource
    private BaseRepository<AnnouncementLableEntity, Integer> baseRepository;
    @Resource
    private AnnouncementLableDomainService announcementLableDomainService;

    /**
     * 新增
     */
    public ResponseResult create(String lableName) {
        AnnouncementLableAggregate aggregate = new AnnouncementLableAggregate.Builder()
                .name(lableName)
                .createTime(new Date())
                .build();
        AnnouncementLableEntity lable = aggregate.aggregateToEntity();
        baseRepository.save(lable);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 信息修改修改
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult update(Integer id, String lableName) {
        AnnouncementLableEntity lable = baseRepository.findById(AnnouncementLableEntity.class, id);
        AnnouncementLableEntity newLable = announcementLableDomainService.updateLableName(lable, lableName);
        baseRepository.replace(newLable);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 信息删除
     */
    public ResponseResult remove(Integer id) {
        announcementLableDomainService.removeLable(id);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 分页条件查询
     */
    public ResponseResult findByCondition(AnnouncementLableIn in) {
        AnnouncementLableDto dto = new AnnouncementLableDto();
        BeanUtils.copyProperties(in, dto);
        Page<AnnouncementLableEntity> page = announcementLableDomainService.findByCondition(dto);
        return new ResponseResult<Page<AnnouncementLableEntity>>(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, page);
    }

}
