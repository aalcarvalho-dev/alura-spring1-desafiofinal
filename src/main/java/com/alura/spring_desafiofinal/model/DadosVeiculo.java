package com.alura.spring_desafiofinal.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Valor") String valor, @JsonAlias("Marca") String marca, 
                        @JsonAlias("Modelo") String modelo, @JsonAlias("AnoModelo") Integer ano, 
                        @JsonAlias("Combustivel") String combustivel) {

}
