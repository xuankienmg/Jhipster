package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DqRules.
 */
@Entity
@Table(name = "dq_rules")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "rule_description")
    private String ruleDescription;

    @ManyToOne
    @JsonIgnoreProperties("dqRules")
    private DqRuleTypes type;

    @ManyToOne
    @JsonIgnoreProperties("dqRules")
    private DqRuleRiskLevels risk;

    @ManyToOne
    @JsonIgnoreProperties("dqRules")
    private DqRuleStatus status;

    @ManyToOne
    @JsonIgnoreProperties("dqRules")
    private DqRuleCategories cat;

    @ManyToOne
    @JsonIgnoreProperties("dqRules")
    private DqRuleActions action;

    @ManyToMany(mappedBy = "rules")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<DqStandards> stds = new HashSet<>();

    @ManyToMany(mappedBy = "rules")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<DsColumns> cols = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public DqRules ruleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public DqRules ruleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
        return this;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public DqRuleTypes getType() {
        return type;
    }

    public DqRules type(DqRuleTypes dqRuleTypes) {
        this.type = dqRuleTypes;
        return this;
    }

    public void setType(DqRuleTypes dqRuleTypes) {
        this.type = dqRuleTypes;
    }

    public DqRuleRiskLevels getRisk() {
        return risk;
    }

    public DqRules risk(DqRuleRiskLevels dqRuleRiskLevels) {
        this.risk = dqRuleRiskLevels;
        return this;
    }

    public void setRisk(DqRuleRiskLevels dqRuleRiskLevels) {
        this.risk = dqRuleRiskLevels;
    }

    public DqRuleStatus getStatus() {
        return status;
    }

    public DqRules status(DqRuleStatus dqRuleStatus) {
        this.status = dqRuleStatus;
        return this;
    }

    public void setStatus(DqRuleStatus dqRuleStatus) {
        this.status = dqRuleStatus;
    }

    public DqRuleCategories getCat() {
        return cat;
    }

    public DqRules cat(DqRuleCategories dqRuleCategories) {
        this.cat = dqRuleCategories;
        return this;
    }

    public void setCat(DqRuleCategories dqRuleCategories) {
        this.cat = dqRuleCategories;
    }

    public DqRuleActions getAction() {
        return action;
    }

    public DqRules action(DqRuleActions dqRuleActions) {
        this.action = dqRuleActions;
        return this;
    }

    public void setAction(DqRuleActions dqRuleActions) {
        this.action = dqRuleActions;
    }

    public Set<DqStandards> getStds() {
        return stds;
    }

    public DqRules stds(Set<DqStandards> dqStandards) {
        this.stds = dqStandards;
        return this;
    }

    public DqRules addStd(DqStandards dqStandards) {
        this.stds.add(dqStandards);
        dqStandards.getRules().add(this);
        return this;
    }

    public DqRules removeStd(DqStandards dqStandards) {
        this.stds.remove(dqStandards);
        dqStandards.getRules().remove(this);
        return this;
    }

    public void setStds(Set<DqStandards> dqStandards) {
        this.stds = dqStandards;
    }

    public Set<DsColumns> getCols() {
        return cols;
    }

    public DqRules cols(Set<DsColumns> dsColumns) {
        this.cols = dsColumns;
        return this;
    }

    public DqRules addCol(DsColumns dsColumns) {
        this.cols.add(dsColumns);
        dsColumns.getRules().add(this);
        return this;
    }

    public DqRules removeCol(DsColumns dsColumns) {
        this.cols.remove(dsColumns);
        dsColumns.getRules().remove(this);
        return this;
    }

    public void setCols(Set<DsColumns> dsColumns) {
        this.cols = dsColumns;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqRules)) {
            return false;
        }
        return id != null && id.equals(((DqRules) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqRules{" +
            "id=" + getId() +
            ", ruleName='" + getRuleName() + "'" +
            ", ruleDescription='" + getRuleDescription() + "'" +
            "}";
    }
}
