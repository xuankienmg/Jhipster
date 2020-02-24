package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DsColumnTypes.
 */
@Entity
@Table(name = "ds_column_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsColumnTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "col_type_name")
    private String colTypeName;

    @Column(name = "col_type_description")
    private String colTypeDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColTypeName() {
        return colTypeName;
    }

    public DsColumnTypes colTypeName(String colTypeName) {
        this.colTypeName = colTypeName;
        return this;
    }

    public void setColTypeName(String colTypeName) {
        this.colTypeName = colTypeName;
    }

    public String getColTypeDescription() {
        return colTypeDescription;
    }

    public DsColumnTypes colTypeDescription(String colTypeDescription) {
        this.colTypeDescription = colTypeDescription;
        return this;
    }

    public void setColTypeDescription(String colTypeDescription) {
        this.colTypeDescription = colTypeDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsColumnTypes)) {
            return false;
        }
        return id != null && id.equals(((DsColumnTypes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsColumnTypes{" +
            "id=" + getId() +
            ", colTypeName='" + getColTypeName() + "'" +
            ", colTypeDescription='" + getColTypeDescription() + "'" +
            "}";
    }
}
