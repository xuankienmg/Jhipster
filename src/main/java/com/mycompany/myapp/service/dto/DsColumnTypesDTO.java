package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsColumnTypes} entity.
 */
public class DsColumnTypesDTO implements Serializable {

    private Long id;

    private String colTypeName;

    private String colTypeDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColTypeName() {
        return colTypeName;
    }

    public void setColTypeName(String colTypeName) {
        this.colTypeName = colTypeName;
    }

    public String getColTypeDescription() {
        return colTypeDescription;
    }

    public void setColTypeDescription(String colTypeDescription) {
        this.colTypeDescription = colTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsColumnTypesDTO dsColumnTypesDTO = (DsColumnTypesDTO) o;
        if (dsColumnTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsColumnTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsColumnTypesDTO{" +
            "id=" + getId() +
            ", colTypeName='" + getColTypeName() + "'" +
            ", colTypeDescription='" + getColTypeDescription() + "'" +
            "}";
    }
}
