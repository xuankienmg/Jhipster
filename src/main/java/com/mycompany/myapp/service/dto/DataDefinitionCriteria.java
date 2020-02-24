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
 * Criteria class for the {@link com.mycompany.myapp.domain.DataDefinition} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DataDefinitionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /data-definitions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DataDefinitionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter srcColId;

    private StringFilter defDescription;

    private StringFilter defSampleData;

    private LongFilter colId;

    private LongFilter typeId;

    private LongFilter tblId;

    public DataDefinitionCriteria() {
    }

    public DataDefinitionCriteria(DataDefinitionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.srcColId = other.srcColId == null ? null : other.srcColId.copy();
        this.defDescription = other.defDescription == null ? null : other.defDescription.copy();
        this.defSampleData = other.defSampleData == null ? null : other.defSampleData.copy();
        this.colId = other.colId == null ? null : other.colId.copy();
        this.typeId = other.typeId == null ? null : other.typeId.copy();
        this.tblId = other.tblId == null ? null : other.tblId.copy();
    }

    @Override
    public DataDefinitionCriteria copy() {
        return new DataDefinitionCriteria(this);
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

    public StringFilter getDefDescription() {
        return defDescription;
    }

    public void setDefDescription(StringFilter defDescription) {
        this.defDescription = defDescription;
    }

    public StringFilter getDefSampleData() {
        return defSampleData;
    }

    public void setDefSampleData(StringFilter defSampleData) {
        this.defSampleData = defSampleData;
    }

    public LongFilter getColId() {
        return colId;
    }

    public void setColId(LongFilter colId) {
        this.colId = colId;
    }

    public LongFilter getTypeId() {
        return typeId;
    }

    public void setTypeId(LongFilter typeId) {
        this.typeId = typeId;
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
        final DataDefinitionCriteria that = (DataDefinitionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(srcColId, that.srcColId) &&
            Objects.equals(defDescription, that.defDescription) &&
            Objects.equals(defSampleData, that.defSampleData) &&
            Objects.equals(colId, that.colId) &&
            Objects.equals(typeId, that.typeId) &&
            Objects.equals(tblId, that.tblId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        srcColId,
        defDescription,
        defSampleData,
        colId,
        typeId,
        tblId
        );
    }

    @Override
    public String toString() {
        return "DataDefinitionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (srcColId != null ? "srcColId=" + srcColId + ", " : "") +
                (defDescription != null ? "defDescription=" + defDescription + ", " : "") +
                (defSampleData != null ? "defSampleData=" + defSampleData + ", " : "") +
                (colId != null ? "colId=" + colId + ", " : "") +
                (typeId != null ? "typeId=" + typeId + ", " : "") +
                (tblId != null ? "tblId=" + tblId + ", " : "") +
            "}";
    }

}
