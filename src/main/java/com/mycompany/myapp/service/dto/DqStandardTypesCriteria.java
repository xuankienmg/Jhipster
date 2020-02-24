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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqStandardTypes} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqStandardTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-standard-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqStandardTypesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter stdTypeName;

    private StringFilter stdTypeDescription;

    public DqStandardTypesCriteria() {
    }

    public DqStandardTypesCriteria(DqStandardTypesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.stdTypeName = other.stdTypeName == null ? null : other.stdTypeName.copy();
        this.stdTypeDescription = other.stdTypeDescription == null ? null : other.stdTypeDescription.copy();
    }

    @Override
    public DqStandardTypesCriteria copy() {
        return new DqStandardTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getStdTypeName() {
        return stdTypeName;
    }

    public void setStdTypeName(StringFilter stdTypeName) {
        this.stdTypeName = stdTypeName;
    }

    public StringFilter getStdTypeDescription() {
        return stdTypeDescription;
    }

    public void setStdTypeDescription(StringFilter stdTypeDescription) {
        this.stdTypeDescription = stdTypeDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqStandardTypesCriteria that = (DqStandardTypesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stdTypeName, that.stdTypeName) &&
            Objects.equals(stdTypeDescription, that.stdTypeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stdTypeName,
        stdTypeDescription
        );
    }

    @Override
    public String toString() {
        return "DqStandardTypesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stdTypeName != null ? "stdTypeName=" + stdTypeName + ", " : "") +
                (stdTypeDescription != null ? "stdTypeDescription=" + stdTypeDescription + ", " : "") +
            "}";
    }

}
