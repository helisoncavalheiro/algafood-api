package com.helison.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.helison.algafood.domain.model.Restaurante;
import com.helison.algafood.domain.repository.RestauranteRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RestauranteRespositoryImpl implements RestauranteRepository {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Restaurante> listar() {
    return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
  }

  @Override
  public Restaurante obter(Long restauranteId) {
    return manager.find(Restaurante.class, restauranteId);
  }

  @Override
  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    return manager.merge(restaurante);
  }

  @Override
  @Transactional
  public void excluir(Long restauranteId) {
    Restaurante restaurante = obter(restauranteId);

    if (restaurante == null) {
      throw new EmptyResultDataAccessException(1);
    }

    manager.remove(restaurante);
  }

}
