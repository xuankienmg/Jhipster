package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EventCategories} entity.
 */
public class EventCategoriesDTO implements Serializable {

    private Long id;

    private String eventCatName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventCatName() {
        return eventCatName;
    }

    public void setEventCatName(String eventCatName) {
        this.eventCatName = eventCatName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventCategoriesDTO eventCategoriesDTO = (EventCategoriesDTO) o;
        if (eventCategoriesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventCategoriesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventCategoriesDTO{" +
            "id=" + getId() +
            ", eventCatName='" + getEventCatName() + "'" +
            "}";
    }
}
