package org.example.software.infrastructure.repository.announcement;

import org.example.software.domain.entity.AnnouncementAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 公告发布者仓储接口
 * @author SHK
 */
@Repository
public interface AnnouncementAuthorEntityRepository extends JpaRepository<AnnouncementAuthorEntity, Integer>, JpaSpecificationExecutor<AnnouncementAuthorEntity> {
}
