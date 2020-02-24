package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.EventLogs} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.EventLogsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /event-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EventLogsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter rows;

    private StringFilter eventNote;

    private InstantFilter eventTimestamp;

    private LongFilter eventTypeId;

    private LongFilter eventCatId;

    private LongFilter flowId;

    private LongFilter tblId;

    public EventLogsCriteria() {
    }

    public EventLogsCriteria(EventLogsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.rows = other.rows == null ? null : other.rows.copy();
        this.eventNote = other.eventNote == null ? null : other.eventNote.copy();
        this.eventTimestamp = other.eventTimestamp == null ? null : other.eventTimestamp.copy();
        this.eventTypeId = other.eventTypeId == null ? null : other.eventTypeId.copy();
        this.eventCatId = other.eventCatId == null ? null : other.eventCatId.copy();
        this.flowId = other.flowId == null ? null : other.flowId.copy();
        this.tblId = other.tblId == null ? null : other.tblId.copy();
    }

    @Override
    public EventLogsCriteria copy() {
        return new EventLogsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getRows() {
        return rows;
    }

    public void setRows(LongFilter rows) {
        this.rows = rows;
    }

    public StringFilter getEventNote() {
        return eventNote;
    }

    public void setEventNote(StringFilter eventNote) {
        this.eventNote = eventNote;
    }

    public InstantFilter getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(InstantFilter eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public LongFilter getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(LongFilter eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public LongFilter getEventCatId() {
        return eventCatId;
    }

    public void setEventCatId(LongFilter eventCatId) {
        this.eventCatId = eventCatId;
    }

    public LongFilter getFlowId() {
        return flowId;
    }

    public void setFlowId(LongFilter flowId) {
        this.flowId = flowId;
    }

    public LongFilter getTblId() {
        return tblId;
    }

    public void setTblId(LongFilter tblId) {
        this.tblId = tblId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EventLogsCriteria that = (EventLogsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rows, that.rows) &&
            Objects.equals(eventNote, that.eventNote) &&
            Objects.equals(eventTimestamp, that.eventTimestamp) &&
            Objects.equals(eventTypeId, that.eventTypeId) &&
            Objects.equals(eventCatId, that.eventCatId) &&
            Objects.equals(flowId, that.flowId) &&
            Objects.equals(tblId, that.tblId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rows,
        eventNote,
        eventTimestamp,
        eventTypeId,
        eventCatId,
        flowId,
        tblId
        );
    }

    @Override
    public String toString() {
        return "EventLogsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rows != null ? "rows=" + rows + ", " : "") +
                (eventNote != null ? "eventNote=" + eventNote + ", " : "") +
                (eventTimestamp != null ? "eventTimestamp=" + eventTimestamp + ", " : "") +
                (eventTypeId != null ? "eventTypeId=" + eventTypeId + ", " : "") +
                (eventCatId != null ? "eventCatId=" + eventCatId + ", " : "") +
                (flowId != null ? "flowId=" + flowId + ", " : "") +
                (tblId != null ? "tblId=" + tblId + ", " : "") +
            "}";
    }

}
