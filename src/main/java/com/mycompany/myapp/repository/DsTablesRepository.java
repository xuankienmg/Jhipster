package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsTables;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DsTables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DsTablesRepository extends JpaRepository<DsTables, Long>, JpaSpecificationExecutor<DsTables> {

}
