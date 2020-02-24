package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DpSourceTables.
 */
@Entity
@Table(name = "dp_source_tables")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DpSourceTables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rows")
    private Long rows;

    @Column(name = "row_size")
    private Integer rowSize;

    @Column(name = "columns")
    private Integer columns;

    @Column(name = "has_timestamp")
    private Boolean hasTimestamp;

    @ManyToOne
    @JsonIgnoreProperties("dpSourceTables")
    private DsTables tbl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRows() {
        return rows;
    }

    public DpSourceTables rows(Long rows) {
        this.rows = rows;
        return this;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public DpSourceTables rowSize(Integer rowSize) {
        this.rowSize = rowSize;
        return this;
    }

    public void setRowSize(Integer rowSize) {
        this.rowSize = rowSize;
    }

    public Integer getColumns() {
        return columns;
    }

    public DpSourceTables columns(Integer columns) {
        this.columns = columns;
        return this;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Boolean isHasTimestamp() {
        return hasTimestamp;
    }

    public DpSourceTables hasTimestamp(Boolean hasTimestamp) {
        this.hasTimestamp = hasTimestamp;
        return this;
    }

    public void setHasTimestamp(Boolean hasTimestamp) {
        this.hasTimestamp = hasTimestamp;
    }

    public DsTables getTbl() {
        return tbl;
    }

    public DpSourceTables tbl(DsTables dsTables) {
        this.tbl = dsTables;
        return this;
    }

    public void setTbl(DsTables dsTables) {
        this.tbl = dsTables;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DpSourceTables)) {
            return false;
        }
        return id != null && id.equals(((DpSourceTables) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DpSourceTables{" +
            "id=" + getId() +
            ", rows=" + getRows() +
            ", rowSize=" + getRowSize() +
            ", columns=" + getColumns() +
            ", hasTimestamp='" + isHasTimestamp() + "'" +
            "}";
    }
}
