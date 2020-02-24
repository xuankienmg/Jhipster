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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqRuleStatus} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqRuleStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-rule-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqRuleStatusCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter statusName;

    private StringFilter statusDescription;

    public DqRuleStatusCriteria() {
    }

    public DqRuleStatusCriteria(DqRuleStatusCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.statusName = other.statusName == null ? null : other.statusName.copy();
        this.statusDescription = other.statusDescription == null ? null : other.statusDescription.copy();
    }

    @Override
    public DqRuleStatusCriteria copy() {
        return new DqRuleStatusCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getStatusName() {
        return statusName;
    }

    public void setStatusName(StringFilter statusName) {
        this.statusName = statusName;
    }

    public StringFilter getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(StringFilter statusDescription) {
        this.statusDescription = statusDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqRuleStatusCriteria that = (DqRuleStatusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(statusName, that.statusName) &&
            Objects.equals(statusDescription, that.statusDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        statusName,
        statusDescription
        );
    }

    @Override
    public String toString() {
        return "DqRuleStatusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (statusName != null ? "statusName=" + statusName + ", " : "") +
                (statusDescription != null ? "statusDescription=" + statusDescription + ", " : "") +
            "}";
    }

}
