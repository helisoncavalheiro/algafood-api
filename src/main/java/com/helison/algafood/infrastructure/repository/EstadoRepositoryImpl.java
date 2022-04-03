package com.helison.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.helison.algafood.domain.model.Estado;
import com.helison.algafood.domain.repository.EstadoRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Estado> listar() {
    return manager.createQuery("from Estado", Estado.class).getResultList();
  }

  @Override
  public Estado obter(Long estadoId) {
    return manager.find(Estado.class, estadoId);
  }

  @Override
  @Transactional
  public Estado salvar(Estado estado) {
    return manager.merge(estado);
  }

  @Override
  @Transactional
  public void excluir(Long estadoId) {
    Estado estado = obter(estadoId);
    if(estado == null){
      throw new EmptyResultDataAccessException(1);
    }
    manager.remove(estado);
  }
  
  
}
