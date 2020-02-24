package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A EventLogs.
 */
@Entity
@Table(name = "event_logs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EventLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rows")
    private Long rows;

    @Column(name = "event_note")
    private String eventNote;

    @Column(name = "event_timestamp")
    private Instant eventTimestamp;

    @ManyToOne
    @JsonIgnoreProperties("eventLogs")
    private EventTypes eventType;

    @ManyToOne
    @JsonIgnoreProperties("eventLogs")
    private EventCategories eventCat;

    @ManyToOne
    @JsonIgnoreProperties("eventLogs")
    private DataFlows flow;

    @ManyToOne
    @JsonIgnoreProperties("eventLogs")
    private DsTables tbl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRows() {
        return rows;
    }

    public EventLogs rows(Long rows) {
        this.rows = rows;
        return this;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public String getEventNote() {
        return eventNote;
    }

    public EventLogs eventNote(String eventNote) {
        this.eventNote = eventNote;
        return this;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public Instant getEventTimestamp() {
        return eventTimestamp;
    }

    public EventLogs eventTimestamp(Instant eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
        return this;
    }

    public void setEventTimestamp(Instant eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public EventTypes getEventType() {
        return eventType;
    }

    public EventLogs eventType(EventTypes eventTypes) {
        this.eventType = eventTypes;
        return this;
    }

    public void setEventType(EventTypes eventTypes) {
        this.eventType = eventTypes;
    }

    public EventCategories getEventCat() {
        return eventCat;
    }

    public EventLogs eventCat(EventCategories eventCategories) {
        this.eventCat = eventCategories;
        return this;
    }

    public void setEventCat(EventCategories eventCategories) {
        this.eventCat = eventCategories;
    }

    public DataFlows getFlow() {
        return flow;
    }

    public EventLogs flow(DataFlows dataFlows) {
        this.flow = dataFlows;
        return this;
    }

    public void setFlow(DataFlows dataFlows) {
        this.flow = dataFlows;
    }

    public DsTables getTbl() {
        return tbl;
    }

    public EventLogs tbl(DsTables dsTables) {
        this.tbl = dsTables;
        return this;
    }

    public void setTbl(DsTables dsTables) {
        this.tbl = dsTables;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventLogs)) {
            return false;
        }
        return id != null && id.equals(((EventLogs) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EventLogs{" +
            "id=" + getId() +
            ", rows=" + getRows() +
            ", eventNote='" + getEventNote() + "'" +
            ", eventTimestamp='" + getEventTimestamp() + "'" +
            "}";
    }
}
