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
import java.util.List;

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
    public Page<AnnouncementLableEntity> findByCondition(final AnnouncementLableDto dto) {
        //jpa是从0开始分页的
        Pageable pageable = PageRequest.of(dto.getPageNum() - 1, dto.getPageSize(), Sort.Direction.DESC, "createTime");

        return repository.findAll(new Specification<AnnouncementLableEntity>() {

            public Predicate toPredicate(Root<AnnouncementLableEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (dto.getId() != null && dto.getId() > 0) {
                    predicates.add(cb.equal(root.get("id").as(String.class), dto.getId().toString()));
                }
                if (dto.getName() != null && dto.getName().length() > 0) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + dto.getName() + "%"));
                }
//                if (dto.getStart() != null && dto.getEnd() != null) {
//                    predicates.add(cb.between(root.get("createTime").as(String.class), dto.getStart(), dto.getEnd()));
//                }

                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        },pageable);

    }



}
