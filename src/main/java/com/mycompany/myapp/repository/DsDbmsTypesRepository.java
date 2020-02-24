package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsDbmsTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DsDbmsTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DsDbmsTypesRepository extends JpaRepository<DsDbmsTypes, Long>, JpaSpecificationExecutor<DsDbmsTypes> {

}
