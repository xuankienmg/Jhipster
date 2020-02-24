package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardDetailsEntityTime;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardDetailsEntityTime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardDetailsEntityTimeRepository extends JpaRepository<DqStandardDetailsEntityTime, Long>, JpaSpecificationExecutor<DqStandardDetailsEntityTime> {

}
