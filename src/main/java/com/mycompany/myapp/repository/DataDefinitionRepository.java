package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DataDefinition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataDefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataDefinitionRepository extends JpaRepository<DataDefinition, Long>, JpaSpecificationExecutor<DataDefinition> {

}
