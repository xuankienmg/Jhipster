package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EtlPackages} entity.
 */
public class EtlPackagesDTO implements Serializable {

    private Long id;

    private String etlPkgName;

    private String etlPkgDescription;

    private String etlPkgSchedule;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtlPkgName() {
        return etlPkgName;
    }

    public void setEtlPkgName(String etlPkgName) {
        this.etlPkgName = etlPkgName;
    }

    public String getEtlPkgDescription() {
        return etlPkgDescription;
    }

    public void setEtlPkgDescription(String etlPkgDescription) {
        this.etlPkgDescription = etlPkgDescription;
    }

    public String getEtlPkgSchedule() {
        return etlPkgSchedule;
    }

    public void setEtlPkgSchedule(String etlPkgSchedule) {
        this.etlPkgSchedule = etlPkgSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtlPackagesDTO etlPackagesDTO = (EtlPackagesDTO) o;
        if (etlPackagesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etlPackagesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtlPackagesDTO{" +
            "id=" + getId() +
            ", etlPkgName='" + getEtlPkgName() + "'" +
            ", etlPkgDescription='" + getEtlPkgDescription() + "'" +
            ", etlPkgSchedule='" + getEtlPkgSchedule() + "'" +
            "}";
    }
}
