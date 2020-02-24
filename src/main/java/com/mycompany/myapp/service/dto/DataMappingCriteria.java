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
 * Criteria class for the {@link com.mycompany.myapp.domain.DataMapping} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DataMappingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /data-mappings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DataMappingCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter srcColId;

    private LongFilter colId;

    public DataMappingCriteria() {
    }

    public DataMappingCriteria(DataMappingCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.srcColId = other.srcColId == null ? null : other.srcColId.copy();
        this.colId = other.colId == null ? null : other.colId.copy();
    }

    @Override
    public DataMappingCriteria copy() {
        return new DataMappingCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSrcColId() {
        return srcColId;
    }

    public void setSrcColId(IntegerFilter srcColId) {
        this.srcColId = srcColId;
    }

    public LongFilter getColId() {
        return colId;
    }

    public void setColId(LongFilter colId) {
        this.colId = colId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DataMappingCriteria that = (DataMappingCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(srcColId, that.srcColId) &&
            Objects.equals(colId, that.colId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        srcColId,
        colId
        );
    }

    @Override
    public String toString() {
        return "DataMappingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (srcColId != null ? "srcColId=" + srcColId + ", " : "") +
                (colId != null ? "colId=" + colId + ", " : "") +
            "}";
    }

}
