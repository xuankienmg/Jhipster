package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardDetailsEntityInt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardDetailsEntityInt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardDetailsEntityIntRepository extends JpaRepository<DqStandardDetailsEntityInt, Long>, JpaSpecificationExecutor<DqStandardDetailsEntityInt> {

}
