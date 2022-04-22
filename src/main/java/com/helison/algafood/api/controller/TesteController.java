package com.helison.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import com.helison.algafood.domain.model.Restaurante;
import com.helison.algafood.domain.repository.RestauranteRepository;
import com.helison.algafood.infrastructure.repository.spec.RestauranteSpecs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @GetMapping("/restaurantes/por-nome-e-frete")
  public List<Restaurante> restaurantePorNomeFrete(
      String nome,
      BigDecimal taxaFreteInicial,
      BigDecimal taxaFreteFinal) {

    return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
  }

  @GetMapping("/restaurantes/com-frete-gratis")
  public List<Restaurante> restaurantesComFreteGratis(String nome) {

    return restauranteRepository
        .findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
  }

}
