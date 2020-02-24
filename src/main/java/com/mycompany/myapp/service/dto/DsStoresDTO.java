package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DsStores} entity.
 */
public class DsStoresDTO implements Serializable {

    private Long id;

    private String storeName;

    private String storeDescription;

    private Long storeSize;

    private Long growthSize;


    private Long storeDbmsTypeId;

    private Long storeCollationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public Long getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(Long storeSize) {
        this.storeSize = storeSize;
    }

    public Long getGrowthSize() {
        return growthSize;
    }

    public void setGrowthSize(Long growthSize) {
        this.growthSize = growthSize;
    }

    public Long getStoreDbmsTypeId() {
        return storeDbmsTypeId;
    }

    public void setStoreDbmsTypeId(Long dsDbmsTypesId) {
        this.storeDbmsTypeId = dsDbmsTypesId;
    }

    public Long getStoreCollationId() {
        return storeCollationId;
    }

    public void setStoreCollationId(Long dsCollationsId) {
        this.storeCollationId = dsCollationsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DsStoresDTO dsStoresDTO = (DsStoresDTO) o;
        if (dsStoresDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dsStoresDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DsStoresDTO{" +
            "id=" + getId() +
            ", storeName='" + getStoreName() + "'" +
            ", storeDescription='" + getStoreDescription() + "'" +
            ", storeSize=" + getStoreSize() +
            ", growthSize=" + getGrowthSize() +
            ", storeDbmsTypeId=" + getStoreDbmsTypeId() +
            ", storeCollationId=" + getStoreCollationId() +
            "}";
    }
}
