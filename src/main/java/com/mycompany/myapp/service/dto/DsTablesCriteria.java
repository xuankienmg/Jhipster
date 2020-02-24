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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsTables} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsTablesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-tables?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsTablesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter tblName;

    private StringFilter tblDescription;

    private LongFilter tblTypeId;

    private LongFilter storeId;

    public DsTablesCriteria() {
    }

    public DsTablesCriteria(DsTablesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.tblName = other.tblName == null ? null : other.tblName.copy();
        this.tblDescription = other.tblDescription == null ? null : other.tblDescription.copy();
        this.tblTypeId = other.tblTypeId == null ? null : other.tblTypeId.copy();
        this.storeId = other.storeId == null ? null : other.storeId.copy();
    }

    @Override
    public DsTablesCriteria copy() {
        return new DsTablesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTblName() {
        return tblName;
    }

    public void setTblName(StringFilter tblName) {
        this.tblName = tblName;
    }

    public StringFilter getTblDescription() {
        return tblDescription;
    }

    public void setTblDescription(StringFilter tblDescription) {
        this.tblDescription = tblDescription;
    }

    public LongFilter getTblTypeId() {
        return tblTypeId;
    }

    public void setTblTypeId(LongFilter tblTypeId) {
        this.tblTypeId = tblTypeId;
    }

    public LongFilter getStoreId() {
        return storeId;
    }

    public void setStoreId(LongFilter storeId) {
        this.storeId = storeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsTablesCriteria that = (DsTablesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tblName, that.tblName) &&
            Objects.equals(tblDescription, that.tblDescription) &&
            Objects.equals(tblTypeId, that.tblTypeId) &&
            Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tblName,
        tblDescription,
        tblTypeId,
        storeId
        );
    }

    @Override
    public String toString() {
        return "DsTablesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tblName != null ? "tblName=" + tblName + ", " : "") +
                (tblDescription != null ? "tblDescription=" + tblDescription + ", " : "") +
                (tblTypeId != null ? "tblTypeId=" + tblTypeId + ", " : "") +
                (storeId != null ? "storeId=" + storeId + ", " : "") +
            "}";
    }

}
