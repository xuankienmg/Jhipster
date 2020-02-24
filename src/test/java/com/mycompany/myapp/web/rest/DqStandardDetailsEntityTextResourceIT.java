package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqStandardDetailsEntityText;
import com.mycompany.myapp.domain.DqStandards;
import com.mycompany.myapp.repository.DqStandardDetailsEntityTextRepository;
import com.mycompany.myapp.service.DqStandardDetailsEntityTextService;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextDTO;
import com.mycompany.myapp.service.mapper.DqStandardDetailsEntityTextMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqStandardDetailsEntityTextCriteria;
import com.mycompany.myapp.service.DqStandardDetailsEntityTextQueryService;

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
 * Integration tests for the {@link DqStandardDetailsEntityTextResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqStandardDetailsEntityTextResourceIT {

    private static final String DEFAULT_STD_ATTRIBUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STD_ATTRIBUTE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_STD_ATTRIBUTE_VALUE = 1L;
    private static final Long UPDATED_STD_ATTRIBUTE_VALUE = 2L;
    private static final Long SMALLER_STD_ATTRIBUTE_VALUE = 1L - 1L;

    @Autowired
    private DqStandardDetailsEntityTextRepository dqStandardDetailsEntityTextRepository;

    @Autowired
    private DqStandardDetailsEntityTextMapper dqStandardDetailsEntityTextMapper;

    @Autowired
    private DqStandardDetailsEntityTextService dqStandardDetailsEntityTextService;

    @Autowired
    private DqStandardDetailsEntityTextQueryService dqStandardDetailsEntityTextQueryService;

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

    private MockMvc restDqStandardDetailsEntityTextMockMvc;

    private DqStandardDetailsEntityText dqStandardDetailsEntityText;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqStandardDetailsEntityTextResource dqStandardDetailsEntityTextResource = new DqStandardDetailsEntityTextResource(dqStandardDetailsEntityTextService, dqStandardDetailsEntityTextQueryService);
        this.restDqStandardDetailsEntityTextMockMvc = MockMvcBuilders.standaloneSetup(dqStandardDetailsEntityTextResource)
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
    public static DqStandardDetailsEntityText createEntity(EntityManager em) {
        DqStandardDetailsEntityText dqStandardDetailsEntityText = new DqStandardDetailsEntityText()
            .stdAttributeName(DEFAULT_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(DEFAULT_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityText;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqStandardDetailsEntityText createUpdatedEntity(EntityManager em) {
        DqStandardDetailsEntityText dqStandardDetailsEntityText = new DqStandardDetailsEntityText()
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        return dqStandardDetailsEntityText;
    }

    @BeforeEach
    public void initTest() {
        dqStandardDetailsEntityText = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityText() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityTextRepository.findAll().size();

        // Create the DqStandardDetailsEntityText
        DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO = dqStandardDetailsEntityTextMapper.toDto(dqStandardDetailsEntityText);
        restDqStandardDetailsEntityTextMockMvc.perform(post("/api/dq-standard-details-entity-texts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTextDTO)))
            .andExpect(status().isCreated());

        // Validate the DqStandardDetailsEntityText in the database
        List<DqStandardDetailsEntityText> dqStandardDetailsEntityTextList = dqStandardDetailsEntityTextRepository.findAll();
        assertThat(dqStandardDetailsEntityTextList).hasSize(databaseSizeBeforeCreate + 1);
        DqStandardDetailsEntityText testDqStandardDetailsEntityText = dqStandardDetailsEntityTextList.get(dqStandardDetailsEntityTextList.size() - 1);
        assertThat(testDqStandardDetailsEntityText.getStdAttributeName()).isEqualTo(DEFAULT_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityText.getStdAttributeValue()).isEqualTo(DEFAULT_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void createDqStandardDetailsEntityTextWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqStandardDetailsEntityTextRepository.findAll().size();

        // Create the DqStandardDetailsEntityText with an existing ID
        dqStandardDetailsEntityText.setId(1L);
        DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO = dqStandardDetailsEntityTextMapper.toDto(dqStandardDetailsEntityText);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqStandardDetailsEntityTextMockMvc.perform(post("/api/dq-standard-details-entity-texts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTextDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityText in the database
        List<DqStandardDetailsEntityText> dqStandardDetailsEntityTextList = dqStandardDetailsEntityTextRepository.findAll();
        assertThat(dqStandardDetailsEntityTextList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTexts() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityText.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.intValue())));
    }
    
    @Test
    @Transactional
    public void getDqStandardDetailsEntityText() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get the dqStandardDetailsEntityText
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts/{id}", dqStandardDetailsEntityText.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqStandardDetailsEntityText.getId().intValue()))
            .andExpect(jsonPath("$.stdAttributeName").value(DEFAULT_STD_ATTRIBUTE_NAME))
            .andExpect(jsonPath("$.stdAttributeValue").value(DEFAULT_STD_ATTRIBUTE_VALUE.intValue()));
    }


    @Test
    @Transactional
    public void getDqStandardDetailsEntityTextsByIdFiltering() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        Long id = dqStandardDetailsEntityText.getId();

        defaultDqStandardDetailsEntityTextShouldBeFound("id.equals=" + id);
        defaultDqStandardDetailsEntityTextShouldNotBeFound("id.notEquals=" + id);

        defaultDqStandardDetailsEntityTextShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqStandardDetailsEntityTextShouldNotBeFound("id.greaterThan=" + id);

        defaultDqStandardDetailsEntityTextShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqStandardDetailsEntityTextShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeName.equals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeName.equals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName not equals to DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeName.notEquals=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName not equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeName.notEquals=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName in DEFAULT_STD_ATTRIBUTE_NAME or UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeName.in=" + DEFAULT_STD_ATTRIBUTE_NAME + "," + UPDATED_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName equals to UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeName.in=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName is not null
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeName.specified=true");

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName is null
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeNameContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName contains DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeName.contains=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName contains UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeName.contains=" + UPDATED_STD_ATTRIBUTE_NAME);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName does not contain DEFAULT_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeName.doesNotContain=" + DEFAULT_STD_ATTRIBUTE_NAME);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeName does not contain UPDATED_STD_ATTRIBUTE_NAME
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeName.doesNotContain=" + UPDATED_STD_ATTRIBUTE_NAME);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.equals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.equals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue not equals to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.notEquals=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue not equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.notEquals=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsInShouldWork() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue in DEFAULT_STD_ATTRIBUTE_VALUE or UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.in=" + DEFAULT_STD_ATTRIBUTE_VALUE + "," + UPDATED_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue equals to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.in=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is not null
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.specified=true");

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is null
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.specified=false");
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is greater than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.greaterThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is greater than or equal to UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.greaterThanOrEqual=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is less than or equal to DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.lessThanOrEqual=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is less than or equal to SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.lessThanOrEqual=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsLessThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is less than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.lessThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is less than UPDATED_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.lessThan=" + UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdAttributeValueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is greater than DEFAULT_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdAttributeValue.greaterThan=" + DEFAULT_STD_ATTRIBUTE_VALUE);

        // Get all the dqStandardDetailsEntityTextList where stdAttributeValue is greater than SMALLER_STD_ATTRIBUTE_VALUE
        defaultDqStandardDetailsEntityTextShouldBeFound("stdAttributeValue.greaterThan=" + SMALLER_STD_ATTRIBUTE_VALUE);
    }


    @Test
    @Transactional
    public void getAllDqStandardDetailsEntityTextsByStdIsEqualToSomething() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);
        DqStandards std = DqStandardsResourceIT.createEntity(em);
        em.persist(std);
        em.flush();
        dqStandardDetailsEntityText.setStd(std);
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);
        Long stdId = std.getId();

        // Get all the dqStandardDetailsEntityTextList where std equals to stdId
        defaultDqStandardDetailsEntityTextShouldBeFound("stdId.equals=" + stdId);

        // Get all the dqStandardDetailsEntityTextList where std equals to stdId + 1
        defaultDqStandardDetailsEntityTextShouldNotBeFound("stdId.equals=" + (stdId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqStandardDetailsEntityTextShouldBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqStandardDetailsEntityText.getId().intValue())))
            .andExpect(jsonPath("$.[*].stdAttributeName").value(hasItem(DEFAULT_STD_ATTRIBUTE_NAME)))
            .andExpect(jsonPath("$.[*].stdAttributeValue").value(hasItem(DEFAULT_STD_ATTRIBUTE_VALUE.intValue())));

        // Check, that the count call also returns 1
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqStandardDetailsEntityTextShouldNotBeFound(String filter) throws Exception {
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqStandardDetailsEntityText() throws Exception {
        // Get the dqStandardDetailsEntityText
        restDqStandardDetailsEntityTextMockMvc.perform(get("/api/dq-standard-details-entity-texts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqStandardDetailsEntityText() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        int databaseSizeBeforeUpdate = dqStandardDetailsEntityTextRepository.findAll().size();

        // Update the dqStandardDetailsEntityText
        DqStandardDetailsEntityText updatedDqStandardDetailsEntityText = dqStandardDetailsEntityTextRepository.findById(dqStandardDetailsEntityText.getId()).get();
        // Disconnect from session so that the updates on updatedDqStandardDetailsEntityText are not directly saved in db
        em.detach(updatedDqStandardDetailsEntityText);
        updatedDqStandardDetailsEntityText
            .stdAttributeName(UPDATED_STD_ATTRIBUTE_NAME)
            .stdAttributeValue(UPDATED_STD_ATTRIBUTE_VALUE);
        DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO = dqStandardDetailsEntityTextMapper.toDto(updatedDqStandardDetailsEntityText);

        restDqStandardDetailsEntityTextMockMvc.perform(put("/api/dq-standard-details-entity-texts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTextDTO)))
            .andExpect(status().isOk());

        // Validate the DqStandardDetailsEntityText in the database
        List<DqStandardDetailsEntityText> dqStandardDetailsEntityTextList = dqStandardDetailsEntityTextRepository.findAll();
        assertThat(dqStandardDetailsEntityTextList).hasSize(databaseSizeBeforeUpdate);
        DqStandardDetailsEntityText testDqStandardDetailsEntityText = dqStandardDetailsEntityTextList.get(dqStandardDetailsEntityTextList.size() - 1);
        assertThat(testDqStandardDetailsEntityText.getStdAttributeName()).isEqualTo(UPDATED_STD_ATTRIBUTE_NAME);
        assertThat(testDqStandardDetailsEntityText.getStdAttributeValue()).isEqualTo(UPDATED_STD_ATTRIBUTE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDqStandardDetailsEntityText() throws Exception {
        int databaseSizeBeforeUpdate = dqStandardDetailsEntityTextRepository.findAll().size();

        // Create the DqStandardDetailsEntityText
        DqStandardDetailsEntityTextDTO dqStandardDetailsEntityTextDTO = dqStandardDetailsEntityTextMapper.toDto(dqStandardDetailsEntityText);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqStandardDetailsEntityTextMockMvc.perform(put("/api/dq-standard-details-entity-texts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqStandardDetailsEntityTextDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqStandardDetailsEntityText in the database
        List<DqStandardDetailsEntityText> dqStandardDetailsEntityTextList = dqStandardDetailsEntityTextRepository.findAll();
        assertThat(dqStandardDetailsEntityTextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqStandardDetailsEntityText() throws Exception {
        // Initialize the database
        dqStandardDetailsEntityTextRepository.saveAndFlush(dqStandardDetailsEntityText);

        int databaseSizeBeforeDelete = dqStandardDetailsEntityTextRepository.findAll().size();

        // Delete the dqStandardDetailsEntityText
        restDqStandardDetailsEntityTextMockMvc.perform(delete("/api/dq-standard-details-entity-texts/{id}", dqStandardDetailsEntityText.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqStandardDetailsEntityText> dqStandardDetailsEntityTextList = dqStandardDetailsEntityTextRepository.findAll();
        assertThat(dqStandardDetailsEntityTextList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
