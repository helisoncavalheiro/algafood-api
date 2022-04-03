package com.helison.algafood.domain.repository;

import java.util.List;

import com.helison.algafood.domain.model.Restaurante;

public interface RestauranteRepository {
  
  List<Restaurante> listar();
  Restaurante obter(Long restauranteId);
  Restaurante salvar(Restaurante restaurante);
  void excluir(Long restauranteId);
}
