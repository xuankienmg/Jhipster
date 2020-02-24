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
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.DataFlows} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DataFlowsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /data-flows?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DataFlowsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter flowName;

    private StringFilter flowDescription;

    private StringFilter source;

    private StringFilter destination;

    private StringFilter transformation;

    private InstantFilter lSET;

    private InstantFilter cET;

    private LongFilter etlStatusId;

    private LongFilter etlPkgId;

    public DataFlowsCriteria() {
    }

    public DataFlowsCriteria(DataFlowsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.flowName = other.flowName == null ? null : other.flowName.copy();
        this.flowDescription = other.flowDescription == null ? null : other.flowDescription.copy();
        this.source = other.source == null ? null : other.source.copy();
        this.destination = other.destination == null ? null : other.destination.copy();
        this.transformation = other.transformation == null ? null : other.transformation.copy();
        this.lSET = other.lSET == null ? null : other.lSET.copy();
        this.cET = other.cET == null ? null : other.cET.copy();
        this.etlStatusId = other.etlStatusId == null ? null : other.etlStatusId.copy();
        this.etlPkgId = other.etlPkgId == null ? null : other.etlPkgId.copy();
    }

    @Override
    public DataFlowsCriteria copy() {
        return new DataFlowsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFlowName() {
        return flowName;
    }

    public void setFlowName(StringFilter flowName) {
        this.flowName = flowName;
    }

    public StringFilter getFlowDescription() {
        return flowDescription;
    }

    public void setFlowDescription(StringFilter flowDescription) {
        this.flowDescription = flowDescription;
    }

    public StringFilter getSource() {
        return source;
    }

    public void setSource(StringFilter source) {
        this.source = source;
    }

    public StringFilter getDestination() {
        return destination;
    }

    public void setDestination(StringFilter destination) {
        this.destination = destination;
    }

    public StringFilter getTransformation() {
        return transformation;
    }

    public void setTransformation(StringFilter transformation) {
        this.transformation = transformation;
    }

    public InstantFilter getlSET() {
        return lSET;
    }

    public void setlSET(InstantFilter lSET) {
        this.lSET = lSET;
    }

    public InstantFilter getcET() {
        return cET;
    }

    public void setcET(InstantFilter cET) {
        this.cET = cET;
    }

    public LongFilter getEtlStatusId() {
        return etlStatusId;
    }

    public void setEtlStatusId(LongFilter etlStatusId) {
        this.etlStatusId = etlStatusId;
    }

    public LongFilter getEtlPkgId() {
        return etlPkgId;
    }

    public void setEtlPkgId(LongFilter etlPkgId) {
        this.etlPkgId = etlPkgId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DataFlowsCriteria that = (DataFlowsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(flowName, that.flowName) &&
            Objects.equals(flowDescription, that.flowDescription) &&
            Objects.equals(source, that.source) &&
            Objects.equals(destination, that.destination) &&
            Objects.equals(transformation, that.transformation) &&
            Objects.equals(lSET, that.lSET) &&
            Objects.equals(cET, that.cET) &&
            Objects.equals(etlStatusId, that.etlStatusId) &&
            Objects.equals(etlPkgId, that.etlPkgId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        flowName,
        flowDescription,
        source,
        destination,
        transformation,
        lSET,
        cET,
        etlStatusId,
        etlPkgId
        );
    }

    @Override
    public String toString() {
        return "DataFlowsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (flowName != null ? "flowName=" + flowName + ", " : "") +
                (flowDescription != null ? "flowDescription=" + flowDescription + ", " : "") +
                (source != null ? "source=" + source + ", " : "") +
                (destination != null ? "destination=" + destination + ", " : "") +
                (transformation != null ? "transformation=" + transformation + ", " : "") +
                (lSET != null ? "lSET=" + lSET + ", " : "") +
                (cET != null ? "cET=" + cET + ", " : "") +
                (etlStatusId != null ? "etlStatusId=" + etlStatusId + ", " : "") +
                (etlPkgId != null ? "etlPkgId=" + etlPkgId + ", " : "") +
            "}";
    }

}
