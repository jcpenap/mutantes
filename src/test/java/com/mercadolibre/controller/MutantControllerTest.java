package com.mercadolibre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.dto.Input;
import com.mercadolibre.service.MutantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class MutantControllerTest {

    @Mock
    private MutantService service;
    @InjectMocks
    private MutantController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(service, controller).build();
    }

    @Test
    public void isMutant() throws Exception {
        List<String> dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        Input input = new Input();
        input.setDna(dna);
        when(service.isMutant(any(Input.class))).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(input)));
    }

    @Test
    public void isNotMutant() throws Exception {
        List<String> dna = Arrays.asList("ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG");
        Input input = new Input();
        input.setDna(dna);
        when(service.isMutant(any(Input.class))).thenReturn(Boolean.FALSE);
        mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(input)));
    }

    @Test
    public void stats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stats")
                .contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}