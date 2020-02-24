package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DataFlows} entity.
 */
public class DataFlowsDTO implements Serializable {

    private Long id;

    private String flowName;

    private String flowDescription;

    private String source;

    private String destination;

    private String transformation;

    private Instant lSET;

    private Instant cET;


    private Long etlStatusId;

    private Long etlPkgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowDescription() {
        return flowDescription;
    }

    public void setFlowDescription(String flowDescription) {
        this.flowDescription = flowDescription;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public Instant getlSET() {
        return lSET;
    }

    public void setlSET(Instant lSET) {
        this.lSET = lSET;
    }

    public Instant getcET() {
        return cET;
    }

    public void setcET(Instant cET) {
        this.cET = cET;
    }

    public Long getEtlStatusId() {
        return etlStatusId;
    }

    public void setEtlStatusId(Long etlStatusId) {
        this.etlStatusId = etlStatusId;
    }

    public Long getEtlPkgId() {
        return etlPkgId;
    }

    public void setEtlPkgId(Long etlPackagesId) {
        this.etlPkgId = etlPackagesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DataFlowsDTO dataFlowsDTO = (DataFlowsDTO) o;
        if (dataFlowsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dataFlowsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DataFlowsDTO{" +
            "id=" + getId() +
            ", flowName='" + getFlowName() + "'" +
            ", flowDescription='" + getFlowDescription() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", transformation='" + getTransformation() + "'" +
            ", lSET='" + getlSET() + "'" +
            ", cET='" + getcET() + "'" +
            ", etlStatusId=" + getEtlStatusId() +
            ", etlPkgId=" + getEtlPkgId() +
            "}";
    }
}
