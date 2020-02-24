package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DataFlows.
 */
@Entity
@Table(name = "data_flows")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DataFlows implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flow_name")
    private String flowName;

    @Column(name = "flow_description")
    private String flowDescription;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @Column(name = "transformation")
    private String transformation;

    @Column(name = "l_set")
    private Instant lSET;

    @Column(name = "c_et")
    private Instant cET;

    @ManyToOne
    @JsonIgnoreProperties("dataFlows")
    private EtlStatus etlStatus;

    @ManyToOne
    @JsonIgnoreProperties("dataFlows")
    private EtlPackages etlPkg;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public DataFlows flowName(String flowName) {
        this.flowName = flowName;
        return this;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowDescription() {
        return flowDescription;
    }

    public DataFlows flowDescription(String flowDescription) {
        this.flowDescription = flowDescription;
        return this;
    }

    public void setFlowDescription(String flowDescription) {
        this.flowDescription = flowDescription;
    }

    public String getSource() {
        return source;
    }

    public DataFlows source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public DataFlows destination(String destination) {
        this.destination = destination;
        return this;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTransformation() {
        return transformation;
    }

    public DataFlows transformation(String transformation) {
        this.transformation = transformation;
        return this;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public Instant getlSET() {
        return lSET;
    }

    public DataFlows lSET(Instant lSET) {
        this.lSET = lSET;
        return this;
    }

    public void setlSET(Instant lSET) {
        this.lSET = lSET;
    }

    public Instant getcET() {
        return cET;
    }

    public DataFlows cET(Instant cET) {
        this.cET = cET;
        return this;
    }

    public void setcET(Instant cET) {
        this.cET = cET;
    }

    public EtlStatus getEtlStatus() {
        return etlStatus;
    }

    public DataFlows etlStatus(EtlStatus etlStatus) {
        this.etlStatus = etlStatus;
        return this;
    }

    public void setEtlStatus(EtlStatus etlStatus) {
        this.etlStatus = etlStatus;
    }

    public EtlPackages getEtlPkg() {
        return etlPkg;
    }

    public DataFlows etlPkg(EtlPackages etlPackages) {
        this.etlPkg = etlPackages;
        return this;
    }

    public void setEtlPkg(EtlPackages etlPackages) {
        this.etlPkg = etlPackages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataFlows)) {
            return false;
        }
        return id != null && id.equals(((DataFlows) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DataFlows{" +
            "id=" + getId() +
            ", flowName='" + getFlowName() + "'" +
            ", flowDescription='" + getFlowDescription() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", transformation='" + getTransformation() + "'" +
            ", lSET='" + getlSET() + "'" +
            ", cET='" + getcET() + "'" +
            "}";
    }
}
