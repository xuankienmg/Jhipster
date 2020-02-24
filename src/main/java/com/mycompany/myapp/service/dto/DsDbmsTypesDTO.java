package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsDbmsTypes} entity.
 */
public class DsDbmsTypesDTO implements Serializable {

    private Long id;

    private String dbmsTypeName;

    private String dbsmTypeDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbmsTypeName() {
        return dbmsTypeName;
    }

    public void setDbmsTypeName(String dbmsTypeName) {
        this.dbmsTypeName = dbmsTypeName;
    }

    public String getDbsmTypeDescription() {
        return dbsmTypeDescription;
    }

    public void setDbsmTypeDescription(String dbsmTypeDescription) {
        this.dbsmTypeDescription = dbsmTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsDbmsTypesDTO dsDbmsTypesDTO = (DsDbmsTypesDTO) o;
        if (dsDbmsTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsDbmsTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsDbmsTypesDTO{" +
            "id=" + getId() +
            ", dbmsTypeName='" + getDbmsTypeName() + "'" +
            ", dbsmTypeDescription='" + getDbsmTypeDescription() + "'" +
            "}";
    }
}
