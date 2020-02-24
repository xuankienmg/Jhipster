package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqRuleRiskLevels;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqRuleRiskLevels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqRuleRiskLevelsRepository extends JpaRepository<DqRuleRiskLevels, Long>, JpaSpecificationExecutor<DqRuleRiskLevels> {

}
