package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardDetailsEntityInt;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardDetailsEntityIntRepository;
import com.mycompany.myapp.service.DqStandardDetailsEntityIntService;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityIntMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityIntCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityIntQueryService;

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
 * Integration tests for the {@link DqStandardDetailsEntityIntResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardDetailsEntityIntResourceIT {

    private static final String DEFAULT_STD_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STD_ATTRIBUTE_VALUE = 1;
    private static final Integer UPDATED_STD_ATTRIBUTE_VALUE = 2;
    private static final Integer SMALLER_STD_ATTRIBUTE_VALUE = 1 - 1;

    @Autowired
    private DqStandardDetailsEntityIntRepository dqStandardDetailsEntityIntRepository;

    @Autowired
    private DqStandardDetailsEntityIntMapper dqStandardDetailsEntityIntMapper;

    @Autowired
    private DqStandardDetailsEntityIntService dqStandardDetailsEntityIntService;

    @Autowired
    private DqStandardDetailsEntityIntQueryService dqStandardDetailsEntityIntQueryService;

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

    private MockMvc restDqStandardDetailsEntityIntMockMvc;

    private DqStandardDetailsEntityInt dqStandardDetailsEntityInt;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardDetailsEntityIntResource dqStandardDetailsEntityIntResource = new DqStandardDetailsEntityIntResource(dqStandardDetailsEntityIntService, dqStandardDetailsEntityIntQueryService);
        this.restDqStandardDetailsEntityIntMockMvc = MockMvcBuilders.standaloneSetup(dqStandardDetailsEntityIntResource)
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
    public static DqStandardDetailsEntityInt createEntity(EntityManager em) {
        DqStandardDetailsEntityInt dqStandardDetailsEntityInt = new DqStandardDetailsEntityInt()
            .stdAttributeName(DEFAULT_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(DEFAULT_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityInt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardDetailsEntityInt createUpdatedEntity(EntityManager em) {
        DqStandardDetailsEntityInt dqStandardDetailsEntityInt = new DqStandardDetailsEntityInt()
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityInt;
    }

    @BeforeEach
    public void initTest() {
        dqStandardDetailsEntityInt = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityInt() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityIntRepository.findAll().size();

        // Create the DqStandardDetailsEntityInt
        DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO = dqStandardDetailsEntityIntMapper.toDto(dqStandardDetailsEntityInt);
        restDqStandardDetailsEntityIntMockMvc.perform(post("/api/dq-standard-details-entity-ints")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityIntDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardDetailsEntityInt in the database
        List<DqStandardDetailsEntityInt> dqStandardDetailsEntityIntList = dqStandardDetailsEntityIntRepository.findAll();
        assertThat(dqStandardDetailsEntityIntList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardDetailsEntityInt testDqStandardDetailsEntityInt = dqStandardDetailsEntityIntList.get(dqStandardDetailsEntityIntList.size() - 1);
        assertThat(testDqStandardDetailsEntityInt.getStdAttributeName()).isEqualTo(DEFAULT_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityInt.getStdAttributeValue()).isEqualTo(DEFAULT_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityIntWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityIntRepository.findAll().size();

        // Create the DqStandardDetailsEntityInt with an existing ID
        dqStandardDetailsEntityInt.setId(1L);
        DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO = dqStandardDetailsEntityIntMapper.toDto(dqStandardDetailsEntityInt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardDetailsEntityIntMockMvc.perform(post("/api/dq-standard-details-entity-ints")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityIntDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityInt in the database
        List<DqStandardDetailsEntityInt> dqStandardDetailsEntityIntList = dqStandardDetailsEntityIntRepository.findAll();
        assertThat(dqStandardDetailsEntityIntList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityInts() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityInt.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE)));
    }
    
    @Test
    @Transactional
    public void getDqStandardDetailsEntityInt() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get the dqStandardDetailsEntityInt
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints/{id}", dqStandardDetailsEntityInt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardDetailsEntityInt.getId().intValue()))
            .andExpect(jsonPath("$.stdAttributeName").value(DEFAULT_STD_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.stdAttributeValue").value(DEFAULT_STD_ATTRIBUTE_VALUE));
    }


    @Test
    @Transactional
    public void getDqStandardDetailsEntityIntsByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        Long id = dqStandardDetailsEntityInt.getId();

        defaultDqStandardDetailsEntityIntShouldBeFound("id.equals=" + id);
        defaultDqStandardDetailsEntityIntShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardDetailsEntityIntShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardDetailsEntityIntShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardDetailsEntityIntShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardDetailsEntityIntShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeName.equals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeName.equals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName not equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeName.notEquals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName not equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeName.notEquals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName in DEFAULT_STD_ATTRIBUTE_NAME or UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeName.in=" + DEFAULT_STD_ATTRIBUTE_NAME + "," + UPDATED_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeName.in=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName is not null
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeName.specified=true");

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName is null
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName contains DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeName.contains=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName contains UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeName.contains=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName does not contain DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeName.doesNotContain=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeName does not contain UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeName.doesNotContain=" + UPDATED_STD_ATTRIBUTE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.equals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.equals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue not equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.notEquals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue not equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.notEquals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue in DEFAULT_STD_ATTRIBUTE_VALUE or UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.in=" + DEFAULT_STD_ATTRIBUTE_VALUE + "," + UPDATED_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.in=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is not null
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.specified=true");

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is null
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is greater than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.greaterThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is greater than or equal to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.greaterThanOrEqual=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is less than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.lessThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is less than or equal to SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.lessThanOrEqual=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsLessThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is less than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.lessThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is less than UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.lessThan=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdAttributeValueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is greater than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdAttributeValue.greaterThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityIntList where stdAttributeValue is greater than SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityIntShouldBeFound("stdAttributeValue.greaterThan=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityIntsByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqStandardDetailsEntityInt.setStd(std);
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);
        Long stdId = std.getId();

        // Get all the dqStandardDetailsEntityIntList where std equals to stdId
        defaultDqStandardDetailsEntityIntShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqStandardDetailsEntityIntList where std equals to stdId + 1
        defaultDqStandardDetailsEntityIntShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardDetailsEntityIntShouldBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityInt.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE)));

        // Check, that the count call also returns 1
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardDetailsEntityIntShouldNotBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardDetailsEntityInt() throws Exception {
        // Get the dqStandardDetailsEntityInt
        restDqStandardDetailsEntityIntMockMvc.perform(get("/api/dq-standard-details-entity-ints/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardDetailsEntityInt() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        int databaseSizeBeforeUpdate = dqStandardDetailsEntityIntRepository.findAll().size();

        // Update the dqStandardDetailsEntityInt
        DqStandardDetailsEntityInt updatedDqStandardDetailsEntityInt = dqStandardDetailsEntityIntRepository.findById(dqStandardDetailsEntityInt.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardDetailsEntityInt are not directly saved in db
        em.detach(updatedDqStandardDetailsEntityInt);
        updatedDqStandardDetailsEntityInt
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO = dqStandardDetailsEntityIntMapper.toDto(updatedDqStandardDetailsEntityInt);

        restDqStandardDetailsEntityIntMockMvc.perform(put("/api/dq-standard-details-entity-ints")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityIntDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardDetailsEntityInt in the database
        List<DqStandardDetailsEntityInt> dqStandardDetailsEntityIntList = dqStandardDetailsEntityIntRepository.findAll();
        assertThat(dqStandardDetailsEntityIntList).hasSize(databaseSizeBeforeUpdate);
        DqStandardDetailsEntityInt testDqStandardDetailsEntityInt = dqStandardDetailsEntityIntList.get(dqStandardDetailsEntityIntList.size() - 1);
        assertThat(testDqStandardDetailsEntityInt.getStdAttributeName()).isEqualTo(UPDATED_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityInt.getStdAttributeValue()).isEqualTo(UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardDetailsEntityInt() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardDetailsEntityIntRepository.findAll().size();

        // Create the DqStandardDetailsEntityInt
        DqStandardDetailsEntityIntDTO dqStandardDetailsEntityIntDTO = dqStandardDetailsEntityIntMapper.toDto(dqStandardDetailsEntityInt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardDetailsEntityIntMockMvc.perform(put("/api/dq-standard-details-entity-ints")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityIntDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityInt in the database
        List<DqStandardDetailsEntityInt> dqStandardDetailsEntityIntList = dqStandardDetailsEntityIntRepository.findAll();
        assertThat(dqStandardDetailsEntityIntList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardDetailsEntityInt() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityIntRepository.saveAndFlush(dqStandardDetailsEntityInt);

        int databaseSizeBeforeDelete = dqStandardDetailsEntityIntRepository.findAll().size();

        // Delete the dqStandardDetailsEntityInt
        restDqStandardDetailsEntityIntMockMvc.perform(delete("/api/dq-standard-details-entity-ints/{id}", dqStandardDetailsEntityInt.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardDetailsEntityInt> dqStandardDetailsEntityIntList = dqStandardDetailsEntityIntRepository.findAll();
        assertThat(dqStandardDetailsEntityIntList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
