package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DsDbmsTypes.
 */
@Entity
@Table(name = "ds_dbms_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsDbmsTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dbms_type_name")
    private String dbmsTypeName;

    @Column(name = "dbsm_type_description")
    private String dbsmTypeDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbmsTypeName() {
        return dbmsTypeName;
    }

    public DsDbmsTypes dbmsTypeName(String dbmsTypeName) {
        this.dbmsTypeName = dbmsTypeName;
        return this;
    }

    public void setDbmsTypeName(String dbmsTypeName) {
        this.dbmsTypeName = dbmsTypeName;
    }

    public String getDbsmTypeDescription() {
        return dbsmTypeDescription;
    }

    public DsDbmsTypes dbsmTypeDescription(String dbsmTypeDescription) {
        this.dbsmTypeDescription = dbsmTypeDescription;
        return this;
    }

    public void setDbsmTypeDescription(String dbsmTypeDescription) {
        this.dbsmTypeDescription = dbsmTypeDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsDbmsTypes)) {
            return false;
        }
        return id != null && id.equals(((DsDbmsTypes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsDbmsTypes{" +
            "id=" + getId() +
            ", dbmsTypeName='" + getDbmsTypeName() + "'" +
            ", dbsmTypeDescription='" + getDbsmTypeDescription() + "'" +
            "}";
    }
}
