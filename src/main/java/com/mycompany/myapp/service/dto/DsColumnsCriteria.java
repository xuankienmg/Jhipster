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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsColumns} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsColumnsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-columns?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsColumnsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter colName;

    private StringFilter colDataType;

    private BooleanFilter isPrimaryKey;

    private BooleanFilter isForeignKey;

    private BooleanFilter isIdentity;

    private BooleanFilter isNull;

    private LongFilter colTblId;

    private LongFilter ruleId;

    public DsColumnsCriteria() {
    }

    public DsColumnsCriteria(DsColumnsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.colName = other.colName == null ? null : other.colName.copy();
        this.colDataType = other.colDataType == null ? null : other.colDataType.copy();
        this.isPrimaryKey = other.isPrimaryKey == null ? null : other.isPrimaryKey.copy();
        this.isForeignKey = other.isForeignKey == null ? null : other.isForeignKey.copy();
        this.isIdentity = other.isIdentity == null ? null : other.isIdentity.copy();
        this.isNull = other.isNull == null ? null : other.isNull.copy();
        this.colTblId = other.colTblId == null ? null : other.colTblId.copy();
        this.ruleId = other.ruleId == null ? null : other.ruleId.copy();
    }

    @Override
    public DsColumnsCriteria copy() {
        return new DsColumnsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getColName() {
        return colName;
    }

    public void setColName(StringFilter colName) {
        this.colName = colName;
    }

    public StringFilter getColDataType() {
        return colDataType;
    }

    public void setColDataType(StringFilter colDataType) {
        this.colDataType = colDataType;
    }

    public BooleanFilter getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(BooleanFilter isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public BooleanFilter getIsForeignKey() {
        return isForeignKey;
    }

    public void setIsForeignKey(BooleanFilter isForeignKey) {
        this.isForeignKey = isForeignKey;
    }

    public BooleanFilter getIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(BooleanFilter isIdentity) {
        this.isIdentity = isIdentity;
    }

    public BooleanFilter getIsNull() {
        return isNull;
    }

    public void setIsNull(BooleanFilter isNull) {
        this.isNull = isNull;
    }

    public LongFilter getColTblId() {
        return colTblId;
    }

    public void setColTblId(LongFilter colTblId) {
        this.colTblId = colTblId;
    }

    public LongFilter getRuleId() {
        return ruleId;
    }

    public void setRuleId(LongFilter ruleId) {
        this.ruleId = ruleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsColumnsCriteria that = (DsColumnsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(colName, that.colName) &&
            Objects.equals(colDataType, that.colDataType) &&
            Objects.equals(isPrimaryKey, that.isPrimaryKey) &&
            Objects.equals(isForeignKey, that.isForeignKey) &&
            Objects.equals(isIdentity, that.isIdentity) &&
            Objects.equals(isNull, that.isNull) &&
            Objects.equals(colTblId, that.colTblId) &&
            Objects.equals(ruleId, that.ruleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        colName,
        colDataType,
        isPrimaryKey,
        isForeignKey,
        isIdentity,
        isNull,
        colTblId,
        ruleId
        );
    }

    @Override
    public String toString() {
        return "DsColumnsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (colName != null ? "colName=" + colName + ", " : "") +
                (colDataType != null ? "colDataType=" + colDataType + ", " : "") +
                (isPrimaryKey != null ? "isPrimaryKey=" + isPrimaryKey + ", " : "") +
                (isForeignKey != null ? "isForeignKey=" + isForeignKey + ", " : "") +
                (isIdentity != null ? "isIdentity=" + isIdentity + ", " : "") +
                (isNull != null ? "isNull=" + isNull + ", " : "") +
                (colTblId != null ? "colTblId=" + colTblId + ", " : "") +
                (ruleId != null ? "ruleId=" + ruleId + ", " : "") +
            "}";
    }

}
