package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsCollations} entity.
 */
public class DsCollationsDTO implements Serializable {

    private Long id;

    private String collationName;

    private String collationDescription;


    private Long dbmsTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getCollationDescription() {
        return collationDescription;
    }

    public void setCollationDescription(String collationDescription) {
        this.collationDescription = collationDescription;
    }

    public Long getDbmsTypeId() {
        return dbmsTypeId;
    }

    public void setDbmsTypeId(Long dsDbmsTypesId) {
        this.dbmsTypeId = dsDbmsTypesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsCollationsDTO dsCollationsDTO = (DsCollationsDTO) o;
        if (dsCollationsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsCollationsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsCollationsDTO{" +
            "id=" + getId() +
            ", collationName='" + getCollationName() + "'" +
            ", collationDescription='" + getCollationDescription() + "'" +
            ", dbmsTypeId=" + getDbmsTypeId() +
            "}";
    }
}
