package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqRuleRiskLevels} entity.
 */
public class DqRuleRiskLevelsDTO implements Serializable {

    private Long id;

    private String riskName;

    private String riskDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqRuleRiskLevelsDTO dqRuleRiskLevelsDTO = (DqRuleRiskLevelsDTO) o;
        if (dqRuleRiskLevelsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqRuleRiskLevelsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqRuleRiskLevelsDTO{" +
            "id=" + getId() +
            ", riskName='" + getRiskName() + "'" +
            ", riskDescription='" + getRiskDescription() + "'" +
            "}";
    }
}
