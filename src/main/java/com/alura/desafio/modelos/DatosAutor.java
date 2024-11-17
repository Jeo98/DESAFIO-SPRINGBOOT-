package com.alura.desafio.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(@JsonAlias ("name") String nombreAutor, @JsonAlias ("birth_year") int añoNacimiento, @JsonAlias("death_year") int añofallecimiento) {
}
