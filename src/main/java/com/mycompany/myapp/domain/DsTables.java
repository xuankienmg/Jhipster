package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DsTables.
 */
@Entity
@Table(name = "ds_tables")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsTables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tbl_name")
    private String tblName;

    @Column(name = "tbl_description")
    private String tblDescription;

    @ManyToOne
    @JsonIgnoreProperties("dsTables")
    private DsTableTypes tblType;

    @ManyToOne
    @JsonIgnoreProperties("dsTables")
    private DsStores store;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTblName() {
        return tblName;
    }

    public DsTables tblName(String tblName) {
        this.tblName = tblName;
        return this;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public String getTblDescription() {
        return tblDescription;
    }

    public DsTables tblDescription(String tblDescription) {
        this.tblDescription = tblDescription;
        return this;
    }

    public void setTblDescription(String tblDescription) {
        this.tblDescription = tblDescription;
    }

    public DsTableTypes getTblType() {
        return tblType;
    }

    public DsTables tblType(DsTableTypes dsTableTypes) {
        this.tblType = dsTableTypes;
        return this;
    }

    public void setTblType(DsTableTypes dsTableTypes) {
        this.tblType = dsTableTypes;
    }

    public DsStores getStore() {
        return store;
    }

    public DsTables store(DsStores dsStores) {
        this.store = dsStores;
        return this;
    }

    public void setStore(DsStores dsStores) {
        this.store = dsStores;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsTables)) {
            return false;
        }
        return id != null && id.equals(((DsTables) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsTables{" +
            "id=" + getId() +
            ", tblName='" + getTblName() + "'" +
            ", tblDescription='" + getTblDescription() + "'" +
            "}";
    }
}
