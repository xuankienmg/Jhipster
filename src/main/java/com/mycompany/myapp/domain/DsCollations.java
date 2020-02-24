package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DsCollations.
 */
@Entity
@Table(name = "ds_collations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsCollations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "collation_name")
    private String collationName;

    @Column(name = "collation_description")
    private String collationDescription;

    @ManyToOne
    @JsonIgnoreProperties("dsCollations")
    private DsDbmsTypes dbmsType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollationName() {
        return collationName;
    }

    public DsCollations collationName(String collationName) {
        this.collationName = collationName;
        return this;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getCollationDescription() {
        return collationDescription;
    }

    public DsCollations collationDescription(String collationDescription) {
        this.collationDescription = collationDescription;
        return this;
    }

    public void setCollationDescription(String collationDescription) {
        this.collationDescription = collationDescription;
    }

    public DsDbmsTypes getDbmsType() {
        return dbmsType;
    }

    public DsCollations dbmsType(DsDbmsTypes dsDbmsTypes) {
        this.dbmsType = dsDbmsTypes;
        return this;
    }

    public void setDbmsType(DsDbmsTypes dsDbmsTypes) {
        this.dbmsType = dsDbmsTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsCollations)) {
            return false;
        }
        return id != null && id.equals(((DsCollations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsCollations{" +
            "id=" + getId() +
            ", collationName='" + getCollationName() + "'" +
            ", collationDescription='" + getCollationDescription() + "'" +
            "}";
    }
}
