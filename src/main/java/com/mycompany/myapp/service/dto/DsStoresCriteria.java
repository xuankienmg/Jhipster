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
 * Criteria class for the {@link com.mycompany.myapp.domain.DsStores} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DsStoresResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ds-stores?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DsStoresCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter storeName;

    private StringFilter storeDescription;

    private LongFilter storeSize;

    private LongFilter growthSize;

    private LongFilter storeDbmsTypeId;

    private LongFilter storeCollationId;

    public DsStoresCriteria() {
    }

    public DsStoresCriteria(DsStoresCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.storeName = other.storeName == null ? null : other.storeName.copy();
        this.storeDescription = other.storeDescription == null ? null : other.storeDescription.copy();
        this.storeSize = other.storeSize == null ? null : other.storeSize.copy();
        this.growthSize = other.growthSize == null ? null : other.growthSize.copy();
        this.storeDbmsTypeId = other.storeDbmsTypeId == null ? null : other.storeDbmsTypeId.copy();
        this.storeCollationId = other.storeCollationId == null ? null : other.storeCollationId.copy();
    }

    @Override
    public DsStoresCriteria copy() {
        return new DsStoresCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getStoreName() {
        return storeName;
    }

    public void setStoreName(StringFilter storeName) {
        this.storeName = storeName;
    }

    public StringFilter getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(StringFilter storeDescription) {
        this.storeDescription = storeDescription;
    }

    public LongFilter getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(LongFilter storeSize) {
        this.storeSize = storeSize;
    }

    public LongFilter getGrowthSize() {
        return growthSize;
    }

    public void setGrowthSize(LongFilter growthSize) {
        this.growthSize = growthSize;
    }

    public LongFilter getStoreDbmsTypeId() {
        return storeDbmsTypeId;
    }

    public void setStoreDbmsTypeId(LongFilter storeDbmsTypeId) {
        this.storeDbmsTypeId = storeDbmsTypeId;
    }

    public LongFilter getStoreCollationId() {
        return storeCollationId;
    }

    public void setStoreCollationId(LongFilter storeCollationId) {
        this.storeCollationId = storeCollationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DsStoresCriteria that = (DsStoresCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(storeName, that.storeName) &&
            Objects.equals(storeDescription, that.storeDescription) &&
            Objects.equals(storeSize, that.storeSize) &&
            Objects.equals(growthSize, that.growthSize) &&
            Objects.equals(storeDbmsTypeId, that.storeDbmsTypeId) &&
            Objects.equals(storeCollationId, that.storeCollationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        storeName,
        storeDescription,
        storeSize,
        growthSize,
        storeDbmsTypeId,
        storeCollationId
        );
    }

    @Override
    public String toString() {
        return "DsStoresCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (storeName != null ? "storeName=" + storeName + ", " : "") +
                (storeDescription != null ? "storeDescription=" + storeDescription + ", " : "") +
                (storeSize != null ? "storeSize=" + storeSize + ", " : "") +
                (growthSize != null ? "growthSize=" + growthSize + ", " : "") +
                (storeDbmsTypeId != null ? "storeDbmsTypeId=" + storeDbmsTypeId + ", " : "") +
                (storeCollationId != null ? "storeCollationId=" + storeCollationId + ", " : "") +
            "}";
    }

}
