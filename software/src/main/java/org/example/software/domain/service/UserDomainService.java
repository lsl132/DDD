package org.example.software.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.example.software.domain.aggregate.entity.UserAccount;
import org.example.software.infrastructure.repository.BaseRepository;
import org.example.software.infrastructure.repository.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SHK
 */
@Slf4j
@Service
public class UserDomainService {

    @Autowired
    private BaseRepository<UserAccount, Long> baseRepository;

    @Autowired
    private UserAccountRepository repository;




    /**
     * 分页查询
     * @param dto
     * @return
     */
//    public Page<UserAccount> findByCondition(final AnnouncementDto dto) {
//        //jpa是从0开始分页的
//        Pageable pageable = PageRequest.of(dto.getPageNum() - 1, dto.getPageSize(), Sort.Direction.DESC, "createTime");
//
//        return repository.findAll(new Specification<UserAccount>() {
//
//            @Override
//            public Predicate toPredicate(Root<UserAccount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<Predicate>();
//                if (!StringUtils.isEmpty(dto.getTitle())) {
//                    predicates.add(cb.equal(root.get("title").as(String.class), "%" + dto.getTitle() + "%"));
//                }
//                if (!StringUtils.isEmpty(dto.getIntro())) {
//                    predicates.add(cb.like(root.get("intro").as(String.class), "%" + dto.getIntro() + "%"));
//                }
//                if (dto.getState() != null && AnnouncementReleaseStateEnum.getValue(dto.getState()) != null) {
//                    predicates.add(cb.greaterThanOrEqualTo(root.get("state").as(Integer.class), dto.getState()));
//                }
//                if (dto.getType() != null ) {
//                    predicates.add(cb.greaterThanOrEqualTo(root.get("type").as(Integer.class), dto.getType()));
//                }
//                if (dto.getIsTop() != null && AnnouncementReleaseIsTopEnum.getValue(dto.getState()) != null) {
//                    predicates.add(cb.greaterThanOrEqualTo(root.get("isTop").as(Integer.class), dto.getIsTop()));
//                }
//                if (dto.getStartCreateTime() != null) {
//                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), dto.getStartCreateTime()));
//                }
//                if ( dto.getEndCreateTime() != null) {
//                    predicates.add(cb.lessThanOrEqualTo(root.get("createTime").as(Date.class), dto.getEndCreateTime()));
//                }
//
//                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
//            }
//        },pageable);
//
//    }



}
