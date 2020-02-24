package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EtlStatus.
 */
@Entity
@Table(name = "etl_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EtlStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "etl_status_name")
    private String etlStatusName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtlStatusName() {
        return etlStatusName;
    }

    public EtlStatus etlStatusName(String etlStatusName) {
        this.etlStatusName = etlStatusName;
        return this;
    }

    public void setEtlStatusName(String etlStatusName) {
        this.etlStatusName = etlStatusName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtlStatus)) {
            return false;
        }
        return id != null && id.equals(((EtlStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtlStatus{" +
            "id=" + getId() +
            ", etlStatusName='" + getEtlStatusName() + "'" +
            "}";
    }
}
