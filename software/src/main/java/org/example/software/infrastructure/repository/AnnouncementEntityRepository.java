package org.example.software.infrastructure.repository;

import org.example.software.domain.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 公告仓储接口
 * @author SHK
 */
@Repository
public interface AnnouncementEntityRepository extends JpaRepository<AnnouncementEntity, Long>, JpaSpecificationExecutor<AnnouncementEntity> {
}
