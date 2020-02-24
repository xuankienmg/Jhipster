package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqStandardTypes} entity.
 */
public class DqStandardTypesDTO implements Serializable {

    private Long id;

    private String stdTypeName;

    private String stdTypeDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdTypeName() {
        return stdTypeName;
    }

    public void setStdTypeName(String stdTypeName) {
        this.stdTypeName = stdTypeName;
    }

    public String getStdTypeDescription() {
        return stdTypeDescription;
    }

    public void setStdTypeDescription(String stdTypeDescription) {
        this.stdTypeDescription = stdTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqStandardTypesDTO dqStandardTypesDTO = (DqStandardTypesDTO) o;
        if (dqStandardTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqStandardTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqStandardTypesDTO{" +
            "id=" + getId() +
            ", stdTypeName='" + getStdTypeName() + "'" +
            ", stdTypeDescription='" + getStdTypeDescription() + "'" +
            "}";
    }
}
