package com.mercadolibre.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;
    @JsonProperty("count_human_dna")
    private long countHumanDna;
    private String ratio;
}
