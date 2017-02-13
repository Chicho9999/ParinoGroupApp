package com.bongodb.parinoapp.service.mapper;

import com.bongodb.parinoapp.domain.*;
import com.bongodb.parinoapp.service.dto.Agua_provenienteDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Agua_proveniente and its DTO Agua_provenienteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Agua_provenienteMapper {

    Agua_provenienteDTO agua_provenienteToAgua_provenienteDTO(Agua_proveniente agua_proveniente);

    List<Agua_provenienteDTO> agua_provenientesToAgua_provenienteDTOs(List<Agua_proveniente> agua_provenientes);

    Agua_proveniente agua_provenienteDTOToAgua_proveniente(Agua_provenienteDTO agua_provenienteDTO);

    List<Agua_proveniente> agua_provenienteDTOsToAgua_provenientes(List<Agua_provenienteDTO> agua_provenienteDTOs);
}
