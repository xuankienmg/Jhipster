package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DpSourceTables} entity.
 */
public class DpSourceTablesDTO implements Serializable {

    private Long id;

    private Long rows;

    private Integer rowSize;

    private Integer columns;

    private Boolean hasTimestamp;


    private Long tblId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public void setRowSize(Integer rowSize) {
        this.rowSize = rowSize;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Boolean isHasTimestamp() {
        return hasTimestamp;
    }

    public void setHasTimestamp(Boolean hasTimestamp) {
        this.hasTimestamp = hasTimestamp;
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

        DpSourceTablesDTO dpSourceTablesDTO = (DpSourceTablesDTO) o;
        if (dpSourceTablesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dpSourceTablesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DpSourceTablesDTO{" +
            "id=" + getId() +
            ", rows=" + getRows() +
            ", rowSize=" + getRowSize() +
            ", columns=" + getColumns() +
            ", hasTimestamp='" + isHasTimestamp() + "'" +
            ", tblId=" + getTblId() +
            "}";
    }
}
