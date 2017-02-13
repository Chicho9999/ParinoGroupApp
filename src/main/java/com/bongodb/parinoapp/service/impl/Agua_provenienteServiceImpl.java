package com.bongodb.parinoapp.service.impl;

import com.bongodb.parinoapp.service.Agua_provenienteService;
import com.bongodb.parinoapp.domain.Agua_proveniente;
import com.bongodb.parinoapp.repository.Agua_provenienteRepository;
import com.bongodb.parinoapp.service.dto.Agua_provenienteDTO;
import com.bongodb.parinoapp.service.mapper.Agua_provenienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Agua_proveniente.
 */
@Service
@Transactional
public class Agua_provenienteServiceImpl implements Agua_provenienteService{

    private final Logger log = LoggerFactory.getLogger(Agua_provenienteServiceImpl.class);
    
    private final Agua_provenienteRepository agua_provenienteRepository;

    private final Agua_provenienteMapper agua_provenienteMapper;

    public Agua_provenienteServiceImpl(Agua_provenienteRepository agua_provenienteRepository, Agua_provenienteMapper agua_provenienteMapper) {
        this.agua_provenienteRepository = agua_provenienteRepository;
        this.agua_provenienteMapper = agua_provenienteMapper;
    }

    /**
     * Save a agua_proveniente.
     *
     * @param agua_provenienteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Agua_provenienteDTO save(Agua_provenienteDTO agua_provenienteDTO) {
        log.debug("Request to save Agua_proveniente : {}", agua_provenienteDTO);
        Agua_proveniente agua_proveniente = agua_provenienteMapper.agua_provenienteDTOToAgua_proveniente(agua_provenienteDTO);
        agua_proveniente = agua_provenienteRepository.save(agua_proveniente);
        Agua_provenienteDTO result = agua_provenienteMapper.agua_provenienteToAgua_provenienteDTO(agua_proveniente);
        return result;
    }

    /**
     *  Get all the agua_provenientes.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Agua_provenienteDTO> findAll() {
        log.debug("Request to get all Agua_provenientes");
        List<Agua_provenienteDTO> result = agua_provenienteRepository.findAll().stream()
            .map(agua_provenienteMapper::agua_provenienteToAgua_provenienteDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one agua_proveniente by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Agua_provenienteDTO findOne(Long id) {
        log.debug("Request to get Agua_proveniente : {}", id);
        Agua_proveniente agua_proveniente = agua_provenienteRepository.findOne(id);
        Agua_provenienteDTO agua_provenienteDTO = agua_provenienteMapper.agua_provenienteToAgua_provenienteDTO(agua_proveniente);
        return agua_provenienteDTO;
    }

    /**
     *  Delete the  agua_proveniente by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agua_proveniente : {}", id);
        agua_provenienteRepository.delete(id);
    }
}
