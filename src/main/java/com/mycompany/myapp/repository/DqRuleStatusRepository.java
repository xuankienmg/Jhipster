package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqRuleStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqRuleStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqRuleStatusRepository extends JpaRepository<DqRuleStatus, Long>, JpaSpecificationExecutor<DqRuleStatus> {

}
