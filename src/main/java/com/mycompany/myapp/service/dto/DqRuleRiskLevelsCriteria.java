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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqRuleRiskLevels} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqRuleRiskLevelsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-rule-risk-levels?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqRuleRiskLevelsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter riskName;

    private StringFilter riskDescription;

    public DqRuleRiskLevelsCriteria() {
    }

    public DqRuleRiskLevelsCriteria(DqRuleRiskLevelsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.riskName = other.riskName == null ? null : other.riskName.copy();
        this.riskDescription = other.riskDescription == null ? null : other.riskDescription.copy();
    }

    @Override
    public DqRuleRiskLevelsCriteria copy() {
        return new DqRuleRiskLevelsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getRiskName() {
        return riskName;
    }

    public void setRiskName(StringFilter riskName) {
        this.riskName = riskName;
    }

    public StringFilter getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(StringFilter riskDescription) {
        this.riskDescription = riskDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqRuleRiskLevelsCriteria that = (DqRuleRiskLevelsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(riskName, that.riskName) &&
            Objects.equals(riskDescription, that.riskDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        riskName,
        riskDescription
        );
    }

    @Override
    public String toString() {
        return "DqRuleRiskLevelsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (riskName != null ? "riskName=" + riskName + ", " : "") +
                (riskDescription != null ? "riskDescription=" + riskDescription + ", " : "") +
            "}";
    }

}
