package org.example.software.infrastructure.repository.user;

import org.example.software.domain.aggregate.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 用户帐号仓储接口
 * @author SHK
 */
@Repository
public interface UserAccountEntityRepository extends JpaRepository<UserAccount, Long>, JpaSpecificationExecutor<UserAccount> {
}
