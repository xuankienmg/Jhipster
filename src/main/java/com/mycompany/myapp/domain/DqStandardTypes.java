package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqStandardTypes.
 */
@Entity
@Table(name = "dq_standard_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqStandardTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_type_name")
    private String stdTypeName;

    @Column(name = "std_type_description")
    private String stdTypeDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdTypeName() {
        return stdTypeName;
    }

    public DqStandardTypes stdTypeName(String stdTypeName) {
        this.stdTypeName = stdTypeName;
        return this;
    }

    public void setStdTypeName(String stdTypeName) {
        this.stdTypeName = stdTypeName;
    }

    public String getStdTypeDescription() {
        return stdTypeDescription;
    }

    public DqStandardTypes stdTypeDescription(String stdTypeDescription) {
        this.stdTypeDescription = stdTypeDescription;
        return this;
    }

    public void setStdTypeDescription(String stdTypeDescription) {
        this.stdTypeDescription = stdTypeDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqStandardTypes)) {
            return false;
        }
        return id != null && id.equals(((DqStandardTypes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqStandardTypes{" +
            "id=" + getId() +
            ", stdTypeName='" + getStdTypeName() + "'" +
            ", stdTypeDescription='" + getStdTypeDescription() + "'" +
            "}";
    }
}
