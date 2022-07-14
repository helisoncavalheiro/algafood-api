package com.helison.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.helison.algafood.domain.model.Restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository
    extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

  
  @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamento")
  List<Restaurante> findAll();

  List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

  List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

}
