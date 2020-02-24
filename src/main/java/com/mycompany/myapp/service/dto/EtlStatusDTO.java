package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EtlStatus} entity.
 */
public class EtlStatusDTO implements Serializable {

    private Long id;

    private String etlStatusName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtlStatusName() {
        return etlStatusName;
    }

    public void setEtlStatusName(String etlStatusName) {
        this.etlStatusName = etlStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtlStatusDTO etlStatusDTO = (EtlStatusDTO) o;
        if (etlStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etlStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtlStatusDTO{" +
            "id=" + getId() +
            ", etlStatusName='" + getEtlStatusName() + "'" +
            "}";
    }
}
