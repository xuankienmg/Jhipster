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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsTableTypes} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsTableTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-table-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsTableTypesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter tblTypeName;

    private StringFilter tblTypeDescription;

    public DsTableTypesCriteria() {
    }

    public DsTableTypesCriteria(DsTableTypesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.tblTypeName = other.tblTypeName == null ? null : other.tblTypeName.copy();
        this.tblTypeDescription = other.tblTypeDescription == null ? null : other.tblTypeDescription.copy();
    }

    @Override
    public DsTableTypesCriteria copy() {
        return new DsTableTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTblTypeName() {
        return tblTypeName;
    }

    public void setTblTypeName(StringFilter tblTypeName) {
        this.tblTypeName = tblTypeName;
    }

    public StringFilter getTblTypeDescription() {
        return tblTypeDescription;
    }

    public void setTblTypeDescription(StringFilter tblTypeDescription) {
        this.tblTypeDescription = tblTypeDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsTableTypesCriteria that = (DsTableTypesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tblTypeName, that.tblTypeName) &&
            Objects.equals(tblTypeDescription, that.tblTypeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tblTypeName,
        tblTypeDescription
        );
    }

    @Override
    public String toString() {
        return "DsTableTypesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tblTypeName != null ? "tblTypeName=" + tblTypeName + ", " : "") +
                (tblTypeDescription != null ? "tblTypeDescription=" + tblTypeDescription + ", " : "") +
            "}";
    }

}
