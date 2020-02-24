package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqStandards;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DqStandards entity.
 */
@Repository
public interface DqStandardsRepository extends JpaRepository<DqStandards, Long>, JpaSpecificationExecutor<DqStandards> {

    @Query(value = "select distinct dqStandards from DqStandards dqStandards left join fetch dqStandards.rules",
        countQuery = "select count(distinct dqStandards) from DqStandards dqStandards")
    Page<DqStandards> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct dqStandards from DqStandards dqStandards left join fetch dqStandards.rules")
    List<DqStandards> findAllWithEagerRelationships();

    @Query("select dqStandards from DqStandards dqStandards left join fetch dqStandards.rules where dqStandards.id =:id")
    Optional<DqStandards> findOneWithEagerRelationships(@Param("id") Long id);

}
