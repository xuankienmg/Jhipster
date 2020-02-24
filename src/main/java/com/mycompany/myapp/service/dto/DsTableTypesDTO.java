package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsTableTypes} entity.
 */
public class DsTableTypesDTO implements Serializable {

    private Long id;

    private String tblTypeName;

    private String tblTypeDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTblTypeName() {
        return tblTypeName;
    }

    public void setTblTypeName(String tblTypeName) {
        this.tblTypeName = tblTypeName;
    }

    public String getTblTypeDescription() {
        return tblTypeDescription;
    }

    public void setTblTypeDescription(String tblTypeDescription) {
        this.tblTypeDescription = tblTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsTableTypesDTO dsTableTypesDTO = (DsTableTypesDTO) o;
        if (dsTableTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsTableTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsTableTypesDTO{" +
            "id=" + getId() +
            ", tblTypeName='" + getTblTypeName() + "'" +
            ", tblTypeDescription='" + getTblTypeDescription() + "'" +
            "}";
    }
}
