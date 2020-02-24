package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqStandardDetailsEntityText.
 */
@Entity
@Table(name = "test")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqStandardDetailsEntityText implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_attribute_name")
    private String stdAttributeName;

    @Column(name = "std_attribute_value")
    private Long stdAttributeValue;

    @ManyToOne
    @JsonIgnoreProperties("dqStandardDetailsEntityTexts")
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

    public DqStandardDetailsEntityText stdAttributeName(String stdAttributeName) {
        this.stdAttributeName = stdAttributeName;
        return this;
    }

    public void setStdAttributeName(String stdAttributeName) {
        this.stdAttributeName = stdAttributeName;
    }

    public Long getStdAttributeValue() {
        return stdAttributeValue;
    }

    public DqStandardDetailsEntityText stdAttributeValue(Long stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
        return this;
    }

    public void setStdAttributeValue(Long stdAttributeValue) {
        this.stdAttributeValue = stdAttributeValue;
    }

    public DqStandards getStd() {
        return std;
    }

    public DqStandardDetailsEntityText std(DqStandards dqStandards) {
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
        if (!(o instanceof DqStandardDetailsEntityText)) {
            return false;
        }
        return id != null && id.equals(((DqStandardDetailsEntityText) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqStandardDetailsEntityText{" +
            "id=" + getId() +
            ", stdAttributeName='" + getStdAttributeName() + "'" +
            ", stdAttributeValue=" + getStdAttributeValue() +
            "}";
    }
}
