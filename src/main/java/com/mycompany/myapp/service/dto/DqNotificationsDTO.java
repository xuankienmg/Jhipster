package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqNotifications} entity.
 */
public class DqNotificationsDTO implements Serializable {

    private Long id;

    private Integer repicientId;

    private Integer repicientTypeId;


    private Long ruleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRepicientId() {
        return repicientId;
    }

    public void setRepicientId(Integer repicientId) {
        this.repicientId = repicientId;
    }

    public Integer getRepicientTypeId() {
        return repicientTypeId;
    }

    public void setRepicientTypeId(Integer repicientTypeId) {
        this.repicientTypeId = repicientTypeId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long dqRulesId) {
        this.ruleId = dqRulesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqNotificationsDTO dqNotificationsDTO = (DqNotificationsDTO) o;
        if (dqNotificationsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqNotificationsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqNotificationsDTO{" +
            "id=" + getId() +
            ", repicientId=" + getRepicientId() +
            ", repicientTypeId=" + getRepicientTypeId() +
            ", ruleId=" + getRuleId() +
            "}";
    }
}
