package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardDetailsEntityDecimal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardDetailsEntityDecimalRepository extends JpaRepository<DqStandardDetailsEntityDecimal, Long>, JpaSpecificationExecutor<DqStandardDetailsEntityDecimal> {

}
