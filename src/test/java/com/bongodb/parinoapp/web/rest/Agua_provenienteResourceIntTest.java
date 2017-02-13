package com.bongodb.parinoapp.web.rest;

import com.bongodb.parinoapp.ParinoGroupApp;

import com.bongodb.parinoapp.domain.Agua_proveniente;
import com.bongodb.parinoapp.repository.Agua_provenienteRepository;
import com.bongodb.parinoapp.service.Agua_provenienteService;
import com.bongodb.parinoapp.service.dto.Agua_provenienteDTO;
import com.bongodb.parinoapp.service.mapper.Agua_provenienteMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Agua_provenienteResource REST controller.
 *
 * @see Agua_provenienteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParinoGroupApp.class)
public class Agua_provenienteResourceIntTest {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private Agua_provenienteRepository agua_provenienteRepository;

    @Autowired
    private Agua_provenienteMapper agua_provenienteMapper;

    @Autowired
    private Agua_provenienteService agua_provenienteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restAgua_provenienteMockMvc;

    private Agua_proveniente agua_proveniente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Agua_provenienteResource agua_provenienteResource = new Agua_provenienteResource(agua_provenienteService);
        this.restAgua_provenienteMockMvc = MockMvcBuilders.standaloneSetup(agua_provenienteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agua_proveniente createEntity(EntityManager em) {
        Agua_proveniente agua_proveniente = new Agua_proveniente()
                .descripcion(DEFAULT_DESCRIPCION);
        return agua_proveniente;
    }

    @Before
    public void initTest() {
        agua_proveniente = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgua_proveniente() throws Exception {
        int databaseSizeBeforeCreate = agua_provenienteRepository.findAll().size();

        // Create the Agua_proveniente
        Agua_provenienteDTO agua_provenienteDTO = agua_provenienteMapper.agua_provenienteToAgua_provenienteDTO(agua_proveniente);

        restAgua_provenienteMockMvc.perform(post("/api/agua-provenientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agua_provenienteDTO)))
            .andExpect(status().isCreated());

        // Validate the Agua_proveniente in the database
        List<Agua_proveniente> agua_provenienteList = agua_provenienteRepository.findAll();
        assertThat(agua_provenienteList).hasSize(databaseSizeBeforeCreate + 1);
        Agua_proveniente testAgua_proveniente = agua_provenienteList.get(agua_provenienteList.size() - 1);
        assertThat(testAgua_proveniente.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createAgua_provenienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agua_provenienteRepository.findAll().size();

        // Create the Agua_proveniente with an existing ID
        Agua_proveniente existingAgua_proveniente = new Agua_proveniente();
        existingAgua_proveniente.setId(1L);
        Agua_provenienteDTO existingAgua_provenienteDTO = agua_provenienteMapper.agua_provenienteToAgua_provenienteDTO(existingAgua_proveniente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgua_provenienteMockMvc.perform(post("/api/agua-provenientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAgua_provenienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Agua_proveniente> agua_provenienteList = agua_provenienteRepository.findAll();
        assertThat(agua_provenienteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAgua_provenientes() throws Exception {
        // Initialize the database
        agua_provenienteRepository.saveAndFlush(agua_proveniente);

        // Get all the agua_provenienteList
        restAgua_provenienteMockMvc.perform(get("/api/agua-provenientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agua_proveniente.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())));
    }

    @Test
    @Transactional
    public void getAgua_proveniente() throws Exception {
        // Initialize the database
        agua_provenienteRepository.saveAndFlush(agua_proveniente);

        // Get the agua_proveniente
        restAgua_provenienteMockMvc.perform(get("/api/agua-provenientes/{id}", agua_proveniente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agua_proveniente.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAgua_proveniente() throws Exception {
        // Get the agua_proveniente
        restAgua_provenienteMockMvc.perform(get("/api/agua-provenientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgua_proveniente() throws Exception {
        // Initialize the database
        agua_provenienteRepository.saveAndFlush(agua_proveniente);
        int databaseSizeBeforeUpdate = agua_provenienteRepository.findAll().size();

        // Update the agua_proveniente
        Agua_proveniente updatedAgua_proveniente = agua_provenienteRepository.findOne(agua_proveniente.getId());
        updatedAgua_proveniente
                .descripcion(UPDATED_DESCRIPCION);
        Agua_provenienteDTO agua_provenienteDTO = agua_provenienteMapper.agua_provenienteToAgua_provenienteDTO(updatedAgua_proveniente);

        restAgua_provenienteMockMvc.perform(put("/api/agua-provenientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agua_provenienteDTO)))
            .andExpect(status().isOk());

        // Validate the Agua_proveniente in the database
        List<Agua_proveniente> agua_provenienteList = agua_provenienteRepository.findAll();
        assertThat(agua_provenienteList).hasSize(databaseSizeBeforeUpdate);
        Agua_proveniente testAgua_proveniente = agua_provenienteList.get(agua_provenienteList.size() - 1);
        assertThat(testAgua_proveniente.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingAgua_proveniente() throws Exception {
        int databaseSizeBeforeUpdate = agua_provenienteRepository.findAll().size();

        // Create the Agua_proveniente
        Agua_provenienteDTO agua_provenienteDTO = agua_provenienteMapper.agua_provenienteToAgua_provenienteDTO(agua_proveniente);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAgua_provenienteMockMvc.perform(put("/api/agua-provenientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agua_provenienteDTO)))
            .andExpect(status().isCreated());

        // Validate the Agua_proveniente in the database
        List<Agua_proveniente> agua_provenienteList = agua_provenienteRepository.findAll();
        assertThat(agua_provenienteList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAgua_proveniente() throws Exception {
        // Initialize the database
        agua_provenienteRepository.saveAndFlush(agua_proveniente);
        int databaseSizeBeforeDelete = agua_provenienteRepository.findAll().size();

        // Get the agua_proveniente
        restAgua_provenienteMockMvc.perform(delete("/api/agua-provenientes/{id}", agua_proveniente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Agua_proveniente> agua_provenienteList = agua_provenienteRepository.findAll();
        assertThat(agua_provenienteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Agua_proveniente.class);
    }
}
