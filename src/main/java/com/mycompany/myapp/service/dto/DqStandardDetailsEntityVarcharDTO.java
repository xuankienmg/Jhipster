package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar} entity.
 */
public class DqStandardDetailsEntityVarcharDTO implements Serializable {

    private Long id;

    private String stdAtttributeName;

    private String stdAttributeValue;


    private Long stdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdAtttributeName() {
        return stdAtttributeName;
    }

    public void setStdAtttributeName(String stdAtttributeName) {
        this.stdAtttributeName = stdAtttributeName;
    }

    public String getStdAttributeValue() {
        return stdAttributeValue;
    }

    public void setStdAttributeValue(String stdAttributeValue) {
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

        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO = (DqStandardDetailsEntityVarcharDTO) o;
        if (dqStandardDetailsEntityVarcharDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqStandardDetailsEntityVarcharDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityVarcharDTO{" +
            "id=" + getId() +
            ", stdAtttributeName='" + getStdAtttributeName() + "'" +
            ", stdAttributeValue='" + getStdAttributeValue() + "'" +
            ", stdId=" + getStdId() +
            "}";
    }
}
