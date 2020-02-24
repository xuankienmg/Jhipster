package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EventLogs;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventLogs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventLogsRepository extends JpaRepository<EventLogs, Long>, JpaSpecificationExecutor<EventLogs> {

}
