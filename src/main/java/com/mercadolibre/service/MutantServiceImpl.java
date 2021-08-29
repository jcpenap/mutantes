package com.mercadolibre.service;

import com.mercadolibre.domain.DnaResult;
import com.mercadolibre.dto.Input;
import com.mercadolibre.dto.Response;
import com.mercadolibre.repository.DnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MutantServiceImpl implements MutantService {

    private final DnaRepository repository;

    @Override
    public boolean isMutant(Input inp) {
        String[] input = new String[inp.getDna().size()];
        inp.getDna().toArray(input);
        int repeatLength = 4;
        int maxStartRow = input.length - repeatLength;
        int maxStartColumn = input[0].length() - repeatLength;
        for (int i = 0; i <= maxStartRow; i++) {
            for (int j = 0; j <= maxStartColumn; j++) {
                boolean allMatch = true;
                char[] sequence = new char[repeatLength];
                sequence[0] = input[i].charAt(j);
                for (int diagonalCounter = 1; diagonalCounter < repeatLength && allMatch; diagonalCounter++) {
                    sequence[diagonalCounter] = input[i + diagonalCounter].charAt(j + diagonalCounter);
                    allMatch &= (sequence[0] == sequence[diagonalCounter]);
                }
                if (allMatch) {
                    return true;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public void createDna(String sequence, boolean isMutant) {
        DnaResult entity = new DnaResult();
        entity.setSequence(sequence);
        entity.setMutant(isMutant);
        repository.save(entity);
    }

    @Override
    public Response stats() {
        List<DnaResult> results = repository.findAll();
        long countMutants = results.stream().filter(item -> item.isMutant()).count();
        long countHumans = results.stream().filter(item -> !item.isMutant()).count();
        DecimalFormat df2 = new DecimalFormat("#.##");
        String ratio = (countMutants >= countHumans) ? df2.format((double) countHumans / countMutants)  : df2.format((double) countMutants / countHumans);
        Response response = new Response();
        response.setCountMutantDna(countMutants);
        response.setCountHumanDna(countHumans);
        response.setRatio(ratio);
        return response;
    }

}
