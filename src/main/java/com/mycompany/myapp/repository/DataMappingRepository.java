package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DataMapping;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataMappingRepository extends JpaRepository<DataMapping, Long>, JpaSpecificationExecutor<DataMapping> {

}
