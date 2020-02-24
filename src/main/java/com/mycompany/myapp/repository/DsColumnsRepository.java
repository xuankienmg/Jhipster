package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DsColumns;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DsColumns entity.
 */
@Repository
public interface DsColumnsRepository extends JpaRepository<DsColumns, Long>, JpaSpecificationExecutor<DsColumns> {

    @Query(value = "select distinct dsColumns from DsColumns dsColumns left join fetch dsColumns.rules",
        countQuery = "select count(distinct dsColumns) from DsColumns dsColumns")
    Page<DsColumns> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct dsColumns from DsColumns dsColumns left join fetch dsColumns.rules")
    List<DsColumns> findAllWithEagerRelationships();

    @Query("select dsColumns from DsColumns dsColumns left join fetch dsColumns.rules where dsColumns.id =:id")
    Optional<DsColumns> findOneWithEagerRelationships(@Param("id") Long id);

}
