package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqStandardDetailsEntityVarchar.
 */
@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqStandardDetailsEntityVarchar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_atttribute_name")
    private String stdAtttributeName;

    @Column(name = "std_attribute_value")
    private String stdAttributeValue;

    @ManyToOne
    @JsonIgnoreProperties("dqStandardDetailsEntityVarchars")
    private DqStandards std;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdAtttributeName() {
        return stdAtttributeName;
    }

    public DqStandardDetailsEntityVarchar stdAtttributeName(String stdAtttributeName) {
        this.stdAtttributeName = stdAtttributeName;
        return this;
    }

    public void setStdAtttributeName(String stdAtttributeName) {
        this.stdAtttributeName = stdAtttributeName;
    }

    public String getStdAttributeValue() {
        return stdAttributeValue;
    }

    public DqStandardDetailsEntityVarchar stdAttributeValue(String stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
        return this;
    }

    public void setStdAttributeValue(String stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
    }

    public DqStandards getStd() {
        return std;
    }

    public DqStandardDetailsEntityVarchar std(DqStandards dqStandards) {
        this.std = dqStandards;
        return this;
    }

    public void setStd(DqStandards dqStandards) {
        this.std = dqStandards;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqStandardDetailsEntityVarchar)) {
            return false;
        }
        return id != null && id.equals(((DqStandardDetailsEntityVarchar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityVarchar{" +
            "id=" + getId() +
            ", stdAtttributeName='" + getStdAtttributeName() + "'" +
            ", stdAttributeValue='" + getStdAttributeValue() + "'" +
            "}";
    }
}
