package org.example.software.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.example.software.application.dto.AnnouncementAssociatedUserDto;
import org.example.software.application.dto.AnnouncementDto;
import org.example.software.domain.entity.AnnouncementAssociatedUserEntity;
import org.example.software.domain.entity.AnnouncementAuthorEntity;
import org.example.software.domain.entity.AnnouncementEntity;
import org.example.software.infrastructure.enums.AnnouncementReleaseIsTopEnum;
import org.example.software.infrastructure.enums.AnnouncementReleaseStateEnum;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.infrastructure.repository.announcement.AnnouncementAuthorEntityRepository;
import org.example.software.infrastructure.repository.announcement.associated.AnnouncementAssociatedUserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
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
 */
@Slf4j
@Service
public class AnnouncementAssociatedUserDomainService {

    @Autowired
    private BaseRepository<AnnouncementAssociatedUserEntity, Long> baseRepository;

    @Autowired
    private AnnouncementAssociatedUserEntityRepository repository;

    /**
     *  公告发布，创建关联用户
     * @Describe TODO
     * @Author : SHK
     * @Date : 2021/10/27
     * @Param []
     * @Return void
     */
    public void releaseCreate(){

    }

    /**
     * 公告取消发布，将未看过的用户清除
     */
    public void unRelease(Long announcementId) {

    }

    /**
     * 列表分页查询
     * @Describe 提供了对公告关联用户信息列表分页查询
     * @Author : SHK
     * @Date : 2021/10/27
     * @Param [dto]
     * @Return org.springframework.data.domain.Page<org.example.software.domain.entity.AnnouncementAssociatedUserEntity>
     */
    public Page<AnnouncementAssociatedUserEntity> findByCondition(final AnnouncementAssociatedUserDto dto) {
        //jpa是从0开始分页的
        Pageable pageable = PageRequest.of(dto.getPageNum() - 1, dto.getPageSize(), Sort.Direction.DESC, "createTime");

        return repository.findAll(new Specification<AnnouncementAssociatedUserEntity>() {
            @Override
            public Predicate toPredicate(Root<AnnouncementAssociatedUserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(dto.getName())) {
                    predicates.add(cb.equal(root.get("title").as(String.class), "%" + dto.getName() + "%"));
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
