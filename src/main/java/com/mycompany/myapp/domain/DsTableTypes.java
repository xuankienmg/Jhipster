package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DsTableTypes.
 */
@Entity
@Table(name = "ds_table_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsTableTypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tbl_type_name")
    private String tblTypeName;

    @Column(name = "tbl_type_description")
    private String tblTypeDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTblTypeName() {
        return tblTypeName;
    }

    public DsTableTypes tblTypeName(String tblTypeName) {
        this.tblTypeName = tblTypeName;
        return this;
    }

    public void setTblTypeName(String tblTypeName) {
        this.tblTypeName = tblTypeName;
    }

    public String getTblTypeDescription() {
        return tblTypeDescription;
    }

    public DsTableTypes tblTypeDescription(String tblTypeDescription) {
        this.tblTypeDescription = tblTypeDescription;
        return this;
    }

    public void setTblTypeDescription(String tblTypeDescription) {
        this.tblTypeDescription = tblTypeDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsTableTypes)) {
            return false;
        }
        return id != null && id.equals(((DsTableTypes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsTableTypes{" +
            "id=" + getId() +
            ", tblTypeName='" + getTblTypeName() + "'" +
            ", tblTypeDescription='" + getTblTypeDescription() + "'" +
            "}";
    }
}
