package com.helison.algafood.domain.service;

import java.util.Optional;

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
    Optional<Estado> estado = estadoRepository.findById(estadoId);

    if (estado.isEmpty()) {
      throw new EntidadeNaoEncontradaException( String.format("Estado %d não existe", estadoId));
    }
    
    cidade.setEstado(estado.get());

    return cidadeRepository.save(cidade);
  }

  public void excluir(Long cidadeId) {
    try {
      cidadeRepository.deleteById(cidadeId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format("Cidade %d não econtrada", cidadeId));
    }
  }
}
