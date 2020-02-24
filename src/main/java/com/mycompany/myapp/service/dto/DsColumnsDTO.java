package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsColumns} entity.
 */
public class DsColumnsDTO implements Serializable {

    private Long id;

    private String colName;

    private String colDataType;

    private Boolean isPrimaryKey;

    private Boolean isForeignKey;

    private Boolean isIdentity;

    private Boolean isNull;


    private Long colTblId;

    private Set<DqRulesDTO> rules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColDataType() {
        return colDataType;
    }

    public void setColDataType(String colDataType) {
        this.colDataType = colDataType;
    }

    public Boolean isIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Boolean isIsForeignKey() {
        return isForeignKey;
    }

    public void setIsForeignKey(Boolean isForeignKey) {
        this.isForeignKey = isForeignKey;
    }

    public Boolean isIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(Boolean isIdentity) {
        this.isIdentity = isIdentity;
    }

    public Boolean isIsNull() {
        return isNull;
    }

    public void setIsNull(Boolean isNull) {
        this.isNull = isNull;
    }

    public Long getColTblId() {
        return colTblId;
    }

    public void setColTblId(Long dsTablesId) {
        this.colTblId = dsTablesId;
    }

    public Set<DqRulesDTO> getRules() {
        return rules;
    }

    public void setRules(Set<DqRulesDTO> dqRules) {
        this.rules = dqRules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsColumnsDTO dsColumnsDTO = (DsColumnsDTO) o;
        if (dsColumnsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsColumnsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsColumnsDTO{" +
            "id=" + getId() +
            ", colName='" + getColName() + "'" +
            ", colDataType='" + getColDataType() + "'" +
            ", isPrimaryKey='" + isIsPrimaryKey() + "'" +
            ", isForeignKey='" + isIsForeignKey() + "'" +
            ", isIdentity='" + isIsIdentity() + "'" +
            ", isNull='" + isIsNull() + "'" +
            ", colTblId=" + getColTblId() +
            "}";
    }
}
