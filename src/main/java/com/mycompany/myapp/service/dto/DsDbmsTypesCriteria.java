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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsDbmsTypes} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsDbmsTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-dbms-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsDbmsTypesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter dbmsTypeName;

    private StringFilter dbsmTypeDescription;

    public DsDbmsTypesCriteria() {
    }

    public DsDbmsTypesCriteria(DsDbmsTypesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.dbmsTypeName = other.dbmsTypeName == null ? null : other.dbmsTypeName.copy();
        this.dbsmTypeDescription = other.dbsmTypeDescription == null ? null : other.dbsmTypeDescription.copy();
    }

    @Override
    public DsDbmsTypesCriteria copy() {
        return new DsDbmsTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDbmsTypeName() {
        return dbmsTypeName;
    }

    public void setDbmsTypeName(StringFilter dbmsTypeName) {
        this.dbmsTypeName = dbmsTypeName;
    }

    public StringFilter getDbsmTypeDescription() {
        return dbsmTypeDescription;
    }

    public void setDbsmTypeDescription(StringFilter dbsmTypeDescription) {
        this.dbsmTypeDescription = dbsmTypeDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsDbmsTypesCriteria that = (DsDbmsTypesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dbmsTypeName, that.dbmsTypeName) &&
            Objects.equals(dbsmTypeDescription, that.dbsmTypeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dbmsTypeName,
        dbsmTypeDescription
        );
    }

    @Override
    public String toString() {
        return "DsDbmsTypesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dbmsTypeName != null ? "dbmsTypeName=" + dbmsTypeName + ", " : "") +
                (dbsmTypeDescription != null ? "dbsmTypeDescription=" + dbsmTypeDescription + ", " : "") +
            "}";
    }

}
