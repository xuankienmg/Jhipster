package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandardDetailsEntityText;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqStandardDetailsEntityText entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqStandardDetailsEntityTextRepository extends JpaRepository<DqStandardDetailsEntityText, Long>, JpaSpecificationExecutor<DqStandardDetailsEntityText> {

}
