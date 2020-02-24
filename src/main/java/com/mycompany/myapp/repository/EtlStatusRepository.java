package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EtlStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EtlStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtlStatusRepository extends JpaRepository<EtlStatus, Long>, JpaSpecificationExecutor<EtlStatus> {

}
