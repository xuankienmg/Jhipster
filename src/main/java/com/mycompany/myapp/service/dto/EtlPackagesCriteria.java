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
 * Criteria class for the {@link com.mycompany.myapp.domain.EtlPackages} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EtlPackagesResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /etl-packages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EtlPackagesCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter etlPkgName;

    private StringFilter etlPkgDescription;

    private StringFilter etlPkgSchedule;

    public EtlPackagesCriteria() {
    }

    public EtlPackagesCriteria(EtlPackagesCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.etlPkgName = other.etlPkgName == null ? null : other.etlPkgName.copy();
        this.etlPkgDescription = other.etlPkgDescription == null ? null : other.etlPkgDescription.copy();
        this.etlPkgSchedule = other.etlPkgSchedule == null ? null : other.etlPkgSchedule.copy();
    }

    @Override
    public EtlPackagesCriteria copy() {
        return new EtlPackagesCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEtlPkgName() {
        return etlPkgName;
    }

    public void setEtlPkgName(StringFilter etlPkgName) {
        this.etlPkgName = etlPkgName;
    }

    public StringFilter getEtlPkgDescription() {
        return etlPkgDescription;
    }

    public void setEtlPkgDescription(StringFilter etlPkgDescription) {
        this.etlPkgDescription = etlPkgDescription;
    }

    public StringFilter getEtlPkgSchedule() {
        return etlPkgSchedule;
    }

    public void setEtlPkgSchedule(StringFilter etlPkgSchedule) {
        this.etlPkgSchedule = etlPkgSchedule;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EtlPackagesCriteria that = (EtlPackagesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(etlPkgName, that.etlPkgName) &&
            Objects.equals(etlPkgDescription, that.etlPkgDescription) &&
            Objects.equals(etlPkgSchedule, that.etlPkgSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        etlPkgName,
        etlPkgDescription,
        etlPkgSchedule
        );
    }

    @Override
    public String toString() {
        return "EtlPackagesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (etlPkgName != null ? "etlPkgName=" + etlPkgName + ", " : "") +
                (etlPkgDescription != null ? "etlPkgDescription=" + etlPkgDescription + ", " : "") +
                (etlPkgSchedule != null ? "etlPkgSchedule=" + etlPkgSchedule + ", " : "") +
            "}";
    }

}
