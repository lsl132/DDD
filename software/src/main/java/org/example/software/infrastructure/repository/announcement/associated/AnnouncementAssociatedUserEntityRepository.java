package org.example.software.infrastructure.repository.announcement.associated;

import org.example.software.domain.entity.AnnouncementAssociatedUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementAssociatedUserEntityRepository extends JpaRepository<AnnouncementAssociatedUserEntity, Long>, JpaSpecificationExecutor<AnnouncementAssociatedUserEntity> {
}
