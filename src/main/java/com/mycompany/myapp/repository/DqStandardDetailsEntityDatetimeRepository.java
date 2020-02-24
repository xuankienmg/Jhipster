package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardDetailsEntityDatetime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardDetailsEntityDatetimeRepository extends JpaRepository<DqStandardDetailsEntityDatetime, Long>, JpaSpecificationExecutor<DqStandardDetailsEntityDatetime> {

}
