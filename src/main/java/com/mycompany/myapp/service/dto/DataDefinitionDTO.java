package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DataDefinition} entity.
 */
public class DataDefinitionDTO implements Serializable {

    private Long id;

    private Integer srcColId;

    private String defDescription;

    private String defSampleData;


    private Long colId;

    private Long typeId;

    private Long tblId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSrcColId() {
        return srcColId;
    }

    public void setSrcColId(Integer srcColId) {
        this.srcColId = srcColId;
    }

    public String getDefDescription() {
        return defDescription;
    }

    public void setDefDescription(String defDescription) {
        this.defDescription = defDescription;
    }

    public String getDefSampleData() {
        return defSampleData;
    }

    public void setDefSampleData(String defSampleData) {
        this.defSampleData = defSampleData;
    }

    public Long getColId() {
        return colId;
    }

    public void setColId(Long dsColumnsId) {
        this.colId = dsColumnsId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long dsColumnTypesId) {
        this.typeId = dsColumnTypesId;
    }

    public Long getTblId() {
        return tblId;
    }

    public void setTblId(Long dsTablesId) {
        this.tblId = dsTablesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataDefinitionDTO dataDefinitionDTO = (DataDefinitionDTO) o;
        if (dataDefinitionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataDefinitionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataDefinitionDTO{" +
            "id=" + getId() +
            ", srcColId=" + getSrcColId() +
            ", defDescription='" + getDefDescription() + "'" +
            ", defSampleData='" + getDefSampleData() + "'" +
            ", colId=" + getColId() +
            ", typeId=" + getTypeId() +
            ", tblId=" + getTblId() +
            "}";
    }
}
