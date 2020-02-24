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
 * Criteria class for the {@link com.mycompany.myapp.domain.EventCategories} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EventCategoriesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /event-categories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EventCategoriesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter eventCatName;

    public EventCategoriesCriteria() {
    }

    public EventCategoriesCriteria(EventCategoriesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.eventCatName = other.eventCatName == null ? null : other.eventCatName.copy();
    }

    @Override
    public EventCategoriesCriteria copy() {
        return new EventCategoriesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEventCatName() {
        return eventCatName;
    }

    public void setEventCatName(StringFilter eventCatName) {
        this.eventCatName = eventCatName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EventCategoriesCriteria that = (EventCategoriesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(eventCatName, that.eventCatName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        eventCatName
        );
    }

    @Override
    public String toString() {
        return "EventCategoriesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (eventCatName != null ? "eventCatName=" + eventCatName + ", " : "") +
            "}";
    }

}
