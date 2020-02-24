package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DataFlows;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataFlows entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataFlowsRepository extends JpaRepository<DataFlows, Long>, JpaSpecificationExecutor<DataFlows> {

}
