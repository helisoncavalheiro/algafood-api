package com.helison.algafood.domain.service;

import com.helison.algafood.domain.exception.EntidadeEmUsoException;
import com.helison.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.helison.algafood.domain.model.Estado;
import com.helison.algafood.domain.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {
  
  @Autowired
  private EstadoRepository estadoRepository;

  public Estado salvar(Estado estado){
      return estadoRepository.salvar(estado);
  }

  public void excluir(Long estadoId){
    try {
      estadoRepository.excluir(estadoId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException( String.format("Estado com id %d não pode ser encontrado", estadoId) );
    }catch(DataIntegrityViolationException e){
      throw new EntidadeEmUsoException(String.format("Estado %d não pode ser excluído pois está em uso", estadoId));
    }
  }
}
