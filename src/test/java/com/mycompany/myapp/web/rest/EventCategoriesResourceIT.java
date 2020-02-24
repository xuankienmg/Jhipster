package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.EventCategories;
import com.mycompany.myapp.repository.EventCategoriesRepository;
import com.mycompany.myapp.service.EventCategoriesService;
import com.mycompany.myapp.service.dto.EventCategoriesDTO;
import com.mycompany.myapp.service.mapper.EventCategoriesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.EventCategoriesCriteria;
import com.mycompany.myapp.service.EventCategoriesQueryService;

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
 * Integration tests for the {@link EventCategoriesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class EventCategoriesResourceIT {

    private static final String DEFAULT_EVENT_CAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_CAT_NAME = "BBBBBBBBBB";

    @Autowired
    private EventCategoriesRepository eventCategoriesRepository;

    @Autowired
    private EventCategoriesMapper eventCategoriesMapper;

    @Autowired
    private EventCategoriesService eventCategoriesService;

    @Autowired
    private EventCategoriesQueryService eventCategoriesQueryService;

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

    private MockMvc restEventCategoriesMockMvc;

    private EventCategories eventCategories;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventCategoriesResource eventCategoriesResource = new EventCategoriesResource(eventCategoriesService, eventCategoriesQueryService);
        this.restEventCategoriesMockMvc = MockMvcBuilders.standaloneSetup(eventCategoriesResource)
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
    public static EventCategories createEntity(EntityManager em) {
        EventCategories eventCategories = new EventCategories()
            .eventCatName(DEFAULT_EVENT_CAT_NAME);
        return eventCategories;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventCategories createUpdatedEntity(EntityManager em) {
        EventCategories eventCategories = new EventCategories()
            .eventCatName(UPDATED_EVENT_CAT_NAME);
        return eventCategories;
    }

    @BeforeEach
    public void initTest() {
        eventCategories = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventCategories() throws Exception {
        int databaseSizeBeforeCreate = eventCategoriesRepository.findAll().size();

        // Create the EventCategories
        EventCategoriesDTO eventCategoriesDTO = eventCategoriesMapper.toDto(eventCategories);
        restEventCategoriesMockMvc.perform(post("/api/event-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventCategoriesDTO)))
            .andExpect(status().isCreated());

        // Validate the EventCategories in the database
        List<EventCategories> eventCategoriesList = eventCategoriesRepository.findAll();
        assertThat(eventCategoriesList).hasSize(databaseSizeBeforeCreate + 1);
        EventCategories testEventCategories = eventCategoriesList.get(eventCategoriesList.size() - 1);
        assertThat(testEventCategories.getEventCatName()).isEqualTo(DEFAULT_EVENT_CAT_NAME);
    }

    @Test
    @Transactional
    public void createEventCategoriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventCategoriesRepository.findAll().size();

        // Create the EventCategories with an existing ID
        eventCategories.setId(1L);
        EventCategoriesDTO eventCategoriesDTO = eventCategoriesMapper.toDto(eventCategories);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventCategoriesMockMvc.perform(post("/api/event-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventCategoriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventCategories in the database
        List<EventCategories> eventCategoriesList = eventCategoriesRepository.findAll();
        assertThat(eventCategoriesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEventCategories() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList
        restEventCategoriesMockMvc.perform(get("/api/event-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventCategories.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventCatName").value(hasItem(DEFAULT_EVENT_CAT_NAME)));
    }
    
    @Test
    @Transactional
    public void getEventCategories() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get the eventCategories
        restEventCategoriesMockMvc.perform(get("/api/event-categories/{id}", eventCategories.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventCategories.getId().intValue()))
            .andExpect(jsonPath("$.eventCatName").value(DEFAULT_EVENT_CAT_NAME));
    }


    @Test
    @Transactional
    public void getEventCategoriesByIdFiltering() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        Long id = eventCategories.getId();

        defaultEventCategoriesShouldBeFound("id.equals=" + id);
        defaultEventCategoriesShouldNotBeFound("id.notEquals=" + id);

        defaultEventCategoriesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEventCategoriesShouldNotBeFound("id.greaterThan=" + id);

        defaultEventCategoriesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEventCategoriesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEventCategoriesByEventCatNameIsEqualToSomething() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList where eventCatName equals to DEFAULT_EVENT_CAT_NAME
        defaultEventCategoriesShouldBeFound("eventCatName.equals=" + DEFAULT_EVENT_CAT_NAME);

        // Get all the eventCategoriesList where eventCatName equals to UPDATED_EVENT_CAT_NAME
        defaultEventCategoriesShouldNotBeFound("eventCatName.equals=" + UPDATED_EVENT_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventCategoriesByEventCatNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList where eventCatName not equals to DEFAULT_EVENT_CAT_NAME
        defaultEventCategoriesShouldNotBeFound("eventCatName.notEquals=" + DEFAULT_EVENT_CAT_NAME);

        // Get all the eventCategoriesList where eventCatName not equals to UPDATED_EVENT_CAT_NAME
        defaultEventCategoriesShouldBeFound("eventCatName.notEquals=" + UPDATED_EVENT_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventCategoriesByEventCatNameIsInShouldWork() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList where eventCatName in DEFAULT_EVENT_CAT_NAME or UPDATED_EVENT_CAT_NAME
        defaultEventCategoriesShouldBeFound("eventCatName.in=" + DEFAULT_EVENT_CAT_NAME + "," + UPDATED_EVENT_CAT_NAME);

        // Get all the eventCategoriesList where eventCatName equals to UPDATED_EVENT_CAT_NAME
        defaultEventCategoriesShouldNotBeFound("eventCatName.in=" + UPDATED_EVENT_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventCategoriesByEventCatNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList where eventCatName is not null
        defaultEventCategoriesShouldBeFound("eventCatName.specified=true");

        // Get all the eventCategoriesList where eventCatName is null
        defaultEventCategoriesShouldNotBeFound("eventCatName.specified=false");
    }
                @Test
    @Transactional
    public void getAllEventCategoriesByEventCatNameContainsSomething() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList where eventCatName contains DEFAULT_EVENT_CAT_NAME
        defaultEventCategoriesShouldBeFound("eventCatName.contains=" + DEFAULT_EVENT_CAT_NAME);

        // Get all the eventCategoriesList where eventCatName contains UPDATED_EVENT_CAT_NAME
        defaultEventCategoriesShouldNotBeFound("eventCatName.contains=" + UPDATED_EVENT_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllEventCategoriesByEventCatNameNotContainsSomething() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        // Get all the eventCategoriesList where eventCatName does not contain DEFAULT_EVENT_CAT_NAME
        defaultEventCategoriesShouldNotBeFound("eventCatName.doesNotContain=" + DEFAULT_EVENT_CAT_NAME);

        // Get all the eventCategoriesList where eventCatName does not contain UPDATED_EVENT_CAT_NAME
        defaultEventCategoriesShouldBeFound("eventCatName.doesNotContain=" + UPDATED_EVENT_CAT_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEventCategoriesShouldBeFound(String filter) throws Exception {
        restEventCategoriesMockMvc.perform(get("/api/event-categories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventCategories.getId().intValue())))
            .andExpect(jsonPath("$.[*].eventCatName").value(hasItem(DEFAULT_EVENT_CAT_NAME)));

        // Check, that the count call also returns 1
        restEventCategoriesMockMvc.perform(get("/api/event-categories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEventCategoriesShouldNotBeFound(String filter) throws Exception {
        restEventCategoriesMockMvc.perform(get("/api/event-categories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEventCategoriesMockMvc.perform(get("/api/event-categories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEventCategories() throws Exception {
        // Get the eventCategories
        restEventCategoriesMockMvc.perform(get("/api/event-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventCategories() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        int databaseSizeBeforeUpdate = eventCategoriesRepository.findAll().size();

        // Update the eventCategories
        EventCategories updatedEventCategories = eventCategoriesRepository.findById(eventCategories.getId()).get();
        // Disconnect from session so that the updates on updatedEventCategories are not directly saved in db
        em.detach(updatedEventCategories);
        updatedEventCategories
            .eventCatName(UPDATED_EVENT_CAT_NAME);
        EventCategoriesDTO eventCategoriesDTO = eventCategoriesMapper.toDto(updatedEventCategories);

        restEventCategoriesMockMvc.perform(put("/api/event-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventCategoriesDTO)))
            .andExpect(status().isOk());

        // Validate the EventCategories in the database
        List<EventCategories> eventCategoriesList = eventCategoriesRepository.findAll();
        assertThat(eventCategoriesList).hasSize(databaseSizeBeforeUpdate);
        EventCategories testEventCategories = eventCategoriesList.get(eventCategoriesList.size() - 1);
        assertThat(testEventCategories.getEventCatName()).isEqualTo(UPDATED_EVENT_CAT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEventCategories() throws Exception {
        int databaseSizeBeforeUpdate = eventCategoriesRepository.findAll().size();

        // Create the EventCategories
        EventCategoriesDTO eventCategoriesDTO = eventCategoriesMapper.toDto(eventCategories);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventCategoriesMockMvc.perform(put("/api/event-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventCategoriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventCategories in the database
        List<EventCategories> eventCategoriesList = eventCategoriesRepository.findAll();
        assertThat(eventCategoriesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventCategories() throws Exception {
        // Initialize the database
        eventCategoriesRepository.saveAndFlush(eventCategories);

        int databaseSizeBeforeDelete = eventCategoriesRepository.findAll().size();

        // Delete the eventCategories
        restEventCategoriesMockMvc.perform(delete("/api/event-categories/{id}", eventCategories.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventCategories> eventCategoriesList = eventCategoriesRepository.findAll();
        assertThat(eventCategoriesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
