package com.mercadolibre.controller;

import com.mercadolibre.dto.Input;
import com.mercadolibre.dto.Response;
import com.mercadolibre.service.MutantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MutantController {

    private final MutantService service;

    @PostMapping("/mutant/")
    public ResponseEntity<String> isMutant(@RequestBody Input input) {
        boolean response = service.isMutant(input);
        String sequence = input.getDna().toString();
        if(response) {
            service.createDna(sequence, Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            service.createDna(sequence, Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Response> stats() {
        Response response = service.stats();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
