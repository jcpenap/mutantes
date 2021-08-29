package com.mercadolibre.service;

import com.mercadolibre.domain.DnaResult;
import com.mercadolibre.dto.Input;
import com.mercadolibre.dto.Response;
import com.mercadolibre.repository.DnaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantServiceImplTest {

    @Mock
    private DnaRepository repository;
    private MutantService service;


    @Before
    public void setUp() {
        service = new MutantServiceImpl(repository);
    }

    @Test
    public void isMutant() {
        List<String> dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        Input input = new Input();
        input.setDna(dna);
        boolean result = service.isMutant(input);
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void isNotMutant() {
        List<String> dna = Arrays.asList("ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG");
        Input input = new Input();
        input.setDna(dna);
        boolean result = service.isMutant(input);
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    public void createDna() {
        String sequence = "[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]";
        service.createDna(sequence, true);
    }

    @Test
    public void stats() {
        List<DnaResult> resultList = new ArrayList<>();
        for(int i = 0; i < 10000; i++) {
            DnaResult dnaResult = new DnaResult();
            dnaResult.setMutant(true);
            dnaResult.setSequence("[ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG]");
            dnaResult.setId(i+1L);
            resultList.add(dnaResult);
        }
        for(int i = 0; i < 4000; i++) {
            DnaResult dnaResult = new DnaResult();
            dnaResult.setMutant(false);
            dnaResult.setSequence("[[ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG]");
            dnaResult.setId(i+1L);
            resultList.add(dnaResult);
        }
        when(repository.findAll()).thenReturn(resultList);
        Response response = service.stats();
        assertNotNull(response);
        assertEquals(10000L, response.getCountMutantDna());
        assertEquals(4000L, response.getCountHumanDna());
        assertEquals("0,4", response.getRatio());
    }
}