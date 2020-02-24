package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DqNotifications;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DqNotifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DqNotificationsRepository extends JpaRepository<DqNotifications, Long>, JpaSpecificationExecutor<DqNotifications> {

}
