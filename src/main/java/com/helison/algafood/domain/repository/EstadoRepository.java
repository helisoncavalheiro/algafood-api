package com.helison.algafood.domain.repository;

import com.helison.algafood.domain.model.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}