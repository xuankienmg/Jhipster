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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqStandards} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqStandardsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-standards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqStandardsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter stdName;

    private StringFilter stdDescription;

    private LongFilter stdTypeId;

    private LongFilter ruleId;

    public DqStandardsCriteria() {
    }

    public DqStandardsCriteria(DqStandardsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.stdName = other.stdName == null ? null : other.stdName.copy();
        this.stdDescription = other.stdDescription == null ? null : other.stdDescription.copy();
        this.stdTypeId = other.stdTypeId == null ? null : other.stdTypeId.copy();
        this.ruleId = other.ruleId == null ? null : other.ruleId.copy();
    }

    @Override
    public DqStandardsCriteria copy() {
        return new DqStandardsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getStdName() {
        return stdName;
    }

    public void setStdName(StringFilter stdName) {
        this.stdName = stdName;
    }

    public StringFilter getStdDescription() {
        return stdDescription;
    }

    public void setStdDescription(StringFilter stdDescription) {
        this.stdDescription = stdDescription;
    }

    public LongFilter getStdTypeId() {
        return stdTypeId;
    }

    public void setStdTypeId(LongFilter stdTypeId) {
        this.stdTypeId = stdTypeId;
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
        final DqStandardsCriteria that = (DqStandardsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stdName, that.stdName) &&
            Objects.equals(stdDescription, that.stdDescription) &&
            Objects.equals(stdTypeId, that.stdTypeId) &&
            Objects.equals(ruleId, that.ruleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stdName,
        stdDescription,
        stdTypeId,
        ruleId
        );
    }

    @Override
    public String toString() {
        return "DqStandardsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stdName != null ? "stdName=" + stdName + ", " : "") +
                (stdDescription != null ? "stdDescription=" + stdDescription + ", " : "") +
                (stdTypeId != null ? "stdTypeId=" + stdTypeId + ", " : "") +
                (ruleId != null ? "ruleId=" + ruleId + ", " : "") +
            "}";
    }

}
