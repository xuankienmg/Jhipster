package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqRuleCategories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqRuleCategories entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqRuleCategoriesRepository extends JpaRepository<DqRuleCategories, Long>, JpaSpecificationExecutor<DqRuleCategories> {

}
