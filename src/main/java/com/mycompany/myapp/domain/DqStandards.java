package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DqStandards.
 */
@Entity
@Table(name = "dq_standards")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqStandards implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_name")
    private String stdName;

    @Column(name = "std_description")
    private String stdDescription;

    @ManyToOne
    @JsonIgnoreProperties("dqStandards")
    private DqStandardTypes stdType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "dq_standards_rule",
               joinColumns = @JoinColumn(name = "dq_standards_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "rule_id", referencedColumnName = "id"))
    private Set<DqRules> rules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStdName() {
        return stdName;
    }

    public DqStandards stdName(String stdName) {
        this.stdName = stdName;
        return this;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdDescription() {
        return stdDescription;
    }

    public DqStandards stdDescription(String stdDescription) {
        this.stdDescription = stdDescription;
        return this;
    }

    public void setStdDescription(String stdDescription) {
        this.stdDescription = stdDescription;
    }

    public DqStandardTypes getStdType() {
        return stdType;
    }

    public DqStandards stdType(DqStandardTypes dqStandardTypes) {
        this.stdType = dqStandardTypes;
        return this;
    }

    public void setStdType(DqStandardTypes dqStandardTypes) {
        this.stdType = dqStandardTypes;
    }

    public Set<DqRules> getRules() {
        return rules;
    }

    public DqStandards rules(Set<DqRules> dqRules) {
        this.rules = dqRules;
        return this;
    }

    public DqStandards addRule(DqRules dqRules) {
        this.rules.add(dqRules);
        dqRules.getStds().add(this);
        return this;
    }

    public DqStandards removeRule(DqRules dqRules) {
        this.rules.remove(dqRules);
        dqRules.getStds().remove(this);
        return this;
    }

    public void setRules(Set<DqRules> dqRules) {
        this.rules = dqRules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqStandards)) {
            return false;
        }
        return id != null && id.equals(((DqStandards) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqStandards{" +
            "id=" + getId() +
            ", stdName='" + getStdName() + "'" +
            ", stdDescription='" + getStdDescription() + "'" +
            "}";
    }
}
