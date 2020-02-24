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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqStandardDetailsEntityText} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqStandardDetailsEntityTextResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-standard-details-entity-texts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqStandardDetailsEntityTextCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter stdAttributeName;

    private LongFilter stdAttributeValue;

    private LongFilter stdId;

    public DqStandardDetailsEntityTextCriteria() {
    }

    public DqStandardDetailsEntityTextCriteria(DqStandardDetailsEntityTextCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.stdAttributeName = other.stdAttributeName == null ? null : other.stdAttributeName.copy();
        this.stdAttributeValue = other.stdAttributeValue == null ? null : other.stdAttributeValue.copy();
        this.stdId = other.stdId == null ? null : other.stdId.copy();
    }

    @Override
    public DqStandardDetailsEntityTextCriteria copy() {
        return new DqStandardDetailsEntityTextCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getStdAttributeName() {
        return stdAttributeName;
    }

    public void setStdAttributeName(StringFilter stdAttributeName) {
        this.stdAttributeName = stdAttributeName;
    }

    public LongFilter getStdAttributeValue() {
        return stdAttributeValue;
    }

    public void setStdAttributeValue(LongFilter stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
    }

    public LongFilter getStdId() {
        return stdId;
    }

    public void setStdId(LongFilter stdId) {
        this.stdId = stdId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqStandardDetailsEntityTextCriteria that = (DqStandardDetailsEntityTextCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stdAttributeName, that.stdAttributeName) &&
            Objects.equals(stdAttributeValue, that.stdAttributeValue) &&
            Objects.equals(stdId, that.stdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stdAttributeName,
        stdAttributeValue,
        stdId
        );
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityTextCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stdAttributeName != null ? "stdAttributeName=" + stdAttributeName + ", " : "") +
                (stdAttributeValue != null ? "stdAttributeValue=" + stdAttributeValue + ", " : "") +
                (stdId != null ? "stdId=" + stdId + ", " : "") +
            "}";
    }

}
