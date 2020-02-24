package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqRuleTypes} entity.
 */
public class DqRuleTypesDTO implements Serializable {

    private Long id;

    private String typeName;

    private String typeDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqRuleTypesDTO dqRuleTypesDTO = (DqRuleTypesDTO) o;
        if (dqRuleTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqRuleTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqRuleTypesDTO{" +
            "id=" + getId() +
            ", typeName='" + getTypeName() + "'" +
            ", typeDescription='" + getTypeDescription() + "'" +
            "}";
    }
}
