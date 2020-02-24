package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal} entity.
 */
public class DqStandardDetailsEntityDecimalDTO implements Serializable {

    private Long id;

    private String stdAttributeName;

    private Long stdAttributeValue;


    private Long stdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdAttributeName() {
        return stdAttributeName;
    }

    public void setStdAttributeName(String stdAttributeName) {
        this.stdAttributeName = stdAttributeName;
    }

    public Long getStdAttributeValue() {
        return stdAttributeValue;
    }

    public void setStdAttributeValue(Long stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
    }

    public Long getStdId() {
        return stdId;
    }

    public void setStdId(Long dqStandardsId) {
        this.stdId = dqStandardsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO = (DqStandardDetailsEntityDecimalDTO) o;
        if (dqStandardDetailsEntityDecimalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqStandardDetailsEntityDecimalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityDecimalDTO{" +
            "id=" + getId() +
            ", stdAttributeName='" + getStdAttributeName() + "'" +
            ", stdAttributeValue=" + getStdAttributeValue() +
            ", stdId=" + getStdId() +
            "}";
    }
}
