package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqStandardDetailsEntityDecimal.
 */
@Entity
@Table(name = "decicam")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqStandardDetailsEntityDecimal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_attribute_name")
    private String stdAttributeName;

    @Column(name = "std_attribute_value")
    private Long stdAttributeValue;

    @ManyToOne
    @JsonIgnoreProperties("dqStandardDetailsEntityDecimals")
    private DqStandards std;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdAttributeName() {
        return stdAttributeName;
    }

    public DqStandardDetailsEntityDecimal stdAttributeName(String stdAttributeName) {
        this.stdAttributeName = stdAttributeName;
        return this;
    }

    public void setStdAttributeName(String stdAttributeName) {
        this.stdAttributeName = stdAttributeName;
    }

    public Long getStdAttributeValue() {
        return stdAttributeValue;
    }

    public DqStandardDetailsEntityDecimal stdAttributeValue(Long stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
        return this;
    }

    public void setStdAttributeValue(Long stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
    }

    public DqStandards getStd() {
        return std;
    }

    public DqStandardDetailsEntityDecimal std(DqStandards dqStandards) {
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
        if (!(o instanceof DqStandardDetailsEntityDecimal)) {
            return false;
        }
        return id != null && id.equals(((DqStandardDetailsEntityDecimal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityDecimal{" +
            "id=" + getId() +
            ", stdAttributeName='" + getStdAttributeName() + "'" +
            ", stdAttributeValue=" + getStdAttributeValue() +
            "}";
    }
}
