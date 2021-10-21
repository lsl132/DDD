package org.example.software.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.application.dto.AnnouncementLableDto;
import org.example.software.domain.aggregate.AnnouncementLableAggregate;
import org.example.software.domain.entity.AnnouncementLableEntity;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.infrastructure.repository.announcement.AnnouncementLableEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author SHK
 */
@Slf4j
@Service
public class AnnouncementLableDomainService {

    @Autowired
    private BaseRepository<AnnouncementLableEntity, Integer> baseRepository;

    @Autowired
    private AnnouncementLableEntityRepository repository;

    /** 更新标签名称 */
    public AnnouncementLableEntity updateLableName(AnnouncementLableEntity lable, String lableName) {
        if(lable == null) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "未匹配到标签数据");
        }
        AnnouncementLableAggregate aggregate = lable.entityToAggregate();
        aggregate.replaceName(lableName);
        return aggregate.aggregateToEntity();
    }

    @Transactional
    public void removeLable(Integer id) {
        // 查询出对象
        // 对象存在?
        // 标签是否在使用？
        // 判定后进行删除

        AnnouncementLableEntity lable = baseRepository.findById(AnnouncementLableEntity.class, id);
        if(lable == null) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "未匹配到标签数据");
        }
        baseRepository.remove(lable);


    }

    /** 分页查询 */
    public Page<AnnouncementLableEntity> findByCondition(AnnouncementLableDto dto, Pageable pageable) {

//        return repository.findAll(new Specification<AnnouncementLableEntity>() {
//            public Predicate toPredicate(Root<AnnouncementLableEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                return null;
//            }
//        });

        return null;
    }



}
