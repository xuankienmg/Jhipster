package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DpSourceColumns;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DpSourceColumns entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DpSourceColumnsRepository extends JpaRepository<DpSourceColumns, Long>, JpaSpecificationExecutor<DpSourceColumns> {

}
