package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqRuleActions.
 */
@Entity
@Table(name = "dq_rule_actions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqRuleActions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "action_description")
    private String actionDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public DqRuleActions actionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public DqRuleActions actionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
        return this;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqRuleActions)) {
            return false;
        }
        return id != null && id.equals(((DqRuleActions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqRuleActions{" +
            "id=" + getId() +
            ", actionName='" + getActionName() + "'" +
            ", actionDescription='" + getActionDescription() + "'" +
            "}";
    }
}
