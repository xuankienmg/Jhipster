package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardDetailsEntityDecimal;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardDetailsEntityDecimalRepository;
import com.mycompany.myapp.service.DqStandardDetailsEntityDecimalService;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityDecimalMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityDecimalCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityDecimalQueryService;

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
 * Integration tests for the {@link DqStandardDetailsEntityDecimalResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardDetailsEntityDecimalResourceIT {

    private static final String DEFAULT_STD_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STD_ATTRIBUTE_VALUE = 1L;
    private static final Long UPDATED_STD_ATTRIBUTE_VALUE = 2L;
    private static final Long SMALLER_STD_ATTRIBUTE_VALUE = 1L - 1L;

    @Autowired
    private DqStandardDetailsEntityDecimalRepository dqStandardDetailsEntityDecimalRepository;

    @Autowired
    private DqStandardDetailsEntityDecimalMapper dqStandardDetailsEntityDecimalMapper;

    @Autowired
    private DqStandardDetailsEntityDecimalService dqStandardDetailsEntityDecimalService;

    @Autowired
    private DqStandardDetailsEntityDecimalQueryService dqStandardDetailsEntityDecimalQueryService;

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

    private MockMvc restDqStandardDetailsEntityDecimalMockMvc;

    private DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardDetailsEntityDecimalResource dqStandardDetailsEntityDecimalResource = new DqStandardDetailsEntityDecimalResource(dqStandardDetailsEntityDecimalService, dqStandardDetailsEntityDecimalQueryService);
        this.restDqStandardDetailsEntityDecimalMockMvc = MockMvcBuilders.standaloneSetup(dqStandardDetailsEntityDecimalResource)
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
    public static DqStandardDetailsEntityDecimal createEntity(EntityManager em) {
        DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal = new DqStandardDetailsEntityDecimal()
            .stdAttributeName(DEFAULT_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(DEFAULT_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityDecimal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardDetailsEntityDecimal createUpdatedEntity(EntityManager em) {
        DqStandardDetailsEntityDecimal dqStandardDetailsEntityDecimal = new DqStandardDetailsEntityDecimal()
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityDecimal;
    }

    @BeforeEach
    public void initTest() {
        dqStandardDetailsEntityDecimal = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityDecimal() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityDecimalRepository.findAll().size();

        // Create the DqStandardDetailsEntityDecimal
        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO = dqStandardDetailsEntityDecimalMapper.toDto(dqStandardDetailsEntityDecimal);
        restDqStandardDetailsEntityDecimalMockMvc.perform(post("/api/dq-standard-details-entity-decimals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDecimalDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardDetailsEntityDecimal in the database
        List<DqStandardDetailsEntityDecimal> dqStandardDetailsEntityDecimalList = dqStandardDetailsEntityDecimalRepository.findAll();
        assertThat(dqStandardDetailsEntityDecimalList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardDetailsEntityDecimal testDqStandardDetailsEntityDecimal = dqStandardDetailsEntityDecimalList.get(dqStandardDetailsEntityDecimalList.size() - 1);
        assertThat(testDqStandardDetailsEntityDecimal.getStdAttributeName()).isEqualTo(DEFAULT_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityDecimal.getStdAttributeValue()).isEqualTo(DEFAULT_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityDecimalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityDecimalRepository.findAll().size();

        // Create the DqStandardDetailsEntityDecimal with an existing ID
        dqStandardDetailsEntityDecimal.setId(1L);
        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO = dqStandardDetailsEntityDecimalMapper.toDto(dqStandardDetailsEntityDecimal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardDetailsEntityDecimalMockMvc.perform(post("/api/dq-standard-details-entity-decimals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDecimalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityDecimal in the database
        List<DqStandardDetailsEntityDecimal> dqStandardDetailsEntityDecimalList = dqStandardDetailsEntityDecimalRepository.findAll();
        assertThat(dqStandardDetailsEntityDecimalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimals() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityDecimal.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.intValue())));
    }
    
    @Test
    @Transactional
    public void getDqStandardDetailsEntityDecimal() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get the dqStandardDetailsEntityDecimal
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals/{id}", dqStandardDetailsEntityDecimal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardDetailsEntityDecimal.getId().intValue()))
            .andExpect(jsonPath("$.stdAttributeName").value(DEFAULT_STD_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.stdAttributeValue").value(DEFAULT_STD_ATTRIBUTE_VALUE.intValue()));
    }


    @Test
    @Transactional
    public void getDqStandardDetailsEntityDecimalsByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        Long id = dqStandardDetailsEntityDecimal.getId();

        defaultDqStandardDetailsEntityDecimalShouldBeFound("id.equals=" + id);
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardDetailsEntityDecimalShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardDetailsEntityDecimalShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeName.equals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeName.equals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName not equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeName.notEquals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName not equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeName.notEquals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName in DEFAULT_STD_ATTRIBUTE_NAME or UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeName.in=" + DEFAULT_STD_ATTRIBUTE_NAME + "," + UPDATED_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeName.in=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName is not null
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeName.specified=true");

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName is null
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName contains DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeName.contains=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName contains UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeName.contains=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName does not contain DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeName.doesNotContain=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeName does not contain UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeName.doesNotContain=" + UPDATED_STD_ATTRIBUTE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.equals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.equals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue not equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.notEquals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue not equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.notEquals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue in DEFAULT_STD_ATTRIBUTE_VALUE or UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.in=" + DEFAULT_STD_ATTRIBUTE_VALUE + "," + UPDATED_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.in=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is not null
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.specified=true");

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is null
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is greater than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.greaterThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is greater than or equal to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.greaterThanOrEqual=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is less than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.lessThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is less than or equal to SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.lessThanOrEqual=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsLessThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is less than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.lessThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is less than UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.lessThan=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdAttributeValueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is greater than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdAttributeValue.greaterThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityDecimalList where stdAttributeValue is greater than SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdAttributeValue.greaterThan=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityDecimalsByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqStandardDetailsEntityDecimal.setStd(std);
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);
        Long stdId = std.getId();

        // Get all the dqStandardDetailsEntityDecimalList where std equals to stdId
        defaultDqStandardDetailsEntityDecimalShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqStandardDetailsEntityDecimalList where std equals to stdId + 1
        defaultDqStandardDetailsEntityDecimalShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardDetailsEntityDecimalShouldBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityDecimal.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.intValue())));

        // Check, that the count call also returns 1
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardDetailsEntityDecimalShouldNotBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardDetailsEntityDecimal() throws Exception {
        // Get the dqStandardDetailsEntityDecimal
        restDqStandardDetailsEntityDecimalMockMvc.perform(get("/api/dq-standard-details-entity-decimals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardDetailsEntityDecimal() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        int databaseSizeBeforeUpdate = dqStandardDetailsEntityDecimalRepository.findAll().size();

        // Update the dqStandardDetailsEntityDecimal
        DqStandardDetailsEntityDecimal updatedDqStandardDetailsEntityDecimal = dqStandardDetailsEntityDecimalRepository.findById(dqStandardDetailsEntityDecimal.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardDetailsEntityDecimal are not directly saved in db
        em.detach(updatedDqStandardDetailsEntityDecimal);
        updatedDqStandardDetailsEntityDecimal
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO = dqStandardDetailsEntityDecimalMapper.toDto(updatedDqStandardDetailsEntityDecimal);

        restDqStandardDetailsEntityDecimalMockMvc.perform(put("/api/dq-standard-details-entity-decimals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDecimalDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardDetailsEntityDecimal in the database
        List<DqStandardDetailsEntityDecimal> dqStandardDetailsEntityDecimalList = dqStandardDetailsEntityDecimalRepository.findAll();
        assertThat(dqStandardDetailsEntityDecimalList).hasSize(databaseSizeBeforeUpdate);
        DqStandardDetailsEntityDecimal testDqStandardDetailsEntityDecimal = dqStandardDetailsEntityDecimalList.get(dqStandardDetailsEntityDecimalList.size() - 1);
        assertThat(testDqStandardDetailsEntityDecimal.getStdAttributeName()).isEqualTo(UPDATED_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityDecimal.getStdAttributeValue()).isEqualTo(UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardDetailsEntityDecimal() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardDetailsEntityDecimalRepository.findAll().size();

        // Create the DqStandardDetailsEntityDecimal
        DqStandardDetailsEntityDecimalDTO dqStandardDetailsEntityDecimalDTO = dqStandardDetailsEntityDecimalMapper.toDto(dqStandardDetailsEntityDecimal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardDetailsEntityDecimalMockMvc.perform(put("/api/dq-standard-details-entity-decimals")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityDecimalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityDecimal in the database
        List<DqStandardDetailsEntityDecimal> dqStandardDetailsEntityDecimalList = dqStandardDetailsEntityDecimalRepository.findAll();
        assertThat(dqStandardDetailsEntityDecimalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardDetailsEntityDecimal() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityDecimalRepository.saveAndFlush(dqStandardDetailsEntityDecimal);

        int databaseSizeBeforeDelete = dqStandardDetailsEntityDecimalRepository.findAll().size();

        // Delete the dqStandardDetailsEntityDecimal
        restDqStandardDetailsEntityDecimalMockMvc.perform(delete("/api/dq-standard-details-entity-decimals/{id}", dqStandardDetailsEntityDecimal.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardDetailsEntityDecimal> dqStandardDetailsEntityDecimalList = dqStandardDetailsEntityDecimalRepository.findAll();
        assertThat(dqStandardDetailsEntityDecimalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
