package com.helison.algafood.domain.service;

import com.helison.algafood.domain.exception.EntidadeEmUsoException;
import com.helison.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.helison.algafood.domain.model.Cozinha;
import com.helison.algafood.domain.repository.CozinhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  public Cozinha salvar(Cozinha cozinha) {
    return cozinhaRepository.salvar(cozinha);
  }

  public void excluir(Long cozinhaId) {
    try {
      cozinhaRepository.remover(cozinhaId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(
          String.format("Cozinha de id %d não pode ser encontrada", cozinhaId));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Cozinha de código %d não pode ser removida pois está em uso", cozinhaId));
    } 

  }

}
