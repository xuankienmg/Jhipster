package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DqRuleCategories} entity.
 */
public class DqRuleCategoriesDTO implements Serializable {

    private Long id;

    private String catName;

    private String catDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DqRuleCategoriesDTO dqRuleCategoriesDTO = (DqRuleCategoriesDTO) o;
        if (dqRuleCategoriesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dqRuleCategoriesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DqRuleCategoriesDTO{" +
            "id=" + getId() +
            ", catName='" + getCatName() + "'" +
            ", catDescription='" + getCatDescription() + "'" +
            "}";
    }
}
