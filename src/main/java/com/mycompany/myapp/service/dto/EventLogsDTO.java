package com.mycompany.myapp.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EventLogs} entity.
 */
public class EventLogsDTO implements Serializable {

    private Long id;

    private Long rows;

    private String eventNote;

    private Instant eventTimestamp;


    private Long eventTypeId;

    private Long eventCatId;

    private Long flowId;

    private Long tblId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public Instant getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Instant eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventTypesId) {
        this.eventTypeId = eventTypesId;
    }

    public Long getEventCatId() {
        return eventCatId;
    }

    public void setEventCatId(Long eventCategoriesId) {
        this.eventCatId = eventCategoriesId;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long dataFlowsId) {
        this.flowId = dataFlowsId;
    }

    public Long getTblId() {
        return tblId;
    }

    public void setTblId(Long dsTablesId) {
        this.tblId = dsTablesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventLogsDTO eventLogsDTO = (EventLogsDTO) o;
        if (eventLogsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLogsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLogsDTO{" +
            "id=" + getId() +
            ", rows=" + getRows() +
            ", eventNote='" + getEventNote() + "'" +
            ", eventTimestamp='" + getEventTimestamp() + "'" +
            ", eventTypeId=" + getEventTypeId() +
            ", eventCatId=" + getEventCatId() +
            ", flowId=" + getFlowId() +
            ", tblId=" + getTblId() +
            "}";
    }
}
