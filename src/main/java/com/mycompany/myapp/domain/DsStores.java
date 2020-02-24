package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DsStores.
 */
@Entity
@Table(name = "ds_stores")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DsStores implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_description")
    private String storeDescription;

    @Column(name = "store_size")
    private Long storeSize;

    @Column(name = "growth_size")
    private Long growthSize;

    @ManyToOne
    @JsonIgnoreProperties("dsStores")
    private DsDbmsTypes storeDbmsType;

    @ManyToOne
    @JsonIgnoreProperties("dsStores")
    private DsCollations storeCollation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public DsStores storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public DsStores storeDescription(String storeDescription) {
        this.storeDescription = storeDescription;
        return this;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public Long getStoreSize() {
        return storeSize;
    }

    public DsStores storeSize(Long storeSize) {
        this.storeSize = storeSize;
        return this;
    }

    public void setStoreSize(Long storeSize) {
        this.storeSize = storeSize;
    }

    public Long getGrowthSize() {
        return growthSize;
    }

    public DsStores growthSize(Long growthSize) {
        this.growthSize = growthSize;
        return this;
    }

    public void setGrowthSize(Long growthSize) {
        this.growthSize = growthSize;
    }

    public DsDbmsTypes getStoreDbmsType() {
        return storeDbmsType;
    }

    public DsStores storeDbmsType(DsDbmsTypes dsDbmsTypes) {
        this.storeDbmsType = dsDbmsTypes;
        return this;
    }

    public void setStoreDbmsType(DsDbmsTypes dsDbmsTypes) {
        this.storeDbmsType = dsDbmsTypes;
    }

    public DsCollations getStoreCollation() {
        return storeCollation;
    }

    public DsStores storeCollation(DsCollations dsCollations) {
        this.storeCollation = dsCollations;
        return this;
    }

    public void setStoreCollation(DsCollations dsCollations) {
        this.storeCollation = dsCollations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DsStores)) {
            return false;
        }
        return id != null && id.equals(((DsStores) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DsStores{" +
            "id=" + getId() +
            ", storeName='" + getStoreName() + "'" +
            ", storeDescription='" + getStoreDescription() + "'" +
            ", storeSize=" + getStoreSize() +
            ", growthSize=" + getGrowthSize() +
            "}";
    }
}
