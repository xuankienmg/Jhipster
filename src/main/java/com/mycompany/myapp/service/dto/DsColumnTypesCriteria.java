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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsColumnTypes} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsColumnTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-column-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsColumnTypesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter colTypeName;

    private StringFilter colTypeDescription;

    public DsColumnTypesCriteria() {
    }

    public DsColumnTypesCriteria(DsColumnTypesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.colTypeName = other.colTypeName == null ? null : other.colTypeName.copy();
        this.colTypeDescription = other.colTypeDescription == null ? null : other.colTypeDescription.copy();
    }

    @Override
    public DsColumnTypesCriteria copy() {
        return new DsColumnTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getColTypeName() {
        return colTypeName;
    }

    public void setColTypeName(StringFilter colTypeName) {
        this.colTypeName = colTypeName;
    }

    public StringFilter getColTypeDescription() {
        return colTypeDescription;
    }

    public void setColTypeDescription(StringFilter colTypeDescription) {
        this.colTypeDescription = colTypeDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsColumnTypesCriteria that = (DsColumnTypesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(colTypeName, that.colTypeName) &&
            Objects.equals(colTypeDescription, that.colTypeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        colTypeName,
        colTypeDescription
        );
    }

    @Override
    public String toString() {
        return "DsColumnTypesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (colTypeName != null ? "colTypeName=" + colTypeName + ", " : "") +
                (colTypeDescription != null ? "colTypeDescription=" + colTypeDescription + ", " : "") +
            "}";
    }

}
