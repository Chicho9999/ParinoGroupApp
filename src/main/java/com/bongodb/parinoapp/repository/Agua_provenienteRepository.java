package com.bongodb.parinoapp.repository;

import com.bongodb.parinoapp.domain.Agua_proveniente;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Agua_proveniente entity.
 */
@SuppressWarnings("unused")
public interface Agua_provenienteRepository extends JpaRepository<Agua_proveniente,Long> {

}
