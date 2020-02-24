package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardDetailsEntityDatetime;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardDetailsEntityDatetimeRepository;
import com.mycompany.myapp.service.DqStandardDetailsEntityDatetimeService;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityDatetimeMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDatetimeCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityDatetimeQueryService;

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
 * Integration tests for the {@link DqStandardDetailsEntityDatetimeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardDetailsEntityDatetimeResourceIT {

    private static final String DEFAULT_STD_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_STD_ATTRIBUTE_VALUE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STD_ATTRIBUTE_VALUE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DqStandardDetailsEntityDatetimeRepository dqStandardDetailsEntityDatetimeRepository;

    @Autowired
    private DqStandardDetailsEntityDatetimeMapper dqStandardDetailsEntityDatetimeMapper;

    @Autowired
    private DqStandardDetailsEntityDatetimeService dqStandardDetailsEntityDatetimeService;

    @Autowired
    private DqStandardDetailsEntityDatetimeQueryService dqStandardDetailsEntityDatetimeQueryService;

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

    private MockMvc restDqStandardDetailsEntityDatetimeMockMvc;

    private DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardDetailsEntityDatetimeResource dqStandardDetailsEntityDatetimeResource = new DqStandardDetailsEntityDatetimeResource(dqStandardDetailsEntityDatetimeService, dqStandardDetailsEntityDatetimeQueryService);
        this.restDqStandardDetailsEntityDatetimeMockMvc = MockMvcBuilders.standaloneSetup(dqStandardDetailsEntityDatetimeResource)
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
    public static DqStandardDetailsEntityDatetime createEntity(EntityManager em) {
        DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime = new DqStandardDetailsEntityDatetime()
            .stdAttributeName(DEFAULT_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(DEFAULT_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityDatetime;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardDetailsEntityDatetime createUpdatedEntity(EntityManager em) {
        DqStandardDetailsEntityDatetime dqStandardDetailsEntityDatetime = new DqStandardDetailsEntityDatetime()
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityDatetime;
    }

    @BeforeEach
    public void initTest() {
        dqStandardDetailsEntityDatetime = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityDatetime() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityDatetimeRepository.findAll().size();

        // Create the DqStandardDetailsEntityDatetime
        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO = dqStandardDetailsEntityDatetimeMapper.toDto(dqStandardDetailsEntityDatetime);
        restDqStandardDetailsEntityDatetimeMockMvc.perform(post("/api/dq-standard-details-entity-datetimes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDatetimeDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardDetailsEntityDatetime in the database
        List<DqStandardDetailsEntityDatetime> dqStandardDetailsEntityDatetimeList = dqStandardDetailsEntityDatetimeRepository.findAll();
        assertThat(dqStandardDetailsEntityDatetimeList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardDetailsEntityDatetime testDqStandardDetailsEntityDatetime = dqStandardDetailsEntityDatetimeList.get(dqStandardDetailsEntityDatetimeList.size() - 1);
        assertThat(testDqStandardDetailsEntityDatetime.getStdAttributeName()).isEqualTo(DEFAULT_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityDatetime.getStdAttributeValue()).isEqualTo(DEFAULT_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityDatetimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityDatetimeRepository.findAll().size();

        // Create the DqStandardDetailsEntityDatetime with an existing ID
        dqStandardDetailsEntityDatetime.setId(1L);
        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO = dqStandardDetailsEntityDatetimeMapper.toDto(dqStandardDetailsEntityDatetime);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardDetailsEntityDatetimeMockMvc.perform(post("/api/dq-standard-details-entity-datetimes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDatetimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityDatetime in the database
        List<DqStandardDetailsEntityDatetime> dqStandardDetailsEntityDatetimeList = dqStandardDetailsEntityDatetimeRepository.findAll();
        assertThat(dqStandardDetailsEntityDatetimeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimes() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityDatetime.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getDqStandardDetailsEntityDatetime() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get the dqStandardDetailsEntityDatetime
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes/{id}", dqStandardDetailsEntityDatetime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardDetailsEntityDatetime.getId().intValue()))
            .andExpect(jsonPath("$.stdAttributeName").value(DEFAULT_STD_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.stdAttributeValue").value(DEFAULT_STD_ATTRIBUTE_VALUE.toString()));
    }


    @Test
    @Transactional
    public void getDqStandardDetailsEntityDatetimesByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        Long id = dqStandardDetailsEntityDatetime.getId();

        defaultDqStandardDetailsEntityDatetimeShouldBeFound("id.equals=" + id);
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardDetailsEntityDatetimeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardDetailsEntityDatetimeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeName.equals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeName.equals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName not equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeName.notEquals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName not equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeName.notEquals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName in DEFAULT_STD_ATTRIBUTE_NAME or UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeName.in=" + DEFAULT_STD_ATTRIBUTE_NAME + "," + UPDATED_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeName.in=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName is not null
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeName.specified=true");

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName is null
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName contains DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeName.contains=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName contains UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeName.contains=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName does not contain DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeName.doesNotContain=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeName does not contain UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeName.doesNotContain=" + UPDATED_STD_ATTRIBUTE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeValue.equals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeValue.equals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue not equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeValue.notEquals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue not equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeValue.notEquals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeValueIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue in DEFAULT_STD_ATTRIBUTE_VALUE or UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeValue.in=" + DEFAULT_STD_ATTRIBUTE_VALUE + "," + UPDATED_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeValue.in=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdAttributeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue is not null
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdAttributeValue.specified=true");

        // Get all the dqStandardDetailsEntityDatetimeList where stdAttributeValue is null
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdAttributeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDatetimesByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqStandardDetailsEntityDatetime.setStd(std);
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);
        Long stdId = std.getId();

        // Get all the dqStandardDetailsEntityDatetimeList where std equals to stdId
        defaultDqStandardDetailsEntityDatetimeShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqStandardDetailsEntityDatetimeList where std equals to stdId + 1
        defaultDqStandardDetailsEntityDatetimeShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardDetailsEntityDatetimeShouldBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityDatetime.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.toString())));

        // Check, that the count call also returns 1
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardDetailsEntityDatetimeShouldNotBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardDetailsEntityDatetime() throws Exception {
        // Get the dqStandardDetailsEntityDatetime
        restDqStandardDetailsEntityDatetimeMockMvc.perform(get("/api/dq-standard-details-entity-datetimes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardDetailsEntityDatetime() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        int databaseSizeBeforeUpdate = dqStandardDetailsEntityDatetimeRepository.findAll().size();

        // Update the dqStandardDetailsEntityDatetime
        DqStandardDetailsEntityDatetime updatedDqStandardDetailsEntityDatetime = dqStandardDetailsEntityDatetimeRepository.findById(dqStandardDetailsEntityDatetime.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardDetailsEntityDatetime are not directly saved in db
        em.detach(updatedDqStandardDetailsEntityDatetime);
        updatedDqStandardDetailsEntityDatetime
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO = dqStandardDetailsEntityDatetimeMapper.toDto(updatedDqStandardDetailsEntityDatetime);

        restDqStandardDetailsEntityDatetimeMockMvc.perform(put("/api/dq-standard-details-entity-datetimes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDatetimeDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardDetailsEntityDatetime in the database
        List<DqStandardDetailsEntityDatetime> dqStandardDetailsEntityDatetimeList = dqStandardDetailsEntityDatetimeRepository.findAll();
        assertThat(dqStandardDetailsEntityDatetimeList).hasSize(databaseSizeBeforeUpdate);
        DqStandardDetailsEntityDatetime testDqStandardDetailsEntityDatetime = dqStandardDetailsEntityDatetimeList.get(dqStandardDetailsEntityDatetimeList.size() - 1);
        assertThat(testDqStandardDetailsEntityDatetime.getStdAttributeName()).isEqualTo(UPDATED_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityDatetime.getStdAttributeValue()).isEqualTo(UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardDetailsEntityDatetime() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardDetailsEntityDatetimeRepository.findAll().size();

        // Create the DqStandardDetailsEntityDatetime
        DqStandardDetailsEntityDatetimeDTO dqStandardDetailsEntityDatetimeDTO = dqStandardDetailsEntityDatetimeMapper.toDto(dqStandardDetailsEntityDatetime);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardDetailsEntityDatetimeMockMvc.perform(put("/api/dq-standard-details-entity-datetimes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDatetimeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityDatetime in the database
        List<DqStandardDetailsEntityDatetime> dqStandardDetailsEntityDatetimeList = dqStandardDetailsEntityDatetimeRepository.findAll();
        assertThat(dqStandardDetailsEntityDatetimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardDetailsEntityDatetime() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDatetimeRepository.saveAndFlush(dqStandardDetailsEntityDatetime);

        int databaseSizeBeforeDelete = dqStandardDetailsEntityDatetimeRepository.findAll().size();

        // Delete the dqStandardDetailsEntityDatetime
        restDqStandardDetailsEntityDatetimeMockMvc.perform(delete("/api/dq-standard-details-entity-datetimes/{id}", dqStandardDetailsEntityDatetime.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardDetailsEntityDatetime> dqStandardDetailsEntityDatetimeList = dqStandardDetailsEntityDatetimeRepository.findAll();
        assertThat(dqStandardDetailsEntityDatetimeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
