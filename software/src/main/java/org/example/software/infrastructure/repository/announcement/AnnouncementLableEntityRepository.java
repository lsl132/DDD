package org.example.software.infrastructure.repository.announcement;

import org.example.software.domain.entity.AnnouncementLableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 公告标签仓储接口
 * @author SHK
 */
@Repository
public interface AnnouncementLableEntityRepository extends JpaRepository<AnnouncementLableEntity, Long>, JpaSpecificationExecutor<AnnouncementLableEntity> {
}
