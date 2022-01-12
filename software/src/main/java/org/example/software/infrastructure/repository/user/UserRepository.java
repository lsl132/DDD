package org.example.software.infrastructure.repository.user;

import org.example.software.domain.aggregate.UserAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 用户仓储接口
 * @author SHK
 */
@Repository
public interface UserRepository extends JpaRepository<UserAggregate, Long>, JpaSpecificationExecutor<UserAggregate> {
}
