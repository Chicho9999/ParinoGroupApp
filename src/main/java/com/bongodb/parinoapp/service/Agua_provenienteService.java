package com.bongodb.parinoapp.service;

import com.bongodb.parinoapp.service.dto.Agua_provenienteDTO;
import java.util.List;

/**
 * Service Interface for managing Agua_proveniente.
 */
public interface Agua_provenienteService {

    /**
     * Save a agua_proveniente.
     *
     * @param agua_provenienteDTO the entity to save
     * @return the persisted entity
     */
    Agua_provenienteDTO save(Agua_provenienteDTO agua_provenienteDTO);

    /**
     *  Get all the agua_provenientes.
     *  
     *  @return the list of entities
     */
    List<Agua_provenienteDTO> findAll();

    /**
     *  Get the "id" agua_proveniente.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Agua_provenienteDTO findOne(Long id);

    /**
     *  Delete the "id" agua_proveniente.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
