package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsTables} entity.
 */
public class DsTablesDTO implements Serializable {

    private Long id;

    private String tblName;

    private String tblDescription;


    private Long tblTypeId;

    private Long storeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public String getTblDescription() {
        return tblDescription;
    }

    public void setTblDescription(String tblDescription) {
        this.tblDescription = tblDescription;
    }

    public Long getTblTypeId() {
        return tblTypeId;
    }

    public void setTblTypeId(Long dsTableTypesId) {
        this.tblTypeId = dsTableTypesId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long dsStoresId) {
        this.storeId = dsStoresId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsTablesDTO dsTablesDTO = (DsTablesDTO) o;
        if (dsTablesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsTablesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsTablesDTO{" +
            "id=" + getId() +
            ", tblName='" + getTblName() + "'" +
            ", tblDescription='" + getTblDescription() + "'" +
            ", tblTypeId=" + getTblTypeId() +
            ", storeId=" + getStoreId() +
            "}";
    }
}
