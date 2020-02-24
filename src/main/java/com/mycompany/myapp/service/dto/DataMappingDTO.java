package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DataMapping} entity.
 */
public class DataMappingDTO implements Serializable {

    private Long id;

    private Integer srcColId;


    private Long colId;

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

    public Long getColId() {
        return colId;
    }

    public void setColId(Long dsColumnsId) {
        this.colId = dsColumnsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataMappingDTO dataMappingDTO = (DataMappingDTO) o;
        if (dataMappingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataMappingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataMappingDTO{" +
            "id=" + getId() +
            ", srcColId=" + getSrcColId() +
            ", colId=" + getColId() +
            "}";
    }
}
