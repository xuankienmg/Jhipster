package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.DpSourceTables} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DpSourceTablesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dp-source-tables?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DpSourceTablesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter rows;

    private IntegerFilter rowSize;

    private IntegerFilter columns;

    private BooleanFilter hasTimestamp;

    private LongFilter tblId;

    public DpSourceTablesCriteria() {
    }

    public DpSourceTablesCriteria(DpSourceTablesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.rows = other.rows == null ? null : other.rows.copy();
        this.rowSize = other.rowSize == null ? null : other.rowSize.copy();
        this.columns = other.columns == null ? null : other.columns.copy();
        this.hasTimestamp = other.hasTimestamp == null ? null : other.hasTimestamp.copy();
        this.tblId = other.tblId == null ? null : other.tblId.copy();
    }

    @Override
    public DpSourceTablesCriteria copy() {
        return new DpSourceTablesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getRows() {
        return rows;
    }

    public void setRows(LongFilter rows) {
        this.rows = rows;
    }

    public IntegerFilter getRowSize() {
        return rowSize;
    }

    public void setRowSize(IntegerFilter rowSize) {
        this.rowSize = rowSize;
    }

    public IntegerFilter getColumns() {
        return columns;
    }

    public void setColumns(IntegerFilter columns) {
        this.columns = columns;
    }

    public BooleanFilter getHasTimestamp() {
        return hasTimestamp;
    }

    public void setHasTimestamp(BooleanFilter hasTimestamp) {
        this.hasTimestamp = hasTimestamp;
    }

    public LongFilter getTblId() {
        return tblId;
    }

    public void setTblId(LongFilter tblId) {
        this.tblId = tblId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DpSourceTablesCriteria that = (DpSourceTablesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rows, that.rows) &&
            Objects.equals(rowSize, that.rowSize) &&
            Objects.equals(columns, that.columns) &&
            Objects.equals(hasTimestamp, that.hasTimestamp) &&
            Objects.equals(tblId, that.tblId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rows,
        rowSize,
        columns,
        hasTimestamp,
        tblId
        );
    }

    @Override
    public String toString() {
        return "DpSourceTablesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rows != null ? "rows=" + rows + ", " : "") +
                (rowSize != null ? "rowSize=" + rowSize + ", " : "") +
                (columns != null ? "columns=" + columns + ", " : "") +
                (hasTimestamp != null ? "hasTimestamp=" + hasTimestamp + ", " : "") +
                (tblId != null ? "tblId=" + tblId + ", " : "") +
            "}";
    }

}
