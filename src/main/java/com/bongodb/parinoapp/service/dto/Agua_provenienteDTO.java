package com.bongodb.parinoapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Agua_proveniente entity.
 */
public class Agua_provenienteDTO implements Serializable {

    private Long id;

    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Agua_provenienteDTO agua_provenienteDTO = (Agua_provenienteDTO) o;

        if ( ! Objects.equals(id, agua_provenienteDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Agua_provenienteDTO{" +
            "id=" + id +
            ", descripcion='" + descripcion + "'" +
            '}';
    }
}
