package org.example.software.infrastructure.repository.announcement;

import org.example.software.domain.entity.AnnouncementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 公告类型仓储接口
 * @author SHK
 */
@Repository
public interface AnnouncementTypeEntityRepository extends JpaRepository<AnnouncementTypeEntity, Integer>, JpaSpecificationExecutor<AnnouncementTypeEntity> {
}
