package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqRuleRiskLevels.
 */
@Entity
@Table(name = "dq_rule_risk_levels")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqRuleRiskLevels implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "risk_name")
    private String riskName;

    @Column(name = "risk_description")
    private String riskDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiskName() {
        return riskName;
    }

    public DqRuleRiskLevels riskName(String riskName) {
        this.riskName = riskName;
        return this;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public DqRuleRiskLevels riskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
        return this;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqRuleRiskLevels)) {
            return false;
        }
        return id != null && id.equals(((DqRuleRiskLevels) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqRuleRiskLevels{" +
            "id=" + getId() +
            ", riskName='" + getRiskName() + "'" +
            ", riskDescription='" + getRiskDescription() + "'" +
            "}";
    }
}
