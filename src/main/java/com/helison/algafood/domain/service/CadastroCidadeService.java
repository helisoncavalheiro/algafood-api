package com.helison.algafood.domain.service;

import com.helison.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.helison.algafood.domain.model.Cidade;
import com.helison.algafood.domain.model.Estado;
import com.helison.algafood.domain.repository.CidadeRepository;
import com.helison.algafood.domain.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoRepository estadoRepository;

  public Cidade salvar(Cidade cidade) {

    Long estadoId = cidade.getEstado().getId();
    Estado estado = estadoRepository.obter(estadoId);

    if(estado == null){
      throw new EntidadeNaoEncontradaException( String.format("Estado %d não existe", estadoId));
    }
    
    cidade.setEstado(estado);

    return cidadeRepository.salvar(cidade);
  }

  public void excluir(Long cidadeId) {
    try {
      cidadeRepository.excluir(cidadeId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format("Cidade %d não econtrada", cidadeId));
    }
  }
}
