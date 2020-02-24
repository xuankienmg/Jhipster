package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqRuleActions;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqRuleActions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqRuleActionsRepository extends JpaRepository<DqRuleActions, Long>, JpaSpecificationExecutor<DqRuleActions> {

}
