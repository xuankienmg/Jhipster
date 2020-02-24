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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqNotifications} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqNotificationsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-notifications?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqNotificationsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter repicientId;

    private IntegerFilter repicientTypeId;

    private LongFilter ruleId;

    public DqNotificationsCriteria() {
    }

    public DqNotificationsCriteria(DqNotificationsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.repicientId = other.repicientId == null ? null : other.repicientId.copy();
        this.repicientTypeId = other.repicientTypeId == null ? null : other.repicientTypeId.copy();
        this.ruleId = other.ruleId == null ? null : other.ruleId.copy();
    }

    @Override
    public DqNotificationsCriteria copy() {
        return new DqNotificationsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRepicientId() {
        return repicientId;
    }

    public void setRepicientId(IntegerFilter repicientId) {
        this.repicientId = repicientId;
    }

    public IntegerFilter getRepicientTypeId() {
        return repicientTypeId;
    }

    public void setRepicientTypeId(IntegerFilter repicientTypeId) {
        this.repicientTypeId = repicientTypeId;
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
        final DqNotificationsCriteria that = (DqNotificationsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(repicientId, that.repicientId) &&
            Objects.equals(repicientTypeId, that.repicientTypeId) &&
            Objects.equals(ruleId, that.ruleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        repicientId,
        repicientTypeId,
        ruleId
        );
    }

    @Override
    public String toString() {
        return "DqNotificationsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (repicientId != null ? "repicientId=" + repicientId + ", " : "") +
                (repicientTypeId != null ? "repicientTypeId=" + repicientTypeId + ", " : "") +
                (ruleId != null ? "ruleId=" + ruleId + ", " : "") +
            "}";
    }

}
