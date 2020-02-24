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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqRules} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqRulesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-rules?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqRulesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ruleName;

    private StringFilter ruleDescription;

    private LongFilter typeId;

    private LongFilter riskId;

    private LongFilter statusId;

    private LongFilter catId;

    private LongFilter actionId;

    private LongFilter stdId;

    private LongFilter colId;

    public DqRulesCriteria() {
    }

    public DqRulesCriteria(DqRulesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ruleName = other.ruleName == null ? null : other.ruleName.copy();
        this.ruleDescription = other.ruleDescription == null ? null : other.ruleDescription.copy();
        this.typeId = other.typeId == null ? null : other.typeId.copy();
        this.riskId = other.riskId == null ? null : other.riskId.copy();
        this.statusId = other.statusId == null ? null : other.statusId.copy();
        this.catId = other.catId == null ? null : other.catId.copy();
        this.actionId = other.actionId == null ? null : other.actionId.copy();
        this.stdId = other.stdId == null ? null : other.stdId.copy();
        this.colId = other.colId == null ? null : other.colId.copy();
    }

    @Override
    public DqRulesCriteria copy() {
        return new DqRulesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getRuleName() {
        return ruleName;
    }

    public void setRuleName(StringFilter ruleName) {
        this.ruleName = ruleName;
    }

    public StringFilter getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(StringFilter ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public LongFilter getTypeId() {
        return typeId;
    }

    public void setTypeId(LongFilter typeId) {
        this.typeId = typeId;
    }

    public LongFilter getRiskId() {
        return riskId;
    }

    public void setRiskId(LongFilter riskId) {
        this.riskId = riskId;
    }

    public LongFilter getStatusId() {
        return statusId;
    }

    public void setStatusId(LongFilter statusId) {
        this.statusId = statusId;
    }

    public LongFilter getCatId() {
        return catId;
    }

    public void setCatId(LongFilter catId) {
        this.catId = catId;
    }

    public LongFilter getActionId() {
        return actionId;
    }

    public void setActionId(LongFilter actionId) {
        this.actionId = actionId;
    }

    public LongFilter getStdId() {
        return stdId;
    }

    public void setStdId(LongFilter stdId) {
        this.stdId = stdId;
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
        final DqRulesCriteria that = (DqRulesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ruleName, that.ruleName) &&
            Objects.equals(ruleDescription, that.ruleDescription) &&
            Objects.equals(typeId, that.typeId) &&
            Objects.equals(riskId, that.riskId) &&
            Objects.equals(statusId, that.statusId) &&
            Objects.equals(catId, that.catId) &&
            Objects.equals(actionId, that.actionId) &&
            Objects.equals(stdId, that.stdId) &&
            Objects.equals(colId, that.colId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ruleName,
        ruleDescription,
        typeId,
        riskId,
        statusId,
        catId,
        actionId,
        stdId,
        colId
        );
    }

    @Override
    public String toString() {
        return "DqRulesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ruleName != null ? "ruleName=" + ruleName + ", " : "") +
                (ruleDescription != null ? "ruleDescription=" + ruleDescription + ", " : "") +
                (typeId != null ? "typeId=" + typeId + ", " : "") +
                (riskId != null ? "riskId=" + riskId + ", " : "") +
                (statusId != null ? "statusId=" + statusId + ", " : "") +
                (catId != null ? "catId=" + catId + ", " : "") +
                (actionId != null ? "actionId=" + actionId + ", " : "") +
                (stdId != null ? "stdId=" + stdId + ", " : "") +
                (colId != null ? "colId=" + colId + ", " : "") +
            "}";
    }

}
