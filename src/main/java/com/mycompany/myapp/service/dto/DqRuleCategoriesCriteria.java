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
 * Criteria class for the {@link com.mycompany.myapp.domain.DqRuleCategories} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DqRuleCategoriesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dq-rule-categories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DqRuleCategoriesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter catName;

    private StringFilter catDescription;

    public DqRuleCategoriesCriteria() {
    }

    public DqRuleCategoriesCriteria(DqRuleCategoriesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.catName = other.catName == null ? null : other.catName.copy();
        this.catDescription = other.catDescription == null ? null : other.catDescription.copy();
    }

    @Override
    public DqRuleCategoriesCriteria copy() {
        return new DqRuleCategoriesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCatName() {
        return catName;
    }

    public void setCatName(StringFilter catName) {
        this.catName = catName;
    }

    public StringFilter getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(StringFilter catDescription) {
        this.catDescription = catDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DqRuleCategoriesCriteria that = (DqRuleCategoriesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(catName, that.catName) &&
            Objects.equals(catDescription, that.catDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        catName,
        catDescription
        );
    }

    @Override
    public String toString() {
        return "DqRuleCategoriesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (catName != null ? "catName=" + catName + ", " : "") +
                (catDescription != null ? "catDescription=" + catDescription + ", " : "") +
            "}";
    }

}
