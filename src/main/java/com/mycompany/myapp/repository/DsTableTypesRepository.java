package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsTableTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DsTableTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DsTableTypesRepository extends JpaRepository<DsTableTypes, Long>, JpaSpecificationExecutor<DsTableTypes> {

}
