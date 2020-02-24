package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.EventTypes;
import com.mycompany.myapp.repository.EventTypesRepository;
import com.mycompany.myapp.service.EventTypesService;
import com.mycompany.myapp.service.dto.EventTypesDTO;
import com.mycompany.myapp.service.mapper.EventTypesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.EventTypesCriteria;
import com.mycompany.myapp.service.EventTypesQueryService;

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
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EventTypesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class EventTypesResourceIT {

    private static final String DEFAULT_EVENT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_TYPE_NAME = "BBBBBBBBBB";

    @Autowired
    private EventTypesRepository eventTypesRepository;

    @Autowired
    private EventTypesMapper eventTypesMapper;

    @Autowired
    private EventTypesService eventTypesService;

    @Autowired
    private EventTypesQueryService eventTypesQueryService;

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

    private MockMvc restEventTypesMockMvc;

    private EventTypes eventTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventTypesResource eventTypesResource = new EventTypesResource(eventTypesService, eventTypesQueryService);
        this.restEventTypesMockMvc = MockMvcBuilders.standaloneSetup(eventTypesResource)
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
    public static EventTypes createEntity(EntityManager em) {
        EventTypes eventTypes = new EventTypes()
            .eventTypeName(DEFAULT_EVENT_TYPE_NAME);
        return eventTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventTypes createUpdatedEntity(EntityManager em) {
        EventTypes eventTypes = new EventTypes()
            .eventTypeName(UPDATED_EVENT_TYPE_NAME);
        return eventTypes;
    }

    @BeforeEach
    public void initTest() {
        eventTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventTypes() throws Exception {
        int databaseSizeBeforeCreate = eventTypesRepository.findAll().size();

        // Create the EventTypes
        EventTypesDTO eventTypesDTO = eventTypesMapper.toDto(eventTypes);
        restEventTypesMockMvc.perform(post("/api/event-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the EventTypes in the database
        List<EventTypes> eventTypesList = eventTypesRepository.findAll();
        assertThat(eventTypesList).hasSize(databaseSizeBeforeCreate + 1);
        EventTypes testEventTypes = eventTypesList.get(eventTypesList.size() - 1);
        assertThat(testEventTypes.getEventTypeName()).isEqualTo(DEFAULT_EVENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void createEventTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventTypesRepository.findAll().size();

        // Create the EventTypes with an existing ID
        eventTypes.setId(1L);
        EventTypesDTO eventTypesDTO = eventTypesMapper.toDto(eventTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventTypesMockMvc.perform(post("/api/event-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventTypes in the database
        List<EventTypes> eventTypesList = eventTypesRepository.findAll();
        assertThat(eventTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEventTypes() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList
        restEventTypesMockMvc.perform(get("/api/event-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventTypeName").value(hasItem(DEFAULT_EVENT_TYPE_NAME)));
    }
    
    @Test
    @Transactional
    public void getEventTypes() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get the eventTypes
        restEventTypesMockMvc.perform(get("/api/event-types/{id}", eventTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventTypes.getId().intValue()))
            .andExpect(jsonPath("$.eventTypeName").value(DEFAULT_EVENT_TYPE_NAME));
    }


    @Test
    @Transactional
    public void getEventTypesByIdFiltering() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        Long id = eventTypes.getId();

        defaultEventTypesShouldBeFound("id.equals=" + id);
        defaultEventTypesShouldNotBeFound("id.notEquals=" + id);

        defaultEventTypesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEventTypesShouldNotBeFound("id.greaterThan=" + id);

        defaultEventTypesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEventTypesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEventTypesByEventTypeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList where eventTypeName equals to DEFAULT_EVENT_TYPE_NAME
        defaultEventTypesShouldBeFound("eventTypeName.equals=" + DEFAULT_EVENT_TYPE_NAME);

        // Get all the eventTypesList where eventTypeName equals to UPDATED_EVENT_TYPE_NAME
        defaultEventTypesShouldNotBeFound("eventTypeName.equals=" + UPDATED_EVENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllEventTypesByEventTypeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList where eventTypeName not equals to DEFAULT_EVENT_TYPE_NAME
        defaultEventTypesShouldNotBeFound("eventTypeName.notEquals=" + DEFAULT_EVENT_TYPE_NAME);

        // Get all the eventTypesList where eventTypeName not equals to UPDATED_EVENT_TYPE_NAME
        defaultEventTypesShouldBeFound("eventTypeName.notEquals=" + UPDATED_EVENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllEventTypesByEventTypeNameIsInShouldWork() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList where eventTypeName in DEFAULT_EVENT_TYPE_NAME or UPDATED_EVENT_TYPE_NAME
        defaultEventTypesShouldBeFound("eventTypeName.in=" + DEFAULT_EVENT_TYPE_NAME + "," + UPDATED_EVENT_TYPE_NAME);

        // Get all the eventTypesList where eventTypeName equals to UPDATED_EVENT_TYPE_NAME
        defaultEventTypesShouldNotBeFound("eventTypeName.in=" + UPDATED_EVENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllEventTypesByEventTypeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList where eventTypeName is not null
        defaultEventTypesShouldBeFound("eventTypeName.specified=true");

        // Get all the eventTypesList where eventTypeName is null
        defaultEventTypesShouldNotBeFound("eventTypeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventTypesByEventTypeNameContainsSomething() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList where eventTypeName contains DEFAULT_EVENT_TYPE_NAME
        defaultEventTypesShouldBeFound("eventTypeName.contains=" + DEFAULT_EVENT_TYPE_NAME);

        // Get all the eventTypesList where eventTypeName contains UPDATED_EVENT_TYPE_NAME
        defaultEventTypesShouldNotBeFound("eventTypeName.contains=" + UPDATED_EVENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void getAllEventTypesByEventTypeNameNotContainsSomething() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        // Get all the eventTypesList where eventTypeName does not contain DEFAULT_EVENT_TYPE_NAME
        defaultEventTypesShouldNotBeFound("eventTypeName.doesNotContain=" + DEFAULT_EVENT_TYPE_NAME);

        // Get all the eventTypesList where eventTypeName does not contain UPDATED_EVENT_TYPE_NAME
        defaultEventTypesShouldBeFound("eventTypeName.doesNotContain=" + UPDATED_EVENT_TYPE_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEventTypesShouldBeFound(String filter) throws Exception {
        restEventTypesMockMvc.perform(get("/api/event-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventTypeName").value(hasItem(DEFAULT_EVENT_TYPE_NAME)));

        // Check, that the count call also returns 1
        restEventTypesMockMvc.perform(get("/api/event-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEventTypesShouldNotBeFound(String filter) throws Exception {
        restEventTypesMockMvc.perform(get("/api/event-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEventTypesMockMvc.perform(get("/api/event-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEventTypes() throws Exception {
        // Get the eventTypes
        restEventTypesMockMvc.perform(get("/api/event-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventTypes() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        int databaseSizeBeforeUpdate = eventTypesRepository.findAll().size();

        // Update the eventTypes
        EventTypes updatedEventTypes = eventTypesRepository.findById(eventTypes.getId()).get();
        // Disconnect from session so that the updates on updatedEventTypes are not directly saved in db
        em.detach(updatedEventTypes);
        updatedEventTypes
            .eventTypeName(UPDATED_EVENT_TYPE_NAME);
        EventTypesDTO eventTypesDTO = eventTypesMapper.toDto(updatedEventTypes);

        restEventTypesMockMvc.perform(put("/api/event-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventTypesDTO)))
            .andExpect(status().isOk());

        // Validate the EventTypes in the database
        List<EventTypes> eventTypesList = eventTypesRepository.findAll();
        assertThat(eventTypesList).hasSize(databaseSizeBeforeUpdate);
        EventTypes testEventTypes = eventTypesList.get(eventTypesList.size() - 1);
        assertThat(testEventTypes.getEventTypeName()).isEqualTo(UPDATED_EVENT_TYPE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEventTypes() throws Exception {
        int databaseSizeBeforeUpdate = eventTypesRepository.findAll().size();

        // Create the EventTypes
        EventTypesDTO eventTypesDTO = eventTypesMapper.toDto(eventTypes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventTypesMockMvc.perform(put("/api/event-types")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventTypes in the database
        List<EventTypes> eventTypesList = eventTypesRepository.findAll();
        assertThat(eventTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventTypes() throws Exception {
        // Initialize the database
        eventTypesRepository.saveAndFlush(eventTypes);

        int databaseSizeBeforeDelete = eventTypesRepository.findAll().size();

        // Delete the eventTypes
        restEventTypesMockMvc.perform(delete("/api/event-types/{id}", eventTypes.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventTypes> eventTypesList = eventTypesRepository.findAll();
        assertThat(eventTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
