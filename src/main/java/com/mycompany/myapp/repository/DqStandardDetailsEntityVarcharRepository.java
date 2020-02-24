package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardDetailsEntityVarchar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardDetailsEntityVarcharRepository extends JpaRepository<DqStandardDetailsEntityVarchar, Long>, JpaSpecificationExecutor<DqStandardDetailsEntityVarchar> {

}
