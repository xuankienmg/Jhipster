package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsStores;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DsStores entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DsStoresRepository extends JpaRepository<DsStores, Long>, JpaSpecificationExecutor<DsStores> {

}
