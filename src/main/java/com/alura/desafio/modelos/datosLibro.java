package com.alura.desafio.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record datosLibro(@JsonAlias("title") String titulo, @JsonAlias("languages") List<String> idiomasLibro, @JsonAlias("authors") List<String> autores, @JsonAlias("download_count") int cantidadDescargas,
                         @JsonAlias("subjects") List<String> temaDelLibro, @JsonAlias("copyright") boolean copyright){

}
