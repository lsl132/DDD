package org.example.software.application.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.example.software.application.dto.AnnouncementDto;
import org.example.software.application.event.AnnouncementReleaseEvent;
import org.example.software.application.event.AnnouncementUnReleaseEvent;
import org.example.software.domain.aggregate.AnnouncementAggregate;
import org.example.software.domain.aggregate.vo.AnnouncementInfoVO;
import org.example.software.domain.entity.AnnouncementEntity;
import org.example.software.domain.service.AnnouncementDomainService;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.interfaces.in.AnnouncementIn;
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
public class AnnouncementApplicationService {

    @Resource
    private BaseRepository<AnnouncementEntity, Long> baseRepository;
    @Resource
    private AnnouncementDomainService announcementDomainService;
    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 初始公告新增
     */
    public ResponseResult initCreate(AnnouncementIn in) {
        //填充公告信息
        AnnouncementInfoVO infoVO = new AnnouncementInfoVO.Builder()
                .title(in.getTitle())
                .intro(in.getIntro())
                .labelNames(in.getLableNames())
                .author(in.getAuthor())
                .type(in.getType())
                .content(in.getContent())
                .buil();

        //组装公告聚合根
        AnnouncementAggregate aggregate = new AnnouncementAggregate.Builder()
                .createTime(new Date())
                .announcementInfo(infoVO)
                .build();
        //转换成实体
        AnnouncementEntity entity = aggregate.aggregateToEntity();
        //存储实体
        baseRepository.save(entity);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, entity.getId());
    }

    /**
     * 公告信息修改修改
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateInfo(AnnouncementIn in) {
        AnnouncementEntity entity = baseRepository.findById(AnnouncementEntity.class, in.getId());
        AnnouncementAggregate aggregate = entity.entityToAggregate();

        aggregate.getAnnouncementInfo().replaceTitle(in.getTitle());
        aggregate.getAnnouncementInfo().replaceIntro(in.getIntro());
        aggregate.getAnnouncementInfo().replaceType(in.getType());
        aggregate.getAnnouncementInfo().replaceLabelNames(in.getLableNames());
        aggregate.getAnnouncementInfo().replaceContent(in.getContent());
        aggregate.getAnnouncementInfo().replaceAuthor(in.getAuthor());

        AnnouncementEntity newEntity = aggregate.aggregateToEntity();
        baseRepository.replace(newEntity);

        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 发布公告
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult release(AnnouncementIn in) {
        AnnouncementEntity entity = baseRepository.findById(AnnouncementEntity.class, in.getId());
        AnnouncementAggregate aggregate = entity.entityToAggregate();

        //发布状态
        aggregate.getAnnouncementRelease().release();
        //置顶替换
        aggregate.getAnnouncementRelease().replaceTop(in.getIsTop());
        // 提醒替换
        aggregate.getAnnouncementRelease().replaceRemind(in.getRemindType(),in.getRemindContinueSeconds(),in.getRemindValidDayNum());
        //内部状态替换
        aggregate.getAnnouncementRelease().replaceInteriorScope(in.getInteriorScopeState(),in.getInteriorAdminRoleEntities());
        //租户状态替换
        aggregate.getAnnouncementRelease().replaceTenantScope(in.getTenantScopeState(), in.getReleaseTenantEntities(), in.getTenantSuperAdminState());
        //有效时间段
        aggregate.getAnnouncementRelease().replaceVaildTime(in.getValidStart(), in.getValidEnd());

        AnnouncementEntity newEntity = aggregate.aggregateToEntity();
        baseRepository.replace(newEntity);

        //发布消息，由消费者查找绑定关联用户...
        applicationContext.publishEvent(new AnnouncementReleaseEvent(applicationContext, aggregate));

        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 撤回发布
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult unRelease(Long id) {
        AnnouncementEntity entity = baseRepository.findById(AnnouncementEntity.class, id);
        AnnouncementAggregate aggregate = entity.entityToAggregate();
        //撤销发布
        aggregate.getAnnouncementRelease().cancelRelease();

        AnnouncementEntity newEntity = aggregate.aggregateToEntity();
        baseRepository.replace(newEntity);

        //发布消息，由消费者处理未读取过的关联用户...
        applicationContext.publishEvent(new AnnouncementUnReleaseEvent(applicationContext, aggregate));

        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }

    /**
     * 查询公告详情
     */
    public ResponseResult<AnnouncementEntity> details(Long id) {
        AnnouncementEntity entity = baseRepository.findById(AnnouncementEntity.class, id);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, entity);
    }


    /**
     * 删除公告
     */
    public ResponseResult delete(Long id) {
        announcementDomainService.removeAnnouncement(id);
        return new ResponseResult(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG);
    }


    /**
     * 分页查询公告列表
     */
    public ResponseResult findByCondition(AnnouncementIn in) {
        AnnouncementDto dto = new AnnouncementDto();
        BeanUtils.copyProperties(in, dto);
        Page<AnnouncementEntity> page = announcementDomainService.findByCondition(dto);
        return new ResponseResult<Page<AnnouncementEntity>>(ResponseResult.SUCCESS_CODE, ResponseResult.SUCCESS_MSG, page);
    }


}
