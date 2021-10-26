package org.example.software.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.application.dto.AnnouncementDto;
import org.example.software.domain.entity.AnnouncementEntity;
import org.example.software.infrastructure.enums.AnnouncementReleaseIsTopEnum;
import org.example.software.infrastructure.enums.AnnouncementReleaseStateEnum;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.infrastructure.repository.announcement.AnnouncementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SHK
 */
@Slf4j
@Service
public class AnnouncementDomainService {

    @Autowired
    private BaseRepository<AnnouncementEntity, Long> baseRepository;

    @Autowired
    private AnnouncementEntityRepository repository;


    /**
     * 删除公告
     * 1、检查状态是否发布中
     * 2、发送消息，后续消费者处理关联用户
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeAnnouncement(Long id) {
        AnnouncementEntity entity = baseRepository.findById(AnnouncementEntity.class, id);
        if(entity.getState() == AnnouncementReleaseStateEnum.RELEASED.getState()) {
            throw new GlobalException(GlobalExceptionEnum.PROHIBITION_CHANGE_STATE.getCode(), "当前状态不允许删除公告");
        }
        baseRepository.remove(entity);
    }


    /**
     * 分页查询
     * @param dto
     * @return
     */
    public Page<AnnouncementEntity> findByCondition(final AnnouncementDto dto) {
        //jpa是从0开始分页的
        Pageable pageable = PageRequest.of(dto.getPageNum() - 1, dto.getPageSize(), Sort.Direction.DESC, "createTime");

        return repository.findAll(new Specification<AnnouncementEntity>() {

            public Predicate toPredicate(Root<AnnouncementEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(dto.getTitle())) {
                    predicates.add(cb.equal(root.get("title").as(String.class), "%" + dto.getTitle() + "%"));
                }
                if (!StringUtils.isEmpty(dto.getIntro())) {
                    predicates.add(cb.like(root.get("intro").as(String.class), "%" + dto.getIntro() + "%"));
                }
                if (dto.getState() != null && AnnouncementReleaseStateEnum.getValue(dto.getState()) != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("state").as(Integer.class), dto.getState()));
                }
                if (dto.getType() != null ) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("type").as(Integer.class), dto.getType()));
                }
                if (dto.getIsTop() != null && AnnouncementReleaseIsTopEnum.getValue(dto.getState()) != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("isTop").as(Integer.class), dto.getIsTop()));
                }
                if (dto.getStartCreateTime() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), dto.getStartCreateTime()));
                }
                if ( dto.getEndCreateTime() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), dto.getEndCreateTime()));
                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        },pageable);

    }



}
