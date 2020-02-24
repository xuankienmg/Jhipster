package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsCollations;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DsCollations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DsCollationsRepository extends JpaRepository<DsCollations, Long>, JpaSpecificationExecutor<DsCollations> {

}
