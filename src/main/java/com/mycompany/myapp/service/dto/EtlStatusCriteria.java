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
 * Criteria class for the {@link com.mycompany.myapp.domain.EtlStatus} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EtlStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /etl-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EtlStatusCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter etlStatusName;

    public EtlStatusCriteria() {
    }

    public EtlStatusCriteria(EtlStatusCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.etlStatusName = other.etlStatusName == null ? null : other.etlStatusName.copy();
    }

    @Override
    public EtlStatusCriteria copy() {
        return new EtlStatusCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEtlStatusName() {
        return etlStatusName;
    }

    public void setEtlStatusName(StringFilter etlStatusName) {
        this.etlStatusName = etlStatusName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EtlStatusCriteria that = (EtlStatusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(etlStatusName, that.etlStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        etlStatusName
        );
    }

    @Override
    public String toString() {
        return "EtlStatusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (etlStatusName != null ? "etlStatusName=" + etlStatusName + ", " : "") +
            "}";
    }

}
