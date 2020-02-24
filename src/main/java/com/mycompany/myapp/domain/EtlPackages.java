package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EtlPackages.
 */
@Entity
@Table(name = "etl_packages")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EtlPackages implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "etl_pkg_name")
    private String etlPkgName;

    @Column(name = "etl_pkg_description")
    private String etlPkgDescription;

    @Column(name = "etl_pkg_schedule")
    private String etlPkgSchedule;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtlPkgName() {
        return etlPkgName;
    }

    public EtlPackages etlPkgName(String etlPkgName) {
        this.etlPkgName = etlPkgName;
        return this;
    }

    public void setEtlPkgName(String etlPkgName) {
        this.etlPkgName = etlPkgName;
    }

    public String getEtlPkgDescription() {
        return etlPkgDescription;
    }

    public EtlPackages etlPkgDescription(String etlPkgDescription) {
        this.etlPkgDescription = etlPkgDescription;
        return this;
    }

    public void setEtlPkgDescription(String etlPkgDescription) {
        this.etlPkgDescription = etlPkgDescription;
    }

    public String getEtlPkgSchedule() {
        return etlPkgSchedule;
    }

    public EtlPackages etlPkgSchedule(String etlPkgSchedule) {
        this.etlPkgSchedule = etlPkgSchedule;
        return this;
    }

    public void setEtlPkgSchedule(String etlPkgSchedule) {
        this.etlPkgSchedule = etlPkgSchedule;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtlPackages)) {
            return false;
        }
        return id != null && id.equals(((EtlPackages) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtlPackages{" +
            "id=" + getId() +
            ", etlPkgName='" + getEtlPkgName() + "'" +
            ", etlPkgDescription='" + getEtlPkgDescription() + "'" +
            ", etlPkgSchedule='" + getEtlPkgSchedule() + "'" +
            "}";
    }
}
