package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqNotifications.
 */
@Entity
@Table(name = "dq_notifications")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqNotifications implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repicient_id")
    private Integer repicientId;

    @Column(name = "repicient_type_id")
    private Integer repicientTypeId;

    @ManyToOne
    @JsonIgnoreProperties("dqNotifications")
    private DqRules rule;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRepicientId() {
        return repicientId;
    }

    public DqNotifications repicientId(Integer repicientId) {
        this.repicientId = repicientId;
        return this;
    }

    public void setRepicientId(Integer repicientId) {
        this.repicientId = repicientId;
    }

    public Integer getRepicientTypeId() {
        return repicientTypeId;
    }

    public DqNotifications repicientTypeId(Integer repicientTypeId) {
        this.repicientTypeId = repicientTypeId;
        return this;
    }

    public void setRepicientTypeId(Integer repicientTypeId) {
        this.repicientTypeId = repicientTypeId;
    }

    public DqRules getRule() {
        return rule;
    }

    public DqNotifications rule(DqRules dqRules) {
        this.rule = dqRules;
        return this;
    }

    public void setRule(DqRules dqRules) {
        this.rule = dqRules;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqNotifications)) {
            return false;
        }
        return id != null && id.equals(((DqNotifications) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqNotifications{" +
            "id=" + getId() +
            ", repicientId=" + getRepicientId() +
            ", repicientTypeId=" + getRepicientTypeId() +
            "}";
    }
}
