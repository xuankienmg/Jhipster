package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqRuleStatus} entity.
 */
public class DqRuleStatusDTO implements Serializable {

    private Long id;

    private String statusName;

    private String statusDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqRuleStatusDTO dqRuleStatusDTO = (DqRuleStatusDTO) o;
        if (dqRuleStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqRuleStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqRuleStatusDTO{" +
            "id=" + getId() +
            ", statusName='" + getStatusName() + "'" +
            ", statusDescription='" + getStatusDescription() + "'" +
            "}";
    }
}
