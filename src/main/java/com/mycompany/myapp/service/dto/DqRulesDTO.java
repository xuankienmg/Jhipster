package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqRules} entity.
 */
public class DqRulesDTO implements Serializable {

    private Long id;

    private String ruleName;

    private String ruleDescription;


    private Long typeId;

    private Long riskId;

    private Long statusId;

    private Long catId;

    private Long actionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long dqRuleTypesId) {
        this.typeId = dqRuleTypesId;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long dqRuleRiskLevelsId) {
        this.riskId = dqRuleRiskLevelsId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long dqRuleStatusId) {
        this.statusId = dqRuleStatusId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long dqRuleCategoriesId) {
        this.catId = dqRuleCategoriesId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long dqRuleActionsId) {
        this.actionId = dqRuleActionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqRulesDTO dqRulesDTO = (DqRulesDTO) o;
        if (dqRulesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqRulesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqRulesDTO{" +
            "id=" + getId() +
            ", ruleName='" + getRuleName() + "'" +
            ", ruleDescription='" + getRuleDescription() + "'" +
            ", typeId=" + getTypeId() +
            ", riskId=" + getRiskId() +
            ", statusId=" + getStatusId() +
            ", catId=" + getCatId() +
            ", actionId=" + getActionId() +
            "}";
    }
}
