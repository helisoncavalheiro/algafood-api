package com.helison.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.helison.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {
  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

  public List<Restaurante> findCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
