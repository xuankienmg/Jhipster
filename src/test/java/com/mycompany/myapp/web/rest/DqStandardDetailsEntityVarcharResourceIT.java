package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardDetailsEntityVarchar;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardDetailsEntityVarcharRepository;
import com.mycompany.myapp.service.DqStandardDetailsEntityVarcharService;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityVarcharMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityVarcharCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityVarcharQueryService;

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
 * Integration tests for the {@link DqStandardDetailsEntityVarcharResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardDetailsEntityVarcharResourceIT {

    private static final String DEFAULT_STD_ATTTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTTRIBUTE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STD_ATTRIBUTE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTRIBUTE_VALUE = "BBBBBBBBBB";

    @Autowired
    private DqStandardDetailsEntityVarcharRepository dqStandardDetailsEntityVarcharRepository;

    @Autowired
    private DqStandardDetailsEntityVarcharMapper dqStandardDetailsEntityVarcharMapper;

    @Autowired
    private DqStandardDetailsEntityVarcharService dqStandardDetailsEntityVarcharService;

    @Autowired
    private DqStandardDetailsEntityVarcharQueryService dqStandardDetailsEntityVarcharQueryService;

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

    private MockMvc restDqStandardDetailsEntityVarcharMockMvc;

    private DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardDetailsEntityVarcharResource dqStandardDetailsEntityVarcharResource = new DqStandardDetailsEntityVarcharResource(dqStandardDetailsEntityVarcharService, dqStandardDetailsEntityVarcharQueryService);
        this.restDqStandardDetailsEntityVarcharMockMvc = MockMvcBuilders.standaloneSetup(dqStandardDetailsEntityVarcharResource)
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
    public static DqStandardDetailsEntityVarchar createEntity(EntityManager em) {
        DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar = new DqStandardDetailsEntityVarchar()
            .stdAtttributeName(DEFAULT_STD_ATTTRIBUTE_NAME)
            .stdAttributeValue(DEFAULT_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityVarchar;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardDetailsEntityVarchar createUpdatedEntity(EntityManager em) {
        DqStandardDetailsEntityVarchar dqStandardDetailsEntityVarchar = new DqStandardDetailsEntityVarchar()
            .stdAtttributeName(UPDATED_STD_ATTTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityVarchar;
    }

    @BeforeEach
    public void initTest() {
        dqStandardDetailsEntityVarchar = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityVarchar() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityVarcharRepository.findAll().size();

        // Create the DqStandardDetailsEntityVarchar
        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO = dqStandardDetailsEntityVarcharMapper.toDto(dqStandardDetailsEntityVarchar);
        restDqStandardDetailsEntityVarcharMockMvc.perform(post("/api/dq-standard-details-entity-varchars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityVarcharDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardDetailsEntityVarchar in the database
        List<DqStandardDetailsEntityVarchar> dqStandardDetailsEntityVarcharList = dqStandardDetailsEntityVarcharRepository.findAll();
        assertThat(dqStandardDetailsEntityVarcharList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardDetailsEntityVarchar testDqStandardDetailsEntityVarchar = dqStandardDetailsEntityVarcharList.get(dqStandardDetailsEntityVarcharList.size() - 1);
        assertThat(testDqStandardDetailsEntityVarchar.getStdAtttributeName()).isEqualTo(DEFAULT_STD_ATTTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityVarchar.getStdAttributeValue()).isEqualTo(DEFAULT_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityVarcharWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityVarcharRepository.findAll().size();

        // Create the DqStandardDetailsEntityVarchar with an existing ID
        dqStandardDetailsEntityVarchar.setId(1L);
        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO = dqStandardDetailsEntityVarcharMapper.toDto(dqStandardDetailsEntityVarchar);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardDetailsEntityVarcharMockMvc.perform(post("/api/dq-standard-details-entity-varchars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityVarcharDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityVarchar in the database
        List<DqStandardDetailsEntityVarchar> dqStandardDetailsEntityVarcharList = dqStandardDetailsEntityVarcharRepository.findAll();
        assertThat(dqStandardDetailsEntityVarcharList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarchars() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityVarchar.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAtttributeName").value(hasItem(DEFAULT_STD_ATTTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE)));
    }
    
    @Test
    @Transactional
    public void getDqStandardDetailsEntityVarchar() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get the dqStandardDetailsEntityVarchar
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars/{id}", dqStandardDetailsEntityVarchar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardDetailsEntityVarchar.getId().intValue()))
            .andExpect(jsonPath("$.stdAtttributeName").value(DEFAULT_STD_ATTTRIBUTE_NAME))
            .andExpect(jsonPath("$.stdAttributeValue").value(DEFAULT_STD_ATTRIBUTE_VALUE));
    }


    @Test
    @Transactional
    public void getDqStandardDetailsEntityVarcharsByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        Long id = dqStandardDetailsEntityVarchar.getId();

        defaultDqStandardDetailsEntityVarcharShouldBeFound("id.equals=" + id);
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardDetailsEntityVarcharShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardDetailsEntityVarcharShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAtttributeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName equals to DEFAULT_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAtttributeName.equals=" + DEFAULT_STD_ATTTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName equals to UPDATED_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAtttributeName.equals=" + UPDATED_STD_ATTTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAtttributeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName not equals to DEFAULT_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAtttributeName.notEquals=" + DEFAULT_STD_ATTTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName not equals to UPDATED_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAtttributeName.notEquals=" + UPDATED_STD_ATTTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAtttributeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName in DEFAULT_STD_ATTTRIBUTE_NAME or UPDATED_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAtttributeName.in=" + DEFAULT_STD_ATTTRIBUTE_NAME + "," + UPDATED_STD_ATTTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName equals to UPDATED_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAtttributeName.in=" + UPDATED_STD_ATTTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAtttributeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName is not null
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAtttributeName.specified=true");

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName is null
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAtttributeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAtttributeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName contains DEFAULT_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAtttributeName.contains=" + DEFAULT_STD_ATTTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName contains UPDATED_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAtttributeName.contains=" + UPDATED_STD_ATTTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAtttributeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName does not contain DEFAULT_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAtttributeName.doesNotContain=" + DEFAULT_STD_ATTTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityVarcharList where stdAtttributeName does not contain UPDATED_STD_ATTTRIBUTE_NAME
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAtttributeName.doesNotContain=" + UPDATED_STD_ATTTRIBUTE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAttributeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAttributeValue.equals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAttributeValue.equals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAttributeValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue not equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAttributeValue.notEquals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue not equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAttributeValue.notEquals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAttributeValueIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue in DEFAULT_STD_ATTRIBUTE_VALUE or UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAttributeValue.in=" + DEFAULT_STD_ATTRIBUTE_VALUE + "," + UPDATED_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAttributeValue.in=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAttributeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue is not null
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAttributeValue.specified=true");

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue is null
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAttributeValue.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAttributeValueContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue contains DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAttributeValue.contains=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue contains UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAttributeValue.contains=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdAttributeValueNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue does not contain DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdAttributeValue.doesNotContain=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityVarcharList where stdAttributeValue does not contain UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdAttributeValue.doesNotContain=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityVarcharsByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqStandardDetailsEntityVarchar.setStd(std);
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);
        Long stdId = std.getId();

        // Get all the dqStandardDetailsEntityVarcharList where std equals to stdId
        defaultDqStandardDetailsEntityVarcharShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqStandardDetailsEntityVarcharList where std equals to stdId + 1
        defaultDqStandardDetailsEntityVarcharShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardDetailsEntityVarcharShouldBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityVarchar.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAtttributeName").value(hasItem(DEFAULT_STD_ATTTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE)));

        // Check, that the count call also returns 1
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardDetailsEntityVarcharShouldNotBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardDetailsEntityVarchar() throws Exception {
        // Get the dqStandardDetailsEntityVarchar
        restDqStandardDetailsEntityVarcharMockMvc.perform(get("/api/dq-standard-details-entity-varchars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardDetailsEntityVarchar() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        int databaseSizeBeforeUpdate = dqStandardDetailsEntityVarcharRepository.findAll().size();

        // Update the dqStandardDetailsEntityVarchar
        DqStandardDetailsEntityVarchar updatedDqStandardDetailsEntityVarchar = dqStandardDetailsEntityVarcharRepository.findById(dqStandardDetailsEntityVarchar.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardDetailsEntityVarchar are not directly saved in db
        em.detach(updatedDqStandardDetailsEntityVarchar);
        updatedDqStandardDetailsEntityVarchar
            .stdAtttributeName(UPDATED_STD_ATTTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO = dqStandardDetailsEntityVarcharMapper.toDto(updatedDqStandardDetailsEntityVarchar);

        restDqStandardDetailsEntityVarcharMockMvc.perform(put("/api/dq-standard-details-entity-varchars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityVarcharDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardDetailsEntityVarchar in the database
        List<DqStandardDetailsEntityVarchar> dqStandardDetailsEntityVarcharList = dqStandardDetailsEntityVarcharRepository.findAll();
        assertThat(dqStandardDetailsEntityVarcharList).hasSize(databaseSizeBeforeUpdate);
        DqStandardDetailsEntityVarchar testDqStandardDetailsEntityVarchar = dqStandardDetailsEntityVarcharList.get(dqStandardDetailsEntityVarcharList.size() - 1);
        assertThat(testDqStandardDetailsEntityVarchar.getStdAtttributeName()).isEqualTo(UPDATED_STD_ATTTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityVarchar.getStdAttributeValue()).isEqualTo(UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardDetailsEntityVarchar() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardDetailsEntityVarcharRepository.findAll().size();

        // Create the DqStandardDetailsEntityVarchar
        DqStandardDetailsEntityVarcharDTO dqStandardDetailsEntityVarcharDTO = dqStandardDetailsEntityVarcharMapper.toDto(dqStandardDetailsEntityVarchar);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardDetailsEntityVarcharMockMvc.perform(put("/api/dq-standard-details-entity-varchars")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityVarcharDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityVarchar in the database
        List<DqStandardDetailsEntityVarchar> dqStandardDetailsEntityVarcharList = dqStandardDetailsEntityVarcharRepository.findAll();
        assertThat(dqStandardDetailsEntityVarcharList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardDetailsEntityVarchar() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityVarcharRepository.saveAndFlush(dqStandardDetailsEntityVarchar);

        int databaseSizeBeforeDelete = dqStandardDetailsEntityVarcharRepository.findAll().size();

        // Delete the dqStandardDetailsEntityVarchar
        restDqStandardDetailsEntityVarcharMockMvc.perform(delete("/api/dq-standard-details-entity-varchars/{id}", dqStandardDetailsEntityVarchar.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardDetailsEntityVarchar> dqStandardDetailsEntityVarcharList = dqStandardDetailsEntityVarcharRepository.findAll();
        assertThat(dqStandardDetailsEntityVarcharList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
