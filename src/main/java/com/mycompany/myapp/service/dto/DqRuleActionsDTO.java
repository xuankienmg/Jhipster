package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqRuleActions} entity.
 */
public class DqRuleActionsDTO implements Serializable {

    private Long id;

    private String actionName;

    private String actionDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqRuleActionsDTO dqRuleActionsDTO = (DqRuleActionsDTO) o;
        if (dqRuleActionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqRuleActionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqRuleActionsDTO{" +
            "id=" + getId() +
            ", actionName='" + getActionName() + "'" +
            ", actionDescription='" + getActionDescription() + "'" +
            "}";
    }
}
