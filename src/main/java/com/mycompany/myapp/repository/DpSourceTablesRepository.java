package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DpSourceTables;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DpSourceTables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DpSourceTablesRepository extends JpaRepository<DpSourceTables, Long>, JpaSpecificationExecutor<DpSourceTables> {

}
