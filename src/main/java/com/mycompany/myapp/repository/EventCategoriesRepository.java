package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EventCategories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventCategories entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventCategoriesRepository extends JpaRepository<EventCategories, Long>, JpaSpecificationExecutor<EventCategories> {

}
