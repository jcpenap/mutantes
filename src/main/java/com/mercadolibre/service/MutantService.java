package com.mercadolibre.service;

import com.mercadolibre.dto.Input;
import com.mercadolibre.dto.Response;

public interface MutantService {
    boolean isMutant(Input input);
    void createDna(String sequence, boolean isMutant);
    Response stats();
}
