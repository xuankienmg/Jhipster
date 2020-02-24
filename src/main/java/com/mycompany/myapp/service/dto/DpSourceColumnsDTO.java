package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DpSourceColumns} entity.
 */
public class DpSourceColumnsDTO implements Serializable {

    private Long id;

    private Long uniqueValues;

    private String minValue;

    private String maxValue;

    private String averageValue;

    private String dpSourceColumnscol;

    private Long maxLength;

    private Long nulls;


    private Long tblId;

    private Long colId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniqueValues() {
        return uniqueValues;
    }

    public void setUniqueValues(Long uniqueValues) {
        this.uniqueValues = uniqueValues;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(String averageValue) {
        this.averageValue = averageValue;
    }

    public String getDpSourceColumnscol() {
        return dpSourceColumnscol;
    }

    public void setDpSourceColumnscol(String dpSourceColumnscol) {
        this.dpSourceColumnscol = dpSourceColumnscol;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public Long getNulls() {
        return nulls;
    }

    public void setNulls(Long nulls) {
        this.nulls = nulls;
    }

    public Long getTblId() {
        return tblId;
    }

    public void setTblId(Long dsTablesId) {
        this.tblId = dsTablesId;
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

        DpSourceColumnsDTO dpSourceColumnsDTO = (DpSourceColumnsDTO) o;
        if (dpSourceColumnsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dpSourceColumnsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DpSourceColumnsDTO{" +
            "id=" + getId() +
            ", uniqueValues=" + getUniqueValues() +
            ", minValue='" + getMinValue() + "'" +
            ", maxValue='" + getMaxValue() + "'" +
            ", averageValue='" + getAverageValue() + "'" +
            ", dpSourceColumnscol='" + getDpSourceColumnscol() + "'" +
            ", maxLength=" + getMaxLength() +
            ", nulls=" + getNulls() +
            ", tblId=" + getTblId() +
            ", colId=" + getColId() +
            "}";
    }
}
