package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqRules;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqRules entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqRulesRepository extends JpaRepository<DqRules, Long>, JpaSpecificationExecutor<DqRules> {

}
