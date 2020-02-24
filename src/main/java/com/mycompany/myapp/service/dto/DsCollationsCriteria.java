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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsCollations} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsCollationsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-collations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsCollationsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter collationName;

    private StringFilter collationDescription;

    private LongFilter dbmsTypeId;

    public DsCollationsCriteria() {
    }

    public DsCollationsCriteria(DsCollationsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.collationName = other.collationName == null ? null : other.collationName.copy();
        this.collationDescription = other.collationDescription == null ? null : other.collationDescription.copy();
        this.dbmsTypeId = other.dbmsTypeId == null ? null : other.dbmsTypeId.copy();
    }

    @Override
    public DsCollationsCriteria copy() {
        return new DsCollationsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCollationName() {
        return collationName;
    }

    public void setCollationName(StringFilter collationName) {
        this.collationName = collationName;
    }

    public StringFilter getCollationDescription() {
        return collationDescription;
    }

    public void setCollationDescription(StringFilter collationDescription) {
        this.collationDescription = collationDescription;
    }

    public LongFilter getDbmsTypeId() {
        return dbmsTypeId;
    }

    public void setDbmsTypeId(LongFilter dbmsTypeId) {
        this.dbmsTypeId = dbmsTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsCollationsCriteria that = (DsCollationsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(collationName, that.collationName) &&
            Objects.equals(collationDescription, that.collationDescription) &&
            Objects.equals(dbmsTypeId, that.dbmsTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        collationName,
        collationDescription,
        dbmsTypeId
        );
    }

    @Override
    public String toString() {
        return "DsCollationsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (collationName != null ? "collationName=" + collationName + ", " : "") +
                (collationDescription != null ? "collationDescription=" + collationDescription + ", " : "") +
                (dbmsTypeId != null ? "dbmsTypeId=" + dbmsTypeId + ", " : "") +
            "}";
    }

}
