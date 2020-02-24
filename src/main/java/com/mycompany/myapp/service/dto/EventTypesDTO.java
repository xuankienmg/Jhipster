package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EventTypes} entity.
 */
public class EventTypesDTO implements Serializable {

    private Long id;

    private String eventTypeName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventTypesDTO eventTypesDTO = (EventTypesDTO) o;
        if (eventTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventTypesDTO{" +
            "id=" + getId() +
            ", eventTypeName='" + getEventTypeName() + "'" +
            "}";
    }
}
