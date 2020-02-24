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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqRuleTypes} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqRuleTypesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-rule-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqRuleTypesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter typeName;

    private StringFilter typeDescription;

    public DqRuleTypesCriteria() {
    }

    public DqRuleTypesCriteria(DqRuleTypesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.typeName = other.typeName == null ? null : other.typeName.copy();
        this.typeDescription = other.typeDescription == null ? null : other.typeDescription.copy();
    }

    @Override
    public DqRuleTypesCriteria copy() {
        return new DqRuleTypesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTypeName() {
        return typeName;
    }

    public void setTypeName(StringFilter typeName) {
        this.typeName = typeName;
    }

    public StringFilter getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(StringFilter typeDescription) {
        this.typeDescription = typeDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqRuleTypesCriteria that = (DqRuleTypesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(typeName, that.typeName) &&
            Objects.equals(typeDescription, that.typeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        typeName,
        typeDescription
        );
    }

    @Override
    public String toString() {
        return "DqRuleTypesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (typeName != null ? "typeName=" + typeName + ", " : "") +
                (typeDescription != null ? "typeDescription=" + typeDescription + ", " : "") +
            "}";
    }

}
