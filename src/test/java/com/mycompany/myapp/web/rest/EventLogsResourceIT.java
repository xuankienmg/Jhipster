package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.EventLogs;
import com.mycompany.myapp.domain.EventTypes;
import com.mycompany.myapp.domain.EventCategories;
import com.mycompany.myapp.domain.DataFlows;
import com.mycompany.myapp.domain.DsTables;
import com.mycompany.myapp.repository.EventLogsRepository;
import com.mycompany.myapp.service.EventLogsService;
import com.mycompany.myapp.service.dto.EventLogsDTO;
import com.mycompany.myapp.service.mapper.EventLogsMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.EventLogsCriteria;
import com.mycompany.myapp.service.EventLogsQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EventLogsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class EventLogsResourceIT {

    private static final Long DEFAULT_ROWS = 1L;
    private static final Long UPDATED_ROWS = 2L;
    private static final Long SMALLER_ROWS = 1L - 1L;

    private static final String DEFAULT_EVENT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_NOTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_EVENT_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EVENT_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EventLogsRepository eventLogsRepository;

    @Autowired
    private EventLogsMapper eventLogsMapper;

    @Autowired
    private EventLogsService eventLogsService;

    @Autowired
    private EventLogsQueryService eventLogsQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEventLogsMockMvc;

    private EventLogs eventLogs;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventLogsResource eventLogsResource = new EventLogsResource(eventLogsService, eventLogsQueryService);
        this.restEventLogsMockMvc = MockMvcBuilders.standaloneSetup(eventLogsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventLogs createEntity(EntityManager em) {
        EventLogs eventLogs = new EventLogs()
            .rows(DEFAULT_ROWS)
            .eventNote(DEFAULT_EVENT_NOTE)
            .eventTimestamp(DEFAULT_EVENT_TIMESTAMP);
        return eventLogs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventLogs createUpdatedEntity(EntityManager em) {
        EventLogs eventLogs = new EventLogs()
            .rows(UPDATED_ROWS)
            .eventNote(UPDATED_EVENT_NOTE)
            .eventTimestamp(UPDATED_EVENT_TIMESTAMP);
        return eventLogs;
    }

    @BeforeEach
    public void initTest() {
        eventLogs = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventLogs() throws Exception {
        int databaseSizeBeforeCreate = eventLogsRepository.findAll().size();

        // Create the EventLogs
        EventLogsDTO eventLogsDTO = eventLogsMapper.toDto(eventLogs);
        restEventLogsMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventLogsDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLogs in the database
        List<EventLogs> eventLogsList = eventLogsRepository.findAll();
        assertThat(eventLogsList).hasSize(databaseSizeBeforeCreate + 1);
        EventLogs testEventLogs = eventLogsList.get(eventLogsList.size() - 1);
        assertThat(testEventLogs.getRows()).isEqualTo(DEFAULT_ROWS);
        assertThat(testEventLogs.getEventNote()).isEqualTo(DEFAULT_EVENT_NOTE);
        assertThat(testEventLogs.getEventTimestamp()).isEqualTo(DEFAULT_EVENT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createEventLogsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventLogsRepository.findAll().size();

        // Create the EventLogs with an existing ID
        eventLogs.setId(1L);
        EventLogsDTO eventLogsDTO = eventLogsMapper.toDto(eventLogs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventLogsMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventLogsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventLogs in the database
        List<EventLogs> eventLogsList = eventLogsRepository.findAll();
        assertThat(eventLogsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEventLogs() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList
        restEventLogsMockMvc.perform(get("/api/event-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventLogs.getId().intValue())))
            .andExpect(jsonPath("$.[*].rows").value(hasItem(DEFAULT_ROWS.intValue())))
            .andExpect(jsonPath("$.[*].eventNote").value(hasItem(DEFAULT_EVENT_NOTE)))
            .andExpect(jsonPath("$.[*].eventTimestamp").value(hasItem(DEFAULT_EVENT_TIMESTAMP.toString())));
    }
    
    @Test
    @Transactional
    public void getEventLogs() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get the eventLogs
        restEventLogsMockMvc.perform(get("/api/event-logs/{id}", eventLogs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventLogs.getId().intValue()))
            .andExpect(jsonPath("$.rows").value(DEFAULT_ROWS.intValue()))
            .andExpect(jsonPath("$.eventNote").value(DEFAULT_EVENT_NOTE))
            .andExpect(jsonPath("$.eventTimestamp").value(DEFAULT_EVENT_TIMESTAMP.toString()));
    }


    @Test
    @Transactional
    public void getEventLogsByIdFiltering() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        Long id = eventLogs.getId();

        defaultEventLogsShouldBeFound("id.equals=" + id);
        defaultEventLogsShouldNotBeFound("id.notEquals=" + id);

        defaultEventLogsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEventLogsShouldNotBeFound("id.greaterThan=" + id);

        defaultEventLogsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEventLogsShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEventLogsByRowsIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows equals to DEFAULT_ROWS
        defaultEventLogsShouldBeFound("rows.equals=" + DEFAULT_ROWS);

        // Get all the eventLogsList where rows equals to UPDATED_ROWS
        defaultEventLogsShouldNotBeFound("rows.equals=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows not equals to DEFAULT_ROWS
        defaultEventLogsShouldNotBeFound("rows.notEquals=" + DEFAULT_ROWS);

        // Get all the eventLogsList where rows not equals to UPDATED_ROWS
        defaultEventLogsShouldBeFound("rows.notEquals=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsInShouldWork() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows in DEFAULT_ROWS or UPDATED_ROWS
        defaultEventLogsShouldBeFound("rows.in=" + DEFAULT_ROWS + "," + UPDATED_ROWS);

        // Get all the eventLogsList where rows equals to UPDATED_ROWS
        defaultEventLogsShouldNotBeFound("rows.in=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows is not null
        defaultEventLogsShouldBeFound("rows.specified=true");

        // Get all the eventLogsList where rows is null
        defaultEventLogsShouldNotBeFound("rows.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows is greater than or equal to DEFAULT_ROWS
        defaultEventLogsShouldBeFound("rows.greaterThanOrEqual=" + DEFAULT_ROWS);

        // Get all the eventLogsList where rows is greater than or equal to UPDATED_ROWS
        defaultEventLogsShouldNotBeFound("rows.greaterThanOrEqual=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows is less than or equal to DEFAULT_ROWS
        defaultEventLogsShouldBeFound("rows.lessThanOrEqual=" + DEFAULT_ROWS);

        // Get all the eventLogsList where rows is less than or equal to SMALLER_ROWS
        defaultEventLogsShouldNotBeFound("rows.lessThanOrEqual=" + SMALLER_ROWS);
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsLessThanSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows is less than DEFAULT_ROWS
        defaultEventLogsShouldNotBeFound("rows.lessThan=" + DEFAULT_ROWS);

        // Get all the eventLogsList where rows is less than UPDATED_ROWS
        defaultEventLogsShouldBeFound("rows.lessThan=" + UPDATED_ROWS);
    }

    @Test
    @Transactional
    public void getAllEventLogsByRowsIsGreaterThanSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where rows is greater than DEFAULT_ROWS
        defaultEventLogsShouldNotBeFound("rows.greaterThan=" + DEFAULT_ROWS);

        // Get all the eventLogsList where rows is greater than SMALLER_ROWS
        defaultEventLogsShouldBeFound("rows.greaterThan=" + SMALLER_ROWS);
    }


    @Test
    @Transactional
    public void getAllEventLogsByEventNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventNote equals to DEFAULT_EVENT_NOTE
        defaultEventLogsShouldBeFound("eventNote.equals=" + DEFAULT_EVENT_NOTE);

        // Get all the eventLogsList where eventNote equals to UPDATED_EVENT_NOTE
        defaultEventLogsShouldNotBeFound("eventNote.equals=" + UPDATED_EVENT_NOTE);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventNoteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventNote not equals to DEFAULT_EVENT_NOTE
        defaultEventLogsShouldNotBeFound("eventNote.notEquals=" + DEFAULT_EVENT_NOTE);

        // Get all the eventLogsList where eventNote not equals to UPDATED_EVENT_NOTE
        defaultEventLogsShouldBeFound("eventNote.notEquals=" + UPDATED_EVENT_NOTE);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventNoteIsInShouldWork() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventNote in DEFAULT_EVENT_NOTE or UPDATED_EVENT_NOTE
        defaultEventLogsShouldBeFound("eventNote.in=" + DEFAULT_EVENT_NOTE + "," + UPDATED_EVENT_NOTE);

        // Get all the eventLogsList where eventNote equals to UPDATED_EVENT_NOTE
        defaultEventLogsShouldNotBeFound("eventNote.in=" + UPDATED_EVENT_NOTE);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventNote is not null
        defaultEventLogsShouldBeFound("eventNote.specified=true");

        // Get all the eventLogsList where eventNote is null
        defaultEventLogsShouldNotBeFound("eventNote.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventLogsByEventNoteContainsSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventNote contains DEFAULT_EVENT_NOTE
        defaultEventLogsShouldBeFound("eventNote.contains=" + DEFAULT_EVENT_NOTE);

        // Get all the eventLogsList where eventNote contains UPDATED_EVENT_NOTE
        defaultEventLogsShouldNotBeFound("eventNote.contains=" + UPDATED_EVENT_NOTE);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventNoteNotContainsSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventNote does not contain DEFAULT_EVENT_NOTE
        defaultEventLogsShouldNotBeFound("eventNote.doesNotContain=" + DEFAULT_EVENT_NOTE);

        // Get all the eventLogsList where eventNote does not contain UPDATED_EVENT_NOTE
        defaultEventLogsShouldBeFound("eventNote.doesNotContain=" + UPDATED_EVENT_NOTE);
    }


    @Test
    @Transactional
    public void getAllEventLogsByEventTimestampIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventTimestamp equals to DEFAULT_EVENT_TIMESTAMP
        defaultEventLogsShouldBeFound("eventTimestamp.equals=" + DEFAULT_EVENT_TIMESTAMP);

        // Get all the eventLogsList where eventTimestamp equals to UPDATED_EVENT_TIMESTAMP
        defaultEventLogsShouldNotBeFound("eventTimestamp.equals=" + UPDATED_EVENT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventTimestampIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventTimestamp not equals to DEFAULT_EVENT_TIMESTAMP
        defaultEventLogsShouldNotBeFound("eventTimestamp.notEquals=" + DEFAULT_EVENT_TIMESTAMP);

        // Get all the eventLogsList where eventTimestamp not equals to UPDATED_EVENT_TIMESTAMP
        defaultEventLogsShouldBeFound("eventTimestamp.notEquals=" + UPDATED_EVENT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventTimestampIsInShouldWork() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventTimestamp in DEFAULT_EVENT_TIMESTAMP or UPDATED_EVENT_TIMESTAMP
        defaultEventLogsShouldBeFound("eventTimestamp.in=" + DEFAULT_EVENT_TIMESTAMP + "," + UPDATED_EVENT_TIMESTAMP);

        // Get all the eventLogsList where eventTimestamp equals to UPDATED_EVENT_TIMESTAMP
        defaultEventLogsShouldNotBeFound("eventTimestamp.in=" + UPDATED_EVENT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventTimestampIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        // Get all the eventLogsList where eventTimestamp is not null
        defaultEventLogsShouldBeFound("eventTimestamp.specified=true");

        // Get all the eventLogsList where eventTimestamp is null
        defaultEventLogsShouldNotBeFound("eventTimestamp.specified=false");
    }

    @Test
    @Transactional
    public void getAllEventLogsByEventTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);
        EventTypes eventType = EventTypesResourceIT.createEntity(em);
        em.persist(eventType);
        em.flush();
        eventLogs.setEventType(eventType);
        eventLogsRepository.saveAndFlush(eventLogs);
        Long eventTypeId = eventType.getId();

        // Get all the eventLogsList where eventType equals to eventTypeId
        defaultEventLogsShouldBeFound("eventTypeId.equals=" + eventTypeId);

        // Get all the eventLogsList where eventType equals to eventTypeId + 1
        defaultEventLogsShouldNotBeFound("eventTypeId.equals=" + (eventTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllEventLogsByEventCatIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);
        EventCategories eventCat = EventCategoriesResourceIT.createEntity(em);
        em.persist(eventCat);
        em.flush();
        eventLogs.setEventCat(eventCat);
        eventLogsRepository.saveAndFlush(eventLogs);
        Long eventCatId = eventCat.getId();

        // Get all the eventLogsList where eventCat equals to eventCatId
        defaultEventLogsShouldBeFound("eventCatId.equals=" + eventCatId);

        // Get all the eventLogsList where eventCat equals to eventCatId + 1
        defaultEventLogsShouldNotBeFound("eventCatId.equals=" + (eventCatId + 1));
    }


    @Test
    @Transactional
    public void getAllEventLogsByFlowIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);
        DataFlows flow = DataFlowsResourceIT.createEntity(em);
        em.persist(flow);
        em.flush();
        eventLogs.setFlow(flow);
        eventLogsRepository.saveAndFlush(eventLogs);
        Long flowId = flow.getId();

        // Get all the eventLogsList where flow equals to flowId
        defaultEventLogsShouldBeFound("flowId.equals=" + flowId);

        // Get all the eventLogsList where flow equals to flowId + 1
        defaultEventLogsShouldNotBeFound("flowId.equals=" + (flowId + 1));
    }


    @Test
    @Transactional
    public void getAllEventLogsByTblIsEqualToSomething() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);
        DsTables tbl = DsTablesResourceIT.createEntity(em);
        em.persist(tbl);
        em.flush();
        eventLogs.setTbl(tbl);
        eventLogsRepository.saveAndFlush(eventLogs);
        Long tblId = tbl.getId();

        // Get all the eventLogsList where tbl equals to tblId
        defaultEventLogsShouldBeFound("tblId.equals=" + tblId);

        // Get all the eventLogsList where tbl equals to tblId + 1
        defaultEventLogsShouldNotBeFound("tblId.equals=" + (tblId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEventLogsShouldBeFound(String filter) throws Exception {
        restEventLogsMockMvc.perform(get("/api/event-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventLogs.getId().intValue())))
            .andExpect(jsonPath("$.[*].rows").value(hasItem(DEFAULT_ROWS.intValue())))
            .andExpect(jsonPath("$.[*].eventNote").value(hasItem(DEFAULT_EVENT_NOTE)))
            .andExpect(jsonPath("$.[*].eventTimestamp").value(hasItem(DEFAULT_EVENT_TIMESTAMP.toString())));

        // Check, that the count call also returns 1
        restEventLogsMockMvc.perform(get("/api/event-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEventLogsShouldNotBeFound(String filter) throws Exception {
        restEventLogsMockMvc.perform(get("/api/event-logs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEventLogsMockMvc.perform(get("/api/event-logs/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEventLogs() throws Exception {
        // Get the eventLogs
        restEventLogsMockMvc.perform(get("/api/event-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventLogs() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        int databaseSizeBeforeUpdate = eventLogsRepository.findAll().size();

        // Update the eventLogs
        EventLogs updatedEventLogs = eventLogsRepository.findById(eventLogs.getId()).get();
        // Disconnect from session so that the updates on updatedEventLogs are not directly saved in db
        em.detach(updatedEventLogs);
        updatedEventLogs
            .rows(UPDATED_ROWS)
            .eventNote(UPDATED_EVENT_NOTE)
            .eventTimestamp(UPDATED_EVENT_TIMESTAMP);
        EventLogsDTO eventLogsDTO = eventLogsMapper.toDto(updatedEventLogs);

        restEventLogsMockMvc.perform(put("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventLogsDTO)))
            .andExpect(status().isOk());

        // Validate the EventLogs in the database
        List<EventLogs> eventLogsList = eventLogsRepository.findAll();
        assertThat(eventLogsList).hasSize(databaseSizeBeforeUpdate);
        EventLogs testEventLogs = eventLogsList.get(eventLogsList.size() - 1);
        assertThat(testEventLogs.getRows()).isEqualTo(UPDATED_ROWS);
        assertThat(testEventLogs.getEventNote()).isEqualTo(UPDATED_EVENT_NOTE);
        assertThat(testEventLogs.getEventTimestamp()).isEqualTo(UPDATED_EVENT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingEventLogs() throws Exception {
        int databaseSizeBeforeUpdate = eventLogsRepository.findAll().size();

        // Create the EventLogs
        EventLogsDTO eventLogsDTO = eventLogsMapper.toDto(eventLogs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventLogsMockMvc.perform(put("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventLogsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventLogs in the database
        List<EventLogs> eventLogsList = eventLogsRepository.findAll();
        assertThat(eventLogsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventLogs() throws Exception {
        // Initialize the database
        eventLogsRepository.saveAndFlush(eventLogs);

        int databaseSizeBeforeDelete = eventLogsRepository.findAll().size();

        // Delete the eventLogs
        restEventLogsMockMvc.perform(delete("/api/event-logs/{id}", eventLogs.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventLogs> eventLogsList = eventLogsRepository.findAll();
        assertThat(eventLogsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
