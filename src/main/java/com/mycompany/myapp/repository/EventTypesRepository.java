package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EventTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventTypesRepository extends JpaRepository<EventTypes, Long>, JpaSpecificationExecutor<EventTypes> {

}
