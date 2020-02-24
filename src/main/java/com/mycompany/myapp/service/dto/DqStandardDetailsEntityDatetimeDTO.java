package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime} entity.
 */
public class DqStandardDetailsEntityDatetimeDTO implements Serializable {

    private Long id;

    private String stdAttributeName;

    private Instant stdAttributeValue;


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

    public Instant getStdAttributeValue() {
        return stdAttributeValue;
    }

    public void setStdAttributeValue(Instant stdAttributeValue) {
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

        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO = (DqStandardDetailsEntityDatetimeDTO) o;
        if (dqStandardDetailsEntityDatetimeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqStandardDetailsEntityDatetimeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityDatetimeDTO{" +
            "id=" + getId() +
            ", stdAttributeName='" + getStdAttributeName() + "'" +
            ", stdAttributeValue='" + getStdAttributeValue() + "'" +
            ", stdId=" + getStdId() +
            "}";
    }
}
