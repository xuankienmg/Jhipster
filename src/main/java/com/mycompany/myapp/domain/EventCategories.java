package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EventCategories.
 */
@Entity
@Table(name = "event_categories")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EventCategories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_cat_name")
    private String eventCatName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventCatName() {
        return eventCatName;
    }

    public EventCategories eventCatName(String eventCatName) {
        this.eventCatName = eventCatName;
        return this;
    }

    public void setEventCatName(String eventCatName) {
        this.eventCatName = eventCatName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventCategories)) {
            return false;
        }
        return id != null && id.equals(((EventCategories) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EventCategories{" +
            "id=" + getId() +
            ", eventCatName='" + getEventCatName() + "'" +
            "}";
    }
}
