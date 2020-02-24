package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardTypesRepository extends JpaRepository<DqStandardTypes, Long>, JpaSpecificationExecutor<DqStandardTypes> {

}
