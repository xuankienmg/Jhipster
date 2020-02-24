package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqRuleTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqRuleTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqRuleTypesRepository extends JpaRepository<DqRuleTypes, Long>, JpaSpecificationExecutor<DqRuleTypes> {

}
