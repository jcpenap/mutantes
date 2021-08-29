package com.mercadolibre.repository;

import com.mercadolibre.domain.DnaResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DnaResult, Long> {

}
