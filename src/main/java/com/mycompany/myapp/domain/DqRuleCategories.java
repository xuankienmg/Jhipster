package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DqRuleCategories.
 */
@Entity
@Table(name = "dq_rule_categories")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DqRuleCategories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "cat_description")
    private String catDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public DqRuleCategories catName(String catName) {
        this.catName = catName;
        return this;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public DqRuleCategories catDescription(String catDescription) {
        this.catDescription = catDescription;
        return this;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DqRuleCategories)) {
            return false;
        }
        return id != null && id.equals(((DqRuleCategories) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DqRuleCategories{" +
            "id=" + getId() +
            ", catName='" + getCatName() + "'" +
            ", catDescription='" + getCatDescription() + "'" +
            "}";
    }
}
