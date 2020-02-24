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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqRuleActions} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqRuleActionsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-rule-actions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqRuleActionsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter actionName;

    private StringFilter actionDescription;

    public DqRuleActionsCriteria() {
    }

    public DqRuleActionsCriteria(DqRuleActionsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.actionName = other.actionName == null ? null : other.actionName.copy();
        this.actionDescription = other.actionDescription == null ? null : other.actionDescription.copy();
    }

    @Override
    public DqRuleActionsCriteria copy() {
        return new DqRuleActionsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getActionName() {
        return actionName;
    }

    public void setActionName(StringFilter actionName) {
        this.actionName = actionName;
    }

    public StringFilter getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(StringFilter actionDescription) {
        this.actionDescription = actionDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqRuleActionsCriteria that = (DqRuleActionsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(actionName, that.actionName) &&
            Objects.equals(actionDescription, that.actionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        actionName,
        actionDescription
        );
    }

    @Override
    public String toString() {
        return "DqRuleActionsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (actionName != null ? "actionName=" + actionName + ", " : "") +
                (actionDescription != null ? "actionDescription=" + actionDescription + ", " : "") +
            "}";
    }

}
