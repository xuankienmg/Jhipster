package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.DqRuleCategories;
import com.mycompany.myapp.repository.DqRuleCategoriesRepository;
import com.mycompany.myapp.service.DqRuleCategoriesService;
import com.mycompany.myapp.service.dto.DqRuleCategoriesDTO;
import com.mycompany.myapp.service.mapper.DqRuleCategoriesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DqRuleCategoriesCriteria;
import com.mycompany.myapp.service.DqRuleCategoriesQueryService;

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
 * Integration tests for the {@link DqRuleCategoriesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class DqRuleCategoriesResourceIT {

    private static final String DEFAULT_CAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAT_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DqRuleCategoriesRepository dqRuleCategoriesRepository;

    @Autowired
    private DqRuleCategoriesMapper dqRuleCategoriesMapper;

    @Autowired
    private DqRuleCategoriesService dqRuleCategoriesService;

    @Autowired
    private DqRuleCategoriesQueryService dqRuleCategoriesQueryService;

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

    private MockMvc restDqRuleCategoriesMockMvc;

    private DqRuleCategories dqRuleCategories;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DqRuleCategoriesResource dqRuleCategoriesResource = new DqRuleCategoriesResource(dqRuleCategoriesService, dqRuleCategoriesQueryService);
        this.restDqRuleCategoriesMockMvc = MockMvcBuilders.standaloneSetup(dqRuleCategoriesResource)
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
    public static DqRuleCategories createEntity(EntityManager em) {
        DqRuleCategories dqRuleCategories = new DqRuleCategories()
            .catName(DEFAULT_CAT_NAME)
            .catDescription(DEFAULT_CAT_DESCRIPTION);
        return dqRuleCategories;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DqRuleCategories createUpdatedEntity(EntityManager em) {
        DqRuleCategories dqRuleCategories = new DqRuleCategories()
            .catName(UPDATED_CAT_NAME)
            .catDescription(UPDATED_CAT_DESCRIPTION);
        return dqRuleCategories;
    }

    @BeforeEach
    public void initTest() {
        dqRuleCategories = createEntity(em);
    }

    @Test
    @Transactional
    public void createDqRuleCategories() throws Exception {
        int databaseSizeBeforeCreate = dqRuleCategoriesRepository.findAll().size();

        // Create the DqRuleCategories
        DqRuleCategoriesDTO dqRuleCategoriesDTO = dqRuleCategoriesMapper.toDto(dqRuleCategories);
        restDqRuleCategoriesMockMvc.perform(post("/api/dq-rule-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleCategoriesDTO)))
            .andExpect(status().isCreated());

        // Validate the DqRuleCategories in the database
        List<DqRuleCategories> dqRuleCategoriesList = dqRuleCategoriesRepository.findAll();
        assertThat(dqRuleCategoriesList).hasSize(databaseSizeBeforeCreate + 1);
        DqRuleCategories testDqRuleCategories = dqRuleCategoriesList.get(dqRuleCategoriesList.size() - 1);
        assertThat(testDqRuleCategories.getCatName()).isEqualTo(DEFAULT_CAT_NAME);
        assertThat(testDqRuleCategories.getCatDescription()).isEqualTo(DEFAULT_CAT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDqRuleCategoriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dqRuleCategoriesRepository.findAll().size();

        // Create the DqRuleCategories with an existing ID
        dqRuleCategories.setId(1L);
        DqRuleCategoriesDTO dqRuleCategoriesDTO = dqRuleCategoriesMapper.toDto(dqRuleCategories);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDqRuleCategoriesMockMvc.perform(post("/api/dq-rule-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleCategoriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleCategories in the database
        List<DqRuleCategories> dqRuleCategoriesList = dqRuleCategoriesRepository.findAll();
        assertThat(dqRuleCategoriesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDqRuleCategories() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleCategories.getId().intValue())))
            .andExpect(jsonPath("$.[*].catName").value(hasItem(DEFAULT_CAT_NAME)))
            .andExpect(jsonPath("$.[*].catDescription").value(hasItem(DEFAULT_CAT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getDqRuleCategories() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get the dqRuleCategories
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories/{id}", dqRuleCategories.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dqRuleCategories.getId().intValue()))
            .andExpect(jsonPath("$.catName").value(DEFAULT_CAT_NAME))
            .andExpect(jsonPath("$.catDescription").value(DEFAULT_CAT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getDqRuleCategoriesByIdFiltering() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        Long id = dqRuleCategories.getId();

        defaultDqRuleCategoriesShouldBeFound("id.equals=" + id);
        defaultDqRuleCategoriesShouldNotBeFound("id.notEquals=" + id);

        defaultDqRuleCategoriesShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDqRuleCategoriesShouldNotBeFound("id.greaterThan=" + id);

        defaultDqRuleCategoriesShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDqRuleCategoriesShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatNameIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catName equals to DEFAULT_CAT_NAME
        defaultDqRuleCategoriesShouldBeFound("catName.equals=" + DEFAULT_CAT_NAME);

        // Get all the dqRuleCategoriesList where catName equals to UPDATED_CAT_NAME
        defaultDqRuleCategoriesShouldNotBeFound("catName.equals=" + UPDATED_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catName not equals to DEFAULT_CAT_NAME
        defaultDqRuleCategoriesShouldNotBeFound("catName.notEquals=" + DEFAULT_CAT_NAME);

        // Get all the dqRuleCategoriesList where catName not equals to UPDATED_CAT_NAME
        defaultDqRuleCategoriesShouldBeFound("catName.notEquals=" + UPDATED_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatNameIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catName in DEFAULT_CAT_NAME or UPDATED_CAT_NAME
        defaultDqRuleCategoriesShouldBeFound("catName.in=" + DEFAULT_CAT_NAME + "," + UPDATED_CAT_NAME);

        // Get all the dqRuleCategoriesList where catName equals to UPDATED_CAT_NAME
        defaultDqRuleCategoriesShouldNotBeFound("catName.in=" + UPDATED_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catName is not null
        defaultDqRuleCategoriesShouldBeFound("catName.specified=true");

        // Get all the dqRuleCategoriesList where catName is null
        defaultDqRuleCategoriesShouldNotBeFound("catName.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatNameContainsSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catName contains DEFAULT_CAT_NAME
        defaultDqRuleCategoriesShouldBeFound("catName.contains=" + DEFAULT_CAT_NAME);

        // Get all the dqRuleCategoriesList where catName contains UPDATED_CAT_NAME
        defaultDqRuleCategoriesShouldNotBeFound("catName.contains=" + UPDATED_CAT_NAME);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatNameNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catName does not contain DEFAULT_CAT_NAME
        defaultDqRuleCategoriesShouldNotBeFound("catName.doesNotContain=" + DEFAULT_CAT_NAME);

        // Get all the dqRuleCategoriesList where catName does not contain UPDATED_CAT_NAME
        defaultDqRuleCategoriesShouldBeFound("catName.doesNotContain=" + UPDATED_CAT_NAME);
    }


    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catDescription equals to DEFAULT_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldBeFound("catDescription.equals=" + DEFAULT_CAT_DESCRIPTION);

        // Get all the dqRuleCategoriesList where catDescription equals to UPDATED_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldNotBeFound("catDescription.equals=" + UPDATED_CAT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catDescription not equals to DEFAULT_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldNotBeFound("catDescription.notEquals=" + DEFAULT_CAT_DESCRIPTION);

        // Get all the dqRuleCategoriesList where catDescription not equals to UPDATED_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldBeFound("catDescription.notEquals=" + UPDATED_CAT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catDescription in DEFAULT_CAT_DESCRIPTION or UPDATED_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldBeFound("catDescription.in=" + DEFAULT_CAT_DESCRIPTION + "," + UPDATED_CAT_DESCRIPTION);

        // Get all the dqRuleCategoriesList where catDescription equals to UPDATED_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldNotBeFound("catDescription.in=" + UPDATED_CAT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catDescription is not null
        defaultDqRuleCategoriesShouldBeFound("catDescription.specified=true");

        // Get all the dqRuleCategoriesList where catDescription is null
        defaultDqRuleCategoriesShouldNotBeFound("catDescription.specified=false");
    }
                @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatDescriptionContainsSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catDescription contains DEFAULT_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldBeFound("catDescription.contains=" + DEFAULT_CAT_DESCRIPTION);

        // Get all the dqRuleCategoriesList where catDescription contains UPDATED_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldNotBeFound("catDescription.contains=" + UPDATED_CAT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDqRuleCategoriesByCatDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        // Get all the dqRuleCategoriesList where catDescription does not contain DEFAULT_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldNotBeFound("catDescription.doesNotContain=" + DEFAULT_CAT_DESCRIPTION);

        // Get all the dqRuleCategoriesList where catDescription does not contain UPDATED_CAT_DESCRIPTION
        defaultDqRuleCategoriesShouldBeFound("catDescription.doesNotContain=" + UPDATED_CAT_DESCRIPTION);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDqRuleCategoriesShouldBeFound(String filter) throws Exception {
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dqRuleCategories.getId().intValue())))
            .andExpect(jsonPath("$.[*].catName").value(hasItem(DEFAULT_CAT_NAME)))
            .andExpect(jsonPath("$.[*].catDescription").value(hasItem(DEFAULT_CAT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDqRuleCategoriesShouldNotBeFound(String filter) throws Exception {
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDqRuleCategories() throws Exception {
        // Get the dqRuleCategories
        restDqRuleCategoriesMockMvc.perform(get("/api/dq-rule-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDqRuleCategories() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        int databaseSizeBeforeUpdate = dqRuleCategoriesRepository.findAll().size();

        // Update the dqRuleCategories
        DqRuleCategories updatedDqRuleCategories = dqRuleCategoriesRepository.findById(dqRuleCategories.getId()).get();
        // Disconnect from session so that the updates on updatedDqRuleCategories are not directly saved in db
        em.detach(updatedDqRuleCategories);
        updatedDqRuleCategories
            .catName(UPDATED_CAT_NAME)
            .catDescription(UPDATED_CAT_DESCRIPTION);
        DqRuleCategoriesDTO dqRuleCategoriesDTO = dqRuleCategoriesMapper.toDto(updatedDqRuleCategories);

        restDqRuleCategoriesMockMvc.perform(put("/api/dq-rule-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleCategoriesDTO)))
            .andExpect(status().isOk());

        // Validate the DqRuleCategories in the database
        List<DqRuleCategories> dqRuleCategoriesList = dqRuleCategoriesRepository.findAll();
        assertThat(dqRuleCategoriesList).hasSize(databaseSizeBeforeUpdate);
        DqRuleCategories testDqRuleCategories = dqRuleCategoriesList.get(dqRuleCategoriesList.size() - 1);
        assertThat(testDqRuleCategories.getCatName()).isEqualTo(UPDATED_CAT_NAME);
        assertThat(testDqRuleCategories.getCatDescription()).isEqualTo(UPDATED_CAT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingDqRuleCategories() throws Exception {
        int databaseSizeBeforeUpdate = dqRuleCategoriesRepository.findAll().size();

        // Create the DqRuleCategories
        DqRuleCategoriesDTO dqRuleCategoriesDTO = dqRuleCategoriesMapper.toDto(dqRuleCategories);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDqRuleCategoriesMockMvc.perform(put("/api/dq-rule-categories")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dqRuleCategoriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DqRuleCategories in the database
        List<DqRuleCategories> dqRuleCategoriesList = dqRuleCategoriesRepository.findAll();
        assertThat(dqRuleCategoriesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDqRuleCategories() throws Exception {
        // Initialize the database
        dqRuleCategoriesRepository.saveAndFlush(dqRuleCategories);

        int databaseSizeBeforeDelete = dqRuleCategoriesRepository.findAll().size();

        // Delete the dqRuleCategories
        restDqRuleCategoriesMockMvc.perform(delete("/api/dq-rule-categories/{id}", dqRuleCategories.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DqRuleCategories> dqRuleCategoriesList = dqRuleCategoriesRepository.findAll();
        assertThat(dqRuleCategoriesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
