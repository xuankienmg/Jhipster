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
 * Criteria class for the {@link com.mycompany.myapp.domain.DpSourceColumns} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DpSourceColumnsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dp-source-columns?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DpSourceColumnsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter uniqueValues;

    private StringFilter minValue;

    private StringFilter maxValue;

    private StringFilter averageValue;

    private StringFilter dpSourceColumnscol;

    private LongFilter maxLength;

    private LongFilter nulls;

    private LongFilter tblId;

    private LongFilter colId;

    public DpSourceColumnsCriteria() {
    }

    public DpSourceColumnsCriteria(DpSourceColumnsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.uniqueValues = other.uniqueValues == null ? null : other.uniqueValues.copy();
        this.minValue = other.minValue == null ? null : other.minValue.copy();
        this.maxValue = other.maxValue == null ? null : other.maxValue.copy();
        this.averageValue = other.averageValue == null ? null : other.averageValue.copy();
        this.dpSourceColumnscol = other.dpSourceColumnscol == null ? null : other.dpSourceColumnscol.copy();
        this.maxLength = other.maxLength == null ? null : other.maxLength.copy();
        this.nulls = other.nulls == null ? null : other.nulls.copy();
        this.tblId = other.tblId == null ? null : other.tblId.copy();
        this.colId = other.colId == null ? null : other.colId.copy();
    }

    @Override
    public DpSourceColumnsCriteria copy() {
        return new DpSourceColumnsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getUniqueValues() {
        return uniqueValues;
    }

    public void setUniqueValues(LongFilter uniqueValues) {
        this.uniqueValues = uniqueValues;
    }

    public StringFilter getMinValue() {
        return minValue;
    }

    public void setMinValue(StringFilter minValue) {
        this.minValue = minValue;
    }

    public StringFilter getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(StringFilter maxValue) {
        this.maxValue = maxValue;
    }

    public StringFilter getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(StringFilter averageValue) {
        this.averageValue = averageValue;
    }

    public StringFilter getDpSourceColumnscol() {
        return dpSourceColumnscol;
    }

    public void setDpSourceColumnscol(StringFilter dpSourceColumnscol) {
        this.dpSourceColumnscol = dpSourceColumnscol;
    }

    public LongFilter getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(LongFilter maxLength) {
        this.maxLength = maxLength;
    }

    public LongFilter getNulls() {
        return nulls;
    }

    public void setNulls(LongFilter nulls) {
        this.nulls = nulls;
    }

    public LongFilter getTblId() {
        return tblId;
    }

    public void setTblId(LongFilter tblId) {
        this.tblId = tblId;
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
        final DpSourceColumnsCriteria that = (DpSourceColumnsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(uniqueValues, that.uniqueValues) &&
            Objects.equals(minValue, that.minValue) &&
            Objects.equals(maxValue, that.maxValue) &&
            Objects.equals(averageValue, that.averageValue) &&
            Objects.equals(dpSourceColumnscol, that.dpSourceColumnscol) &&
            Objects.equals(maxLength, that.maxLength) &&
            Objects.equals(nulls, that.nulls) &&
            Objects.equals(tblId, that.tblId) &&
            Objects.equals(colId, that.colId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        uniqueValues,
        minValue,
        maxValue,
        averageValue,
        dpSourceColumnscol,
        maxLength,
        nulls,
        tblId,
        colId
        );
    }

    @Override
    public String toString() {
        return "DpSourceColumnsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (uniqueValues != null ? "uniqueValues=" + uniqueValues + ", " : "") +
                (minValue != null ? "minValue=" + minValue + ", " : "") +
                (maxValue != null ? "maxValue=" + maxValue + ", " : "") +
                (averageValue != null ? "averageValue=" + averageValue + ", " : "") +
                (dpSourceColumnscol != null ? "dpSourceColumnscol=" + dpSourceColumnscol + ", " : "") +
                (maxLength != null ? "maxLength=" + maxLength + ", " : "") +
                (nulls != null ? "nulls=" + nulls + ", " : "") +
                (tblId != null ? "tblId=" + tblId + ", " : "") +
                (colId != null ? "colId=" + colId + ", " : "") +
            "}";
    }

}
