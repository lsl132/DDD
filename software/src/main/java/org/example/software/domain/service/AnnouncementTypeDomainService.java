package org.example.software.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.exception.GlobalException;
import org.example.common.exception.GlobalExceptionEnum;
import org.example.software.application.dto.AnnouncementLableDto;
import org.example.software.application.dto.AnnouncementTypeDto;
import org.example.software.domain.aggregate.AnnouncementLableAggregate;
import org.example.software.domain.aggregate.AnnouncementTypeAggregate;
import org.example.software.domain.entity.AnnouncementLableEntity;
import org.example.software.domain.entity.AnnouncementTypeEntity;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.infrastructure.repository.announcement.AnnouncementLableEntityRepository;
import org.example.software.infrastructure.repository.announcement.AnnouncementTypeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AnnouncementTypeDomainService {

    @Autowired
    private BaseRepository<AnnouncementTypeEntity, Integer> baseRepository;

    @Autowired
    private AnnouncementTypeEntityRepository repository;

    /** 更新标签名称 */
    public AnnouncementTypeEntity updateTypeName(AnnouncementTypeEntity type, String typeName) {
        if(type == null) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "未匹配到类型数据");
        }
        AnnouncementTypeAggregate aggregate = type.entityToAggregate();
        aggregate.replaceName(typeName);
        return aggregate.aggregateToEntity();
    }

    /**
     * 1、查询对象，判定对象存在校验ID参数；
     * 2、关联查询数据是否被引用？判定是否进行下一步；
     * 3、执行删除；
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeType(Integer id) {

        AnnouncementTypeEntity type = baseRepository.findById(AnnouncementTypeEntity.class, id);
        if(type == null) {
            throw new GlobalException(GlobalExceptionEnum.CHECK_NULL_ERROR.getCode(), "未匹配到标签数据");
        }
        baseRepository.remove(type);


    }

    /**
     * 条件查询
     * @param dto
     * @return
     */
    public List<AnnouncementTypeEntity> findCondition(final AnnouncementTypeDto dto) {
        return repository.findAll(new Specification<AnnouncementTypeEntity>() {

            public Predicate toPredicate(Root<AnnouncementTypeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (dto.getId() != null && dto.getId() > 0) {
                    predicates.add(cb.equal(root.get("id").as(Integer.class), dto.getId()));
                }
                if (dto.getName() != null && dto.getName().length() > 0) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
                }
                if (dto.getStart() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), dto.getStart()));
                }
                if ( dto.getEnd() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), dto.getEnd()));
                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });
    }



    /** 分页查询 */
    public Page<AnnouncementTypeEntity> findByCondition(final AnnouncementTypeDto dto) {
        //jpa是从0开始分页的
        Pageable pageable = PageRequest.of(dto.getPageNum() - 1, dto.getPageSize(), Sort.Direction.DESC, "createTime");

        return repository.findAll(new Specification<AnnouncementTypeEntity>() {

            public Predicate toPredicate(Root<AnnouncementTypeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (dto.getId() != null && dto.getId() > 0) {
                    predicates.add(cb.equal(root.get("id").as(Integer.class), dto.getId()));
                }
                if (dto.getName() != null && dto.getName().length() > 0) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
                }
                if (dto.getStart() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), dto.getStart()));
                }
                if ( dto.getEnd() != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), dto.getEnd()));
                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        },pageable);

    }



}
