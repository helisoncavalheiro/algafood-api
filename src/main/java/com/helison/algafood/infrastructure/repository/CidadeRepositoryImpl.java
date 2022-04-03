package com.helison.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.helison.algafood.domain.model.Cidade;
import com.helison.algafood.domain.repository.CidadeRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {
  
  @PersistenceContext
  private EntityManager manager;

  @Override
  public Cidade obter(Long cidadeId) {
    return manager.find(Cidade.class, cidadeId);
  }

  @Override
  public List<Cidade> listar() {
    return manager.createQuery("from Cidade", Cidade.class).getResultList();
  }

  @Override
  @Transactional
  public Cidade salvar(Cidade cidade) {
    return manager.merge(cidade);
  }

  @Override
  @Transactional
  public void excluir(Long cidadeId) {
    Cidade cidade = obter(cidadeId);
    if(cidade == null){
      throw new EmptyResultDataAccessException(1);
    }
    manager.remove(cidade);
  }
}
