package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsColumnTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DsColumnTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DsColumnTypesRepository extends JpaRepository<DsColumnTypes, Long>, JpaSpecificationExecutor<DsColumnTypes> {

}
