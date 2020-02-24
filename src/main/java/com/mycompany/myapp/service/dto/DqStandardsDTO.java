package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqStandards} entity.
 */
public class DqStandardsDTO implements Serializable {

    private Long id;

    private String stdName;

    private String stdDescription;


    private Long stdTypeId;

    private Set<DqRulesDTO> rules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdDescription() {
        return stdDescription;
    }

    public void setStdDescription(String stdDescription) {
        this.stdDescription = stdDescription;
    }

    public Long getStdTypeId() {
        return stdTypeId;
    }

    public void setStdTypeId(Long dqStandardTypesId) {
        this.stdTypeId = dqStandardTypesId;
    }

    public Set<DqRulesDTO> getRules() {
        return rules;
    }

    public void setRules(Set<DqRulesDTO> dqRules) {
        this.rules = dqRules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqStandardsDTO dqStandardsDTO = (DqStandardsDTO) o;
        if (dqStandardsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqStandardsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqStandardsDTO{" +
            "id=" + getId() +
            ", stdName='" + getStdName() + "'" +
            ", stdDescription='" + getStdDescription() + "'" +
            ", stdTypeId=" + getStdTypeId() +
            "}";
    }
}
