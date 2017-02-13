package com.bongodb.parinoapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bongodb.parinoapp.service.Agua_provenienteService;
import com.bongodb.parinoapp.web.rest.util.HeaderUtil;
import com.bongodb.parinoapp.service.dto.Agua_provenienteDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Agua_proveniente.
 */
@RestController
@RequestMapping("/api")
public class Agua_provenienteResource {

    private final Logger log = LoggerFactory.getLogger(Agua_provenienteResource.class);

    private static final String ENTITY_NAME = "agua_proveniente";
        
    private final Agua_provenienteService agua_provenienteService;

    public Agua_provenienteResource(Agua_provenienteService agua_provenienteService) {
        this.agua_provenienteService = agua_provenienteService;
    }

    /**
     * POST  /agua-provenientes : Create a new agua_proveniente.
     *
     * @param agua_provenienteDTO the agua_provenienteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agua_provenienteDTO, or with status 400 (Bad Request) if the agua_proveniente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agua-provenientes")
    @Timed
    public ResponseEntity<Agua_provenienteDTO> createAgua_proveniente(@RequestBody Agua_provenienteDTO agua_provenienteDTO) throws URISyntaxException {
        log.debug("REST request to save Agua_proveniente : {}", agua_provenienteDTO);
        if (agua_provenienteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new agua_proveniente cannot already have an ID")).body(null);
        }
        Agua_provenienteDTO result = agua_provenienteService.save(agua_provenienteDTO);
        return ResponseEntity.created(new URI("/api/agua-provenientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agua-provenientes : Updates an existing agua_proveniente.
     *
     * @param agua_provenienteDTO the agua_provenienteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agua_provenienteDTO,
     * or with status 400 (Bad Request) if the agua_provenienteDTO is not valid,
     * or with status 500 (Internal Server Error) if the agua_provenienteDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agua-provenientes")
    @Timed
    public ResponseEntity<Agua_provenienteDTO> updateAgua_proveniente(@RequestBody Agua_provenienteDTO agua_provenienteDTO) throws URISyntaxException {
        log.debug("REST request to update Agua_proveniente : {}", agua_provenienteDTO);
        if (agua_provenienteDTO.getId() == null) {
            return createAgua_proveniente(agua_provenienteDTO);
        }
        Agua_provenienteDTO result = agua_provenienteService.save(agua_provenienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agua_provenienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agua-provenientes : get all the agua_provenientes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of agua_provenientes in body
     */
    @GetMapping("/agua-provenientes")
    @Timed
    public List<Agua_provenienteDTO> getAllAgua_provenientes() {
        log.debug("REST request to get all Agua_provenientes");
        return agua_provenienteService.findAll();
    }

    /**
     * GET  /agua-provenientes/:id : get the "id" agua_proveniente.
     *
     * @param id the id of the agua_provenienteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agua_provenienteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/agua-provenientes/{id}")
    @Timed
    public ResponseEntity<Agua_provenienteDTO> getAgua_proveniente(@PathVariable Long id) {
        log.debug("REST request to get Agua_proveniente : {}", id);
        Agua_provenienteDTO agua_provenienteDTO = agua_provenienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(agua_provenienteDTO));
    }

    /**
     * DELETE  /agua-provenientes/:id : delete the "id" agua_proveniente.
     *
     * @param id the id of the agua_provenienteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agua-provenientes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAgua_proveniente(@PathVariable Long id) {
        log.debug("REST request to delete Agua_proveniente : {}", id);
        agua_provenienteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
